package com.searchinner.viewmodel;

import java.io.Serializable;

public class ArticleAndCatalog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3090125895949590558L;
	private String articleTitle; // 文章标题  +
	private String articleContent; // 文章内容  +
	
	private String articleAuthor; // 作者  +
	private String articleContentPre;//文章内容索引
	private String articleTag; //文章标签
	private String articleCatalog; // 文章分类
	private String articleId; // 文章id
	private String catalogId;
	private String catalogName;	// 分类名
	private int isdraft; // 如果是0,就表示发表。是1，表示存草稿
	
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
	public String getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public int getIsdraft() {
		return isdraft;
	}
	public void setIsdraft(int isdraft) {
		this.isdraft = isdraft;
	}
	
}
