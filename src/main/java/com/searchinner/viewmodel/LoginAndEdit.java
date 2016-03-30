package com.searchinner.viewmodel;

import java.io.Serializable;

public class LoginAndEdit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -586323948721919162L;
	private String realName; // 用户真实姓名
	private String userName;	// 登录名
	private String passWord;	// 密码
	private String addTime; // 第一次注册时间 +
	private int isDelete; // 0:未删除  1:删除
	private String lastModified; // 最后修改时间
	private String remember;// 下次自动登录
	private String articleTitle; // 文章标题  +
	private String articleContent; // 文章内容  +
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
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
	public String getRemember() {
		return remember;
	}
	public void setRemember(String remember) {
		this.remember = remember;
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
	@Override
	public String toString() {
		return "LoginAndEdit [realName=" + realName + ", userName=" + userName
				+ ", passWord=" + passWord + ", addTime=" + addTime
				+ ", isDelete=" + isDelete + ", lastModified=" + lastModified
				+ ", remember=" + remember + ", articleTitle=" + articleTitle
				+ ", articleContent=" + articleContent + "]";
	}
	
	
}
