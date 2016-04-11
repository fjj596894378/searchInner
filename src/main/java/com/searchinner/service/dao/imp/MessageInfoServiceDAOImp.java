package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.searchinner.dao.CommentDAO;
import com.searchinner.dao.MessageInfoDAO;
import com.searchinner.model.MessageInfo;
import com.searchinner.service.dao.IMessageInfoServiceDAO;
@Service("messageInfoServiceDAOImp")
public class MessageInfoServiceDAOImp implements IMessageInfoServiceDAO {
	private MessageInfoDAO messageInfoDAOImp;
	@Override
	public MessageInfo addMessageInfo(MessageInfo messageInfo) {
		return messageInfoDAOImp.addMessageInfo(messageInfo);
	}

	@Override
	public MessageInfo getMessageInfo(String messageInfoId) {
		return messageInfoDAOImp.getMessageInfo(messageInfoId);
	}

	@Override
	public void deleteMessageInfo(int messageInfoId) {
		messageInfoDAOImp.deleteMessageInfo(messageInfoId);

	}

	public List<MessageInfo> getMessageInfos() {
		return messageInfoDAOImp.getMessageInfos();
	}

	public MessageInfoDAO getMessageInfoDAOImp() {
		return messageInfoDAOImp;
	}
	@Resource(name="messageInfoDAOImp")
	public void setMessageInfoDAOImp(MessageInfoDAO messageInfoDAOImp) {
		this.messageInfoDAOImp = messageInfoDAOImp;
	}

}
