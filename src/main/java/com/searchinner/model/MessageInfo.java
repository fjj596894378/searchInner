package com.searchinner.model;

import java.io.Serializable;

public class MessageInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4352055047262186509L;
	private int commentId;//评论id
	private String articleId;// 文章id
	private String commentUserId; //评论者id
	private String commentUserName;//评论者姓名
	private String commentedUserId;	//被评论者id
	
	private String commentedUserName; //被评论者姓名
	private String commentcontext; // 评论 内容
	private String addTime; // 第一次注册时间 +
	private int messageStatus; // 0:未读  1:已读
	private String lastModified; // 最后修改时间
	private int messageType; // 消息类型 0：评论回复
	
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
	
	public String getLastModified() {
		return lastModified;
	}
	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}
	public int getMessageStatus() {
		return messageStatus;
	}
	public void setMessageStatus(int messageStatus) {
		this.messageStatus = messageStatus;
	}
	public int getMessageType() {
		return messageType;
	}
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public Object clone(){
		MessageInfo message = null;
		try {
			message = (MessageInfo) super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return message;
	}
	
}
