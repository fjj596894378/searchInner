package com.searchinner.model;

import java.io.Serializable;

public class Comment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1200897008599328121L;
	private int commentId;//评论id
	private String articleId;// 文章id
	private String commentUserId; //评论者id
	private String commentUserName;//评论者姓名
	private String commentedUserId;	//被评论者id
	
	private String commentedUserName; //被评论者姓名
	private String commentcontext; // 评论 内容
	private String addTime; // 第一次注册时间 +
	private int isDelete; // 0:未删除  1:删除
	private String lastModified; // 最后修改时间
	private String imagePath;// 不存数据库。只用作前台显示。
	private int commentPid;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentUserName() {
		return commentUserName;
	}
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	public String getCommentedUserId() {
		return commentedUserId;
	}
	public void setCommentedUserId(String commentedUserId) {
		this.commentedUserId = commentedUserId;
	}
	public String getCommentedUserName() {
		return commentedUserName;
	}
	public void setCommentedUserName(String commentedUserName) {
		this.commentedUserName = commentedUserName;
	}
	public String getCommentcontext() {
		return commentcontext;
	}
	public void setCommentcontext(String commentcontext) {
		this.commentcontext = commentcontext;
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
	public int getCommentPid() {
		return commentPid;
	}
	public void setCommentPid(int commentPid) {
		this.commentPid = commentPid;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
}
