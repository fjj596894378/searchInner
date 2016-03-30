package com.searchinner.model;

import java.io.Serializable;

/**
 * 用户信息表
 * @author fujj
 *
 */
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3269838206512794140L;
	
	private String realName; // 用户真实姓名
	private String userName;	// 登录名
	private String passWord;	// 密码
	private String addTime; // 第一次注册时间 +
	private int isDelete; // 0:未删除  1:删除
	private String lastModified; // 最后修改时间
	private String remember;// 下次自动登录
	private int defaultCatalogid;//默认分类id
	private String imagePath; // 头像路径
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
	public int getDefaultCatalogid() {
		return defaultCatalogid;
	}
	public void setDefaultCatalogid(int defaultCatalogid) {
		this.defaultCatalogid = defaultCatalogid;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
