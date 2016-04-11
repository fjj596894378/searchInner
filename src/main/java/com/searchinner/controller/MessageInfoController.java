package com.searchinner.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.searchinner.model.MessageInfo;
import com.searchinner.model.User;
import com.searchinner.service.dao.IMessageInfoServiceDAO;
import com.searchinner.util.MessageInfoCache;

@Controller
@RequestMapping("/messageInfo")
public class MessageInfoController {
	private IMessageInfoServiceDAO messageInfoServiceDAOImp;
	
	
	@RequestMapping(value = "/getMessageInfos", method = RequestMethod.POST)
	@ResponseBody
	public void getMessageInfos(@RequestBody String tmp, HttpSession httpSession,HttpServletResponse response) throws IOException {
		User user = (User) httpSession.getAttribute("currentUser");
		JsonObject json = new JsonObject();
		if(user != null){
			MessageInfoCache  cache = MessageInfoCache.getInstance();
			MessageInfo retMessage = cache.getMessageInfoList(user.getUserName());
			if(retMessage != null){
				String message = new Gson().toJson(retMessage); 
				json.addProperty("retMessage", message);
				json.addProperty("status", "0"); //成功
				
			}else{
				json.addProperty("status", "2"); //没消息
			}
		}else{
			json.addProperty("status", "1"); // 未登录
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
		//messageInfoServiceDAOImp.getMessageInfo(commentedUserId);
	}
	
	public IMessageInfoServiceDAO getMessageInfoServiceDAOImp() {
		return messageInfoServiceDAOImp;
	}

	@Resource(name = "messageInfoServiceDAOImp")
	public void setMessageInfoServiceDAOImp(
			IMessageInfoServiceDAO messageInfoServiceDAOImp) {
		this.messageInfoServiceDAOImp = messageInfoServiceDAOImp;
	}
}
