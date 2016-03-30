package com.searchinner.model;

import java.io.Serializable;

/**
 * 分类模块
 * @author fujj
 *
 */
public class Catalog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6844042844496768537L;
	private int catalogid;	// 分类id 自增。
	private String userName;	// 用户名
	private String catalogName;	// 分类名
	private String addTime;
	private String lastModified;
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
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	
	
}
