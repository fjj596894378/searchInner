package com.searchinner.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.searchinner.model.Article;
import com.searchinner.model.Catalog;
import com.searchinner.model.User;
import com.searchinner.service.dao.ICatalogAndArticleRefServiceDAO;
import com.searchinner.service.dao.ICatalogServiceDAO;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
	private ICatalogServiceDAO catalogServiceDAOImp;
	private ICatalogAndArticleRefServiceDAO catalogAndArticleRefServiceDAOImp;
	
	/**
	 * 添加分类
	 * @param catalog 分类信息
	 * @param response
	 * @param httpSession
	 * @throws IOException
	 */
	@RequestMapping(value = "/addCatalog")
	@ResponseBody
	public void addCatalog(@RequestBody Catalog catalog,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		JsonObject json = new JsonObject();
		User user = (User) httpSession.getAttribute("currentUser");
		if (user != null) {
			catalog.setUserName(user.getUserName());
			try{
				catalogServiceDAOImp.addCatalog(catalog); //添加
			}catch(Exception e){
				List<Catalog> lists = catalogServiceDAOImp.getCatalogs(user.getUserName());
				String listStr = new Gson().toJson(lists);  
				json.addProperty("message", "添加失败");
				json.addProperty("listStr", listStr);
				json.addProperty("status", "2");
				System.out.println(json.toString());
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			List<Catalog> lists = catalogServiceDAOImp.getCatalogs(user.getUserName());
			String listStr = new Gson().toJson(lists);  
			json.addProperty("message", "添加成功");
			json.addProperty("listStr", listStr);
			json.addProperty("status", "0");
			System.out.println(json.toString());
		} else {
			json.addProperty("message", "添加失败");
			json.addProperty("status", "1");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	
	/**
	 * 获得分类列表
	 * @param sp
	 * @param httpSession
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCatalogs")
	public ModelAndView getCatalogs(Catalog catalog,HttpSession httpSession) throws Exception {
		ModelAndView modelView = new ModelAndView();
		User user = (User) httpSession.getAttribute("currentUser");
		if(user != null){
			List<Catalog> lists = catalogServiceDAOImp.getCatalogs(user.getUserName());
			modelView.addObject("catalogList", lists); // 所有的分类
		}
		modelView.setViewName("/user/user_catalogManage");
		return modelView;
	}
	
	/**
	 * 根据分类id获得文章
	 * @param sp
	 * @param httpSession
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getArticleBycatalogId")
	public ModelAndView getArticleBycatalogId(Catalog catalog,HttpSession httpSession) throws Exception {
		ModelAndView modelView = new ModelAndView();
		User user = (User) httpSession.getAttribute("currentUser");
		if(user != null){
			catalog.setUserName(user.getUserName());
			List<Article> lists = catalogAndArticleRefServiceDAOImp.getArticleBycatalogId(catalog);
			modelView.addObject("articleList", lists); // 所有的分类
		}
		modelView.setViewName("/user/user_articleManage");
		return modelView;
	}
	
	
	
	/**
	 * 修改分类信息
	 * @param sp
	 * @param httpSession
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateCatalog")
	@ResponseBody
	public void updateCatalog(@RequestBody Catalog catalog,HttpServletResponse response, HttpSession httpSession) throws Exception {
		JsonObject json = new JsonObject();
		User user = (User) httpSession.getAttribute("currentUser");
		if(user != null){
			Catalog catalogRet = catalogServiceDAOImp.updateCatalog(catalog);
			json.addProperty("message", "添加成功");
			json.addProperty("catalogName", catalogRet.getCatalogName());
			json.addProperty("catalogId", catalogRet.getCatalogid());
			json.addProperty("status", "0");
			System.out.println(json.toString());
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	public ICatalogServiceDAO getCatalogServiceDAOImp() {
		return catalogServiceDAOImp;
	}
	
	@Resource(name = "catalogServiceDAOImp")
	public void setCatalogServiceDAOImp(ICatalogServiceDAO catalogServiceDAOImp) {
		this.catalogServiceDAOImp = catalogServiceDAOImp;
	}


	public ICatalogAndArticleRefServiceDAO getCatalogAndArticleRefServiceDAOImp() {
		return catalogAndArticleRefServiceDAOImp;
	}

	@Resource(name = "catalogAndArticleRefServiceDAOImp")
	public void setCatalogAndArticleRefServiceDAOImp(
			ICatalogAndArticleRefServiceDAO catalogAndArticleRefServiceDAOImp) {
		this.catalogAndArticleRefServiceDAOImp = catalogAndArticleRefServiceDAOImp;
	}
	
}
