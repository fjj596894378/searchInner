package com.searchinner.task;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.searchinner.model.MessageInfo;
import com.searchinner.service.dao.IMessageInfoServiceDAO;
import com.searchinner.util.MessageInfoCache;

@Component("messageInfoTask") 
/*@Lazy(value=false)*/
public class MessageInfoTask {
	private IMessageInfoServiceDAO messageInfoServiceDAOImp;
	private String aricleId;
	private static MessageInfoCache myCache;
	static{
		myCache = MessageInfoCache.getInstance();
	}
	// 早上九点到晚上九点。每30秒触发一次
	/*@Scheduled(cron = "0/30 0/1 9-21 * * ?")*/
	public void getMessageInfos() {
		//MessageInfoCache.getInstance();
		/*System.out.println(MessageInfoCache.getInstance());
		System.out.println(messageInfoServiceDAOImp);
		System.out.println(System.currentTimeMillis() + "测试");*/
		List<MessageInfo> messageList = messageInfoServiceDAOImp.getMessageInfos();
		MessageInfoCache.addCacheInfoToMap(messageList );
	}
	
	public void deleteMessageInfos() {
		//MessageInfoCache.getInstance();
		/*System.out.println(MessageInfoCache.getInstance());
		System.out.println(messageInfoServiceDAOImp);
		System.out.println(System.currentTimeMillis() + "删除");*/
		if(myCache.readCacheMap != null && myCache.readCacheMap.size() > 0){
			for(Map.Entry<Integer, MessageInfo> e: myCache.readCacheMap.entrySet() ){
		        //System.out.println("键:"+e.getKey()+", 值:"+e.getValue());
		        messageInfoServiceDAOImp.deleteMessageInfo(e.getKey());
			}
		}
		myCache.readCacheMap.clear();
		//List<MessageInfo> messageList = 
		//MessageInfoCache.addCacheInfoToMap(messageList );
	}
	

	
	
	public IMessageInfoServiceDAO getMessageInfoServiceDAOImp() {
		return messageInfoServiceDAOImp;
	}
	
	@Resource(name = "messageInfoServiceDAOImp")
	public void setMessageInfoServiceDAOImp(
			IMessageInfoServiceDAO messageInfoServiceDAOImp) {
		this.messageInfoServiceDAOImp = messageInfoServiceDAOImp;
	}

	public String getAricleId() {
		return aricleId;
	}




	public void setAricleId(String aricleId) {
		this.aricleId = aricleId;
	}
}
