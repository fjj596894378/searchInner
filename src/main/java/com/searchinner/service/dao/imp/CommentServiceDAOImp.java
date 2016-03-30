package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.CommentDAO;
import com.searchinner.model.Comment;
import com.searchinner.service.dao.ICommentServiceDAO;
@Service("commentServiceDAOImp")
public class CommentServiceDAOImp implements ICommentServiceDAO{
	private CommentDAO commentDAOImp;
	@Override
	public Comment addComment(Comment comment) {
		return commentDAOImp.addComment(comment);
	}

	@Override
	public Comment getComment(String commentId) {
		return commentDAOImp.getComment(commentId);
	}

	@Override
	public void deleteComment(String commentId) {
		commentDAOImp.deleteComment(commentId);
	}

	@Override
	public List<Comment> getComments(String aricleId) {
		return commentDAOImp.getComments(aricleId);
	}

	public CommentDAO getCommentDAOImp() {
		return commentDAOImp;
	}

	@Resource(name="commentDAOImp")
	public void setCommentDAOImp(CommentDAO commentDAOImp) {
		this.commentDAOImp = commentDAOImp;
	}
}
