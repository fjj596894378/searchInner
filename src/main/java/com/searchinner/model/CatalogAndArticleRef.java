package com.searchinner.model;

import java.io.Serializable;

public class CatalogAndArticleRef implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8549993690400903937L;
	private int catalogid;		//分类id
	private String userName;	//用户名
	private String articleid;	// 文章id
	public int getCatalogid() {
		return catalogid;
	}
	public void setCatalogid(int catalogid) {
		this.catalogid = catalogid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getArticleid() {
		return articleid;
	}
	public void setArticleid(String articleid) {
		this.articleid = articleid;
	}
	
}
