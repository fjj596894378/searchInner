package com.searchinner.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.searchinner.model.MessageInfo;

public class MessageInfoCache {
	private static MessageInfoCache cache = null;
	public static ConcurrentMap<String, List<MessageInfo>> cacheMap = new ConcurrentHashMap<String, List<MessageInfo>>(); 
	//已读
	public static ConcurrentMap<Integer, MessageInfo> readCacheMap = new ConcurrentHashMap<Integer, MessageInfo>(); 
	
	private MessageInfoCache(){
		
	}
	public static MessageInfoCache getInstance(){
		if(cache == null){
			synchronized (MessageInfoCache.class) {
				MessageInfoCache mycache = cache;
				if(mycache == null){
					mycache = new MessageInfoCache();
					cache = mycache;
				}
			}
			
		}
		
		return cache;
	}
	
	public static void addCacheInfoToMap(List<MessageInfo> messageList){
		List<MessageInfo> subReplay = new LinkedList<MessageInfo>();
		if(messageList != null && messageList.size() > 0){
			cacheMap.clear();
			for (MessageInfo messageInfo : messageList) {
				if(!readCacheMap.containsKey(messageInfo.getCommentId())){
					if(cacheMap.containsKey(messageInfo.getCommentedUserId())){
						List<MessageInfo> subReplayOfList = cacheMap.get(messageInfo.getCommentedUserId());
						subReplayOfList.add(messageInfo);
						//cacheMap.put(messageInfo.getCommentedUserId(), subReplayOfList);
					}else{
						subReplay = new LinkedList<MessageInfo>();
						subReplay.add(messageInfo);
						cacheMap.put(messageInfo.getCommentedUserId(), subReplay);
					}
				}
				//cacheMap.put(messageInfo.getCommentedUserId(), messageInfo);
			}
			
		}
	}
	
	/** 
     * 根据代码集从缓存里面获取常量数据 
     * getCacheByCodeSet 
     * @param key 
     * @return  
     * List<ConstantData> 
     * @since  1.0.0 
     */  
    public static MessageInfo getMessageInfoList(String key) {  
    	List<MessageInfo> subReplayOfList = cacheMap.get(key); 
    	MessageInfo  messageRet = null;
    	if(subReplayOfList != null && subReplayOfList.size() > 0){
    		if(subReplayOfList.size() == 1){
    			messageRet = subReplayOfList.remove(0);
    			cacheMap.remove(key);
    		}else{
    			messageRet = subReplayOfList.remove(0);
    			System.out.println(cacheMap);
    		}
    		readCacheMap.put(messageRet.getCommentId(), messageRet);
    	}
    		
    	return messageRet;
    } 
    
	public static void main(String[] args) {
		MessageInfoCache.getInstance();
		List<MessageInfo> messageList = new LinkedList<MessageInfo>();
		MessageInfo info = new MessageInfo();
		for (int j = 0; j < 30; j++) {
			info = new MessageInfo();
			info.setArticleId("articleid" + j);
			info.setCommentedUserId(new Random().nextInt(3) + "");
			messageList.add(info);
		}
		
		MessageInfoCache.addCacheInfoToMap(messageList );
		
		System.out.println(MessageInfoCache.cacheMap);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<100;++i){	
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(MessageInfoCache.cacheMap.containsKey(1));
				}
			}
		}).start();
		System.out.println(MessageInfoCache.getMessageInfoList(1 + ""));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MessageInfoCache.cacheMap.clear();
				System.out.println("删除了");
			}
		}).start();
	}
	
	/*public MessageInfo get
	
	public Map<String, MessageInfo> getCacheMap() {
		return cacheMap;
	}

	public void setCacheMap(Map<String, MessageInfo> cacheMap) {
		this.cacheMap = cacheMap;
	}*/
	
	
}
