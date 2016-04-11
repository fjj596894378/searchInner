package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.CommentDAO;
import com.searchinner.model.Comment;
import com.searchinner.model.MessageInfo;
import com.searchinner.service.dao.ICommentServiceDAO;
import com.searchinner.service.dao.IMessageInfoServiceDAO;
import com.searchinner.util.Util;
@Service("commentServiceDAOImp")
public class CommentServiceDAOImp implements ICommentServiceDAO{
	private CommentDAO commentDAOImp;
	private IMessageInfoServiceDAO messageInfoServiceDAOImp;
	@Override
	public Comment addComment(Comment comment) {
		Comment commentRet = commentDAOImp.addComment(comment);
		MessageInfo info = Util.commentToMessage(comment);
		info.setMessageStatus(0);
		info.setMessageType(0);
		messageInfoServiceDAOImp.addMessageInfo(info);
		return commentRet;
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

	public IMessageInfoServiceDAO getMessageInfoServiceDAOImp() {
		return messageInfoServiceDAOImp;
	}
	
	@Resource(name="messageInfoServiceDAOImp")
	public void setMessageInfoServiceDAOImp(
			IMessageInfoServiceDAO messageInfoServiceDAOImp) {
		this.messageInfoServiceDAOImp = messageInfoServiceDAOImp;
	}
	
	
}
