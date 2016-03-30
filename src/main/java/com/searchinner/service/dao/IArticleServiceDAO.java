package com.searchinner.service.dao;

import java.util.List;

import com.searchinner.model.Article;
import com.searchinner.model.User;
import com.searchinner.viewmodel.ArticleAndCatalog;

public interface IArticleServiceDAO {
	/**
	 * 添加文章
	 * @param article 文章实体类
	 * @return
	 */
	public Article addArticle(Article article);


	/**
	 * 修改文章
	 * @param article 文章实体类
	 * @return
	 */
	public Article updateArticle(Article article);

	/**
	 * 通过id获得文章
	 * @param id
	 * @return
	 */
	public Article getArticle(String id);

	/**
	 * 删除。伪删除
	 * @param id
	 */
	public void deleteArticle(String id);

	/**
	 * 显示给首页。十条
	 * @return
	 */
	public List<Article> getArticles();
	
	/**
	 * 添加或者修改文章
	 * @param aac 页面model。保存页面传过来的参数
	 * @param user 用户
	 * @param isUpdate true：更新    false ：删除
	 * @return
	 */
	public Article addOrUpdateArticle(ArticleAndCatalog aac,User user,boolean isUpdate);
	
	/**
	 * 用户的所有文章
	 * @param userId
	 * @return
	 */
	public List<Article> getArticlesByUserId(String userId);
	
	/**
	 * 用户的草稿箱
	 * @param userId
	 * @return
	 */
	public List<Article> getArticlesDraftByUserId(String userId);
	
	/**
	 * 根据文章id获得文章。文章id是个list
	 * @param articleIds
	 * @return
	 */
	public List<Article> getArticlesByArticleId(List<String> articleIds);
}
