package com.searchinner.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonObject;
import com.searchinner.model.Comment;
import com.searchinner.model.User;
import com.searchinner.service.dao.ICommentServiceDAO;

@Controller
@RequestMapping("/comment")
public class CommentController {
	private ICommentServiceDAO commentServiceDAOImp;
	/**
	 * 添加评论
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/addComment")
	@ResponseBody
	public void addComment(@RequestBody Comment comment,HttpServletResponse response,HttpSession httpSession) throws IOException{
		JsonObject json = new JsonObject();
		User currentuser = (User) httpSession.getAttribute("currentUser");
		if (currentuser != null) {
			/*if(currentuser.getUserName().equals(comment.getCommentedUserId())){
				json.addProperty("message", "评论失败,不能评论自己的文章");
				json.addProperty("status", "2");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}*/
			comment.setCommentUserId(currentuser.getUserName());
			comment.setCommentUserName(currentuser.getRealName());
			Comment commentRet = null;
			try{
				commentRet = commentServiceDAOImp.addComment(comment);
			}catch(Exception e){
				json.addProperty("message", "评论失败");
				json.addProperty("status", "2");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			json.addProperty("message", "评论成功");
			json.addProperty("status", "0");
			json.addProperty("info", commentRet.getCommentId());
			System.out.println(json.toString());
		} else {
			json.addProperty("message", "评论失败");
			json.addProperty("status", "1");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	
	public ICommentServiceDAO getCommentServiceDAOImp() {
		return commentServiceDAOImp;
	}
	
	@Resource(name = "commentServiceDAOImp")
	public void setCommentServiceDAOImp(ICommentServiceDAO commentServiceDAOImp) {
		this.commentServiceDAOImp = commentServiceDAOImp;
	}
}
