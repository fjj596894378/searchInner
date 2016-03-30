package com.searchinner.model.test;

import java.util.Date;

import javax.xml.crypto.Data;

import org.apache.solr.client.solrj.beans.Field;

public class Article {
	

	@Field
	private String id;

	/*@Field
	private String name;*/
	@Field
	private String articleTitle; // 文章标题  +
	@Field
	private String articleContent; // 文章内容  +
	@Field
	private String articleAuthor; // 作者  +
	@Field("last_modifiedtime")
	private String lastModified; // 最后修改时间
	@Field("first_modified")
	private String addTime; // 第一次上传时间 +
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
	
	
}
