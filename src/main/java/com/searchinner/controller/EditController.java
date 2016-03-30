package com.searchinner.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.searchinner.model.Article;
import com.searchinner.model.Catalog;
import com.searchinner.model.CatalogAndArticleRef;
import com.searchinner.model.Comment;
import com.searchinner.model.User;
import com.searchinner.service.dao.IArticleServiceDAO;
import com.searchinner.service.dao.ICatalogAndArticleRefServiceDAO;
import com.searchinner.service.dao.ICatalogServiceDAO;
import com.searchinner.service.dao.ICommentServiceDAO;
import com.searchinner.viewmodel.ArticleAndCatalog;

@Controller
@RequestMapping("/edit")
public class EditController {
	private IArticleServiceDAO articleServiceDAOImp;
	private ICatalogServiceDAO catalogServiceDAOImp;
	private ICatalogAndArticleRefServiceDAO catalogAndArticleRefServiceDAOImp;
	private ICommentServiceDAO commentServiceDAOImp;
	
	/**
	 * 进入编辑文章页面
	 * @param httpSession
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index( HttpSession httpSession) throws Exception {
		ModelAndView modelView = new ModelAndView();
		User user = (User) httpSession.getAttribute("currentUser");
		if(user != null){
			List<Catalog> lists = catalogServiceDAOImp.getCatalogs(user.getUserName());
			modelView.addObject("catalogList", lists); // 所有的分类
			/*redirectAttributes.addFlashAttribute("catalogList", lists);*/
		}
		modelView.setViewName("/user/user-edit");
		return modelView;
	}
	
	/**
	 * 重新编辑
	 * @param sp
	 * @param httpSession
	 * @param redirectAttributes
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/reedit")
	public ModelAndView reedit(String id,HttpSession httpSession) throws Exception {
		ModelAndView modelView = new ModelAndView();
		User user = (User) httpSession.getAttribute("currentUser");
		if(user != null){
			List<Catalog> lists = catalogServiceDAOImp.getCatalogs(user.getUserName());
			modelView.addObject("catalogList", lists); // 所有的分类
			
			Article article = articleServiceDAOImp.getArticle(id); // 文章
			
			CatalogAndArticleRef ref = new CatalogAndArticleRef();
			ref.setArticleid(article.getId());
			ref.setUserName(user.getUserName());
			List<CatalogAndArticleRef> refList = catalogAndArticleRefServiceDAOImp.getCatalogAndArticleRef(ref ); //文章对应的分类
			Map<Integer, String> refMap = new HashMap<Integer, String>();
			for(CatalogAndArticleRef refTmp : refList ){
				refMap.put(refTmp.getCatalogid(), "shuai");
			}
			
			modelView.addObject("refMap", refMap);
			modelView.addObject("article", article);
		}
		modelView.setViewName("/user/user-reedit");
		return modelView;
	}

	/**
	 * 添加文章
	 * 
	 * @param name
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(value = "/addArticle")
	@ResponseBody
	public void addArticle(@RequestBody ArticleAndCatalog articleAndCatalog,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		JsonObject json = new JsonObject();
		User user = (User) httpSession.getAttribute("currentUser");
		
		
		if (user != null) {
			Article articleRet = null;
			try{
				if(articleAndCatalog.getArticleId().equals("")){
					// 添加
					articleRet = articleServiceDAOImp.addOrUpdateArticle(articleAndCatalog, user,false);
				}else{
					// 更新
					articleRet = articleServiceDAOImp.addOrUpdateArticle(articleAndCatalog, user,true);
				}
			}catch(Exception e){
				json.addProperty("message", "添加失败");
				json.addProperty("status", "2");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			json.addProperty("message", "发表成功");
			json.addProperty("status", "0");
			json.addProperty("info", articleRet.getId());
			System.out.println(json.toString());
		} else {
			json.addProperty("message", "添加失败");
			json.addProperty("status", "1");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	
	
	
	/**
	 * 修改文章
	 * 
	 * @param name
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateArticle")
	@ResponseBody
	public void updateArticle(@RequestBody ArticleAndCatalog articleAndCatalog,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		JsonObject json = new JsonObject();
		User user = (User) httpSession.getAttribute("currentUser");
		
		
		if (user != null) {
			Article articleRet = null;
			try{
				articleRet = articleServiceDAOImp.addOrUpdateArticle(articleAndCatalog, user,true);
			}catch(Exception e){
				json.addProperty("message", "修改失败");
				json.addProperty("status", "2");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			json.addProperty("message", "修改成功");
			json.addProperty("status", "0");
			json.addProperty("info", articleRet.getId());
			System.out.println(json.toString());
		} else {
			json.addProperty("message", "修改失败");
			json.addProperty("status", "1");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}
	
	/**
	 * 删除文章
	 * 
	 * @param name
	 * @param out
	 * @throws IOException
	 */
	@RequestMapping(value = "/deleteArticle")
	@ResponseBody
	public void deleteArticle(@RequestBody ArticleAndCatalog articleAndCatalog,
			HttpServletResponse response, HttpSession httpSession)
			throws IOException {
		JsonObject json = new JsonObject();
		User user = (User) httpSession.getAttribute("currentUser");
		
		
		if (user != null) {
			try{
				articleServiceDAOImp.deleteArticle(articleAndCatalog.getArticleId());
			}catch(Exception e){
				json.addProperty("message", "删除失败");
				json.addProperty("status", "2");
				response.setContentType("text/html;charset=UTF-8"); // 防止乱码
				response.getWriter().print(json.toString());
				return ;
			}
			json.addProperty("message", "删除成功");
			json.addProperty("status", "0");
			System.out.println(json.toString());
		} else {
			json.addProperty("message", "删除失败");
			json.addProperty("status", "1");
		}
		response.setContentType("text/html;charset=UTF-8"); // 防止乱码
		response.getWriter().print(json.toString());
	}

	/**
	 * 根据文章id获得文章内容
	 * @param id
	 * @return
	 */
	@RequestMapping("/getArticle")
	public ModelAndView getArticleContent(String id) {
		Article article = articleServiceDAOImp.getArticle(id);
		ModelAndView model = new ModelAndView();
		
		String tag = article.getArticleTag();
		List<String> articleTagList = new ArrayList<String>();
		if(tag != null && tag.length() > 0){
			String[]  strArr = tag.split(",");
			for(String str : strArr){
				articleTagList.add(str);
			}
			model.addObject("articleTagList", articleTagList);
		}
		
		List<Comment> commentsRet = commentServiceDAOImp.getComments(article.getId());
		List<Comment> subReplay = new LinkedList<Comment>();
		Map<Integer, List<Comment>> mapsRet = new HashMap<Integer, List<Comment>>();
		if(commentsRet != null && commentsRet.size() > 0){
			for (Comment commentModel : commentsRet) {
				if(commentModel.getCommentPid() != 0){
					if(mapsRet.containsKey(commentModel.getCommentPid())){
						List<Comment> subReplayOfList = mapsRet.get(commentModel.getCommentPid());
						subReplayOfList.add(commentModel);
						mapsRet.put(commentModel.getCommentPid(), subReplayOfList);
					}else{
						subReplay = new LinkedList<Comment>();
						subReplay.add(commentModel);
						mapsRet.put(commentModel.getCommentPid(), subReplay);
					}
				}
			}
			subReplay = new LinkedList<Comment>();
			for (Comment commentModel : commentsRet) {
				
				if(commentModel.getCommentPid() == 0){
					subReplay.add(commentModel);
				}
			}
			
		}
		model.addObject("article", article);
		if( subReplay != null && subReplay.size() > 0){
			model.addObject("commentsRet", subReplay);
		}else{
			model.addObject("commentsRet", commentsRet);
		}
		//model.addObject("commentsRet", commentsRet);
		model.addObject("mapsRet", mapsRet);
		model.setViewName("user/user-readArticle");
		return model;
	}

	public IArticleServiceDAO getArticleServiceDAOImp() {
		return articleServiceDAOImp;
	}

	@Resource(name = "articleServiceDAOImp")
	public void setArticleServiceDAOImp(IArticleServiceDAO articleServiceDAOImp) {
		this.articleServiceDAOImp = articleServiceDAOImp;
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
	
	public ICommentServiceDAO getCommentServiceDAOImp() {
		return commentServiceDAOImp;
	}
	
	@Resource(name = "commentServiceDAOImp")
	public void setCommentServiceDAOImp(ICommentServiceDAO commentServiceDAOImp) {
		this.commentServiceDAOImp = commentServiceDAOImp;
	}

}
