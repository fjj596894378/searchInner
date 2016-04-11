package com.searchinner.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.searchinner.model.Article;
import com.searchinner.model.User;
import com.searchinner.service.dao.IArticleServiceDAO;

@Controller
@RequestMapping("/article")
public class ArticleController {
	private IArticleServiceDAO articleServiceDAOImp;
	/**
	 * 获得当前用户的所有文章
	 * @param user 用户
	 * @return
	 */
	@RequestMapping("/getArticlesByUserId")
	public ModelAndView getArticlesByUserId(HttpSession httpSession){
		User currentuser = (User) httpSession.getAttribute("currentUser");
		
		ModelAndView modelView = new ModelAndView();
		List<Article> articleList = articleServiceDAOImp.getArticlesByUserId(currentuser.getUserName());
		modelView.addObject("articleList",articleList); 
		modelView.setViewName("/user/user_articleManage");
		return modelView;
		 
	}
	
	
	/**
	 * 获得草稿箱内容
	 * @param user
	 * @return
	 */
	@RequestMapping("/getArticlesDraftByUserId")
	public ModelAndView getArticlesDraftByUserId(HttpSession httpSession){
		ModelAndView modelView = new ModelAndView();
		User currentuser = (User) httpSession.getAttribute("currentUser");
		List<Article> articleList = articleServiceDAOImp.getArticlesDraftByUserId(currentuser.getUserName());
	
		modelView.addObject("articleList",articleList); 
		modelView.setViewName("/user/user_articleDraftManage");
		return modelView;
	}
	
	/**
	 * 查看别人的所有文章
	 * @param user 用户
	 * @return
	 */
	@RequestMapping("/getArticlesByOtherUserId")
	public ModelAndView getArticlesByOtherUserId(String userName,HttpSession httpSession){
		ModelAndView modelView = new ModelAndView();
		List<Article> articleList = articleServiceDAOImp.getArticlesByUserId(userName);
		User currentuser = (User) httpSession.getAttribute("currentUser");
		if(currentuser != null){
			if(currentuser.getUserName().equals(userName)){
				modelView.addObject("selfAuth","1"); 
			}else{
				modelView.addObject("otherAuth","1"); 
			}
		}else{
			modelView.addObject("otherAuth","1"); 
		}
		modelView.addObject("articleList",articleList); 
		modelView.setViewName("/user/user_articleManage");
		return modelView;
		 
	}
	
	
	public IArticleServiceDAO getArticleServiceDAOImp() {
		return articleServiceDAOImp;
	}

	@Resource(name = "articleServiceDAOImp")
	public void setArticleServiceDAOImp(IArticleServiceDAO articleServiceDAOImp) {
		this.articleServiceDAOImp = articleServiceDAOImp;
	}
	
	
}
