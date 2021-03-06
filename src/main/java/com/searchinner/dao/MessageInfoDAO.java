package com.searchinner.dao;

import java.util.List;

import com.searchinner.model.MessageInfo;

public interface MessageInfoDAO {
	/**
	 * 添加消息
	 * @param messageInfo
	 * @return
	 */
	public MessageInfo addMessageInfo( MessageInfo messageInfo);

	

	/**
	 * 通过消息Id获得消息信息
	 * @param messageInfoId
	 * @return
	 */
	public MessageInfo getMessageInfo(String messageInfoId);
	

	/**
	 * 更新状态为已读
	 * @param messageInfoName
	 */
	public void deleteMessageInfo(int messageInfoId);

	/**
	 * 获得所有消息
	 * @return
	 */
	public List<MessageInfo> getMessageInfos();
	
}
