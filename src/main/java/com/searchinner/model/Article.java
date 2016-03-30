package com.searchinner.model;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 文章实体。
 * @author fujj
 *
 */
public class Article implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8511222966608672910L;

	
	@Field
	private String id;

	/*@Field
	private String name;*/
	@Field
	private String articleTitle; // 文章标题  +
	@Field
	private String articleContent; // 文章内容  +
	
	private String articleAuthor; // 作者  +
	/*@Field("last_modifiedtime")*/
	private String lastModified; // 最后修改时间
	@Field("addTime")
	private String addTime; // 第一次上传时间 +
	@Field
	private String articleContentPre;//文章内容索引
	private int isDelete; // 0:未删除  1:删除
	@Field
	private String articleTag; //文章标签
	private String articleCatalog; // 文章分类
	private String articleAuthorId;
	private int status; // 状态
	/*@Field
	private String author;*/

	/*@Field
	private String description;*/

	/*@Field("links")
	private List<String> relatedLinks;*/

	public Article() {

	}
	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getArticleContentPre() {
		return articleContentPre;
	}
	public void setArticleContentPre(String articleContentPre) {
		this.articleContentPre = articleContentPre;
	}
	public String getArticleTag() {
		return articleTag;
	}
	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}
	public String getArticleCatalog() {
		return articleCatalog;
	}
	public void setArticleCatalog(String articleCatalog) {
		this.articleCatalog = articleCatalog;
	}
	public String getArticleAuthorId() {
		return articleAuthorId;
	}
	public void setArticleAuthorId(String articleAuthorId) {
		this.articleAuthorId = articleAuthorId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
