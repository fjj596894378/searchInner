package com.searchinner.dao;

import java.util.List;

import com.searchinner.model.Comment;

public interface CommentDAO {
	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public Comment addComment( Comment comment);

	

	/**
	 * 通过评论Id获得用户信息
	 * @param commentId
	 * @return
	 */
	public Comment getComment(String commentId);
	

	/**
	 * 删除评论
	 * @param commentName
	 */
	public void deleteComment(String commentId);

	/**
	 * 获得所有评论
	 * @return
	 */
	public List<Comment> getComments(String aricleId);
	
	
	
	
}
