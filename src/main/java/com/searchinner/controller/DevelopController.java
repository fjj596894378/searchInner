package com.searchinner.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.searchinner.model.DevelopLink;
import com.searchinner.model.MessageInfo;
import com.searchinner.model.User;
import com.searchinner.service.dao.IDevelopServiceDAO;
import com.searchinner.util.MessageInfoCache;
import com.searchinner.util.SingleThreadStdoutStderr;
import com.searchinner.viewmodel.SSHLinkModel;

@Controller
@RequestMapping("/develop")
public class DevelopController {
	private IDevelopServiceDAO developServiceDAOImp;
	private static final String CMD= "cd ./bin;./tt;";

	public Map<String,String> serviceNameMap;
	
	/**
	 * 根据服务器地址，获取所有的用户名
	 * @param linkModel
	 * @param httpSession
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getDevelopLinkInfoByAddress", method = RequestMethod.POST)
	@ResponseBody
	public void getDataInfos(@RequestBody DevelopLink linkModel, HttpSession httpSession,HttpServletResponse response) throws IOException {
		JsonObject json = new JsonObject();
		List<DevelopLink> lists = developServiceDAOImp.getDevelopLinkInfoByAddress(linkModel.getSiteaddress());
		/*Map<String, String> mapRet = new HashMap<String, String>();
		if(lists != null && lists.size() > 0){
			DevelopLink model  = null;
			for (int i = 0; i < lists.size(); i++) {
				model = lists.get(i);
				//siteAddressList.add(model.getSiteaddress());
				mapRet.put(model.getSiteusername(), model.getSitepassword());
			}
		}*/
		json.addProperty("status", "0");
		String listsStr = new Gson().toJson(lists);  
		json.addProperty("listsStr", listsStr); // 所有的分类
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
		//messageInfoServiceDAOImp.getMessageInfo(commentedUserId);
	}
	
	/**
	 * 进入页面调用的方法
	 * @param httpSession
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getList")
	public ModelAndView getList(HttpSession httpSession,HttpServletResponse response) throws IOException {
		ModelAndView modelView = new ModelAndView();
		List<DevelopLink> lists = developServiceDAOImp.getDevelopLinks();
		//List<String> siteAddressList = new LinkedList<String>();
		Map<String, String> mapRet = new HashMap<String, String>();
		if(lists != null && lists.size() > 0){
			DevelopLink model  = null;
			for (int i = 0; i < lists.size(); i++) {
				model = lists.get(i);
				//siteAddressList.add(model.getSiteaddress());
				mapRet.put(model.getSiteaddress(), model.getSiteusername());
			}
		}
		modelView.addObject("mapRet", mapRet); // 所有的分类
		modelView.setViewName("/sys/devManager");
		return modelView;
	}

	/**
	 * 添加
	 * @param linkModel
	 * @param httpSession
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/addDevelopLink", method = RequestMethod.POST)
	@ResponseBody
	public void addDevelopLink(@RequestBody DevelopLink linkModel, HttpSession httpSession,HttpServletResponse response) throws IOException {
		JsonObject json = new JsonObject();
		DevelopLink retModel = null;
		try{
			retModel = developServiceDAOImp.addDevelopLink(linkModel);
		}catch(Exception e){
			//e.printStackTrace();
		}
		if(retModel != null){
			
			json.addProperty("status", "0");
			json.addProperty("message", "添加成功");
		}else{
			json.addProperty("status", "2");
			json.addProperty("message", "添加失败,请检查账号密码");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
		//messageInfoServiceDAOImp.getMessageInfo(commentedUserId);
	}
	
	/**
	 * 查询
	 * @param linkModel
	 * @param httpSession
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/queryServerStatus", method = RequestMethod.POST)
	@ResponseBody
	public void  queryServerStatus(@RequestBody DevelopLink linkModel, HttpSession httpSession,HttpServletResponse response) throws IOException{
		JsonObject json = new JsonObject();
		linkModel.setCmd(CMD);
		DevelopLink infoRet = developServiceDAOImp.getPasswordByUserName(linkModel); // 获得密码
		linkModel.setSitepassword(infoRet.getSitepassword());
		
		Map<String, String> mapRet = developServiceDAOImp.queryServerStatus(linkModel);
		if(mapRet == null){
			json.addProperty("status", "2");
			json.addProperty("message", "账号密码验证失败");
		}else{
			String mapStr = new Gson().toJson(mapRet);  
			json.addProperty("mapStr", mapStr); // 所有的分类
			json.addProperty("retPassword", infoRet.getSitepassword()); 
			json.addProperty("status", "0");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 开启或者关闭
	 * @param linkModel
	 * @param httpSession
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value = "/startOrStopCmd", method = RequestMethod.POST)
	@ResponseBody
	public void  startOrStopCmd(@RequestBody DevelopLink linkModel, HttpSession httpSession,HttpServletResponse response) throws IOException{
		JsonObject json = new JsonObject();
		List<String> listOut = new LinkedList<String>();
		String realServiceName = this.serviceNameMap.get(linkModel.getServiceName().trim());
		
		if(realServiceName != null){
			linkModel.setServiceName(realServiceName);
		}
		if(linkModel.getStatus() != null && linkModel.getStatus().equals("N") ){
			//启动
			linkModel.setCmd("cd ./bin;./" + linkModel.getServiceName().trim() + " start");
			listOut = developServiceDAOImp.startOrStopCmd(linkModel,true);
		}else{
			// 关闭
			linkModel.setCmd("cd ./bin;./" + linkModel.getServiceName() + " stop");
			listOut = developServiceDAOImp.startOrStopCmd(linkModel,false);
			//linkModel.setCmd(CMD);
		}
		
		
		if(listOut == null){
			json.addProperty("status", "2");
			json.addProperty("message", "账号密码验证失败");
		}else if(listOut.size() == 0){
			json.addProperty("status", "3");
			json.addProperty("message", "服务启动失败。可能原因：【服务名不正确】");
			//json.addProperty("str", "操作成功");
		}else{
			String listOutStr = new Gson().toJson(listOut);  
			json.addProperty("status", "0");
			json.addProperty("listOutStr", listOutStr); //输出结果
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	public IDevelopServiceDAO getDevelopServiceDAOImp() {
		return developServiceDAOImp;
	}
	@Resource(name = "developServiceDAOImp")
	public void setDevelopServiceDAOImp(IDevelopServiceDAO developServiceDAOImp) {
		this.developServiceDAOImp = developServiceDAOImp;
	}

	public Map<String, String> getServiceNameMap() {
		return serviceNameMap;
	}

	public void setServiceNameMap(Map<String, String> serviceNameMap) {
		this.serviceNameMap = serviceNameMap;
	}
}
