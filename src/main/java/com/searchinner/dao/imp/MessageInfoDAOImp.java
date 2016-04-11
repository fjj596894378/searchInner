package com.searchinner.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.MessageInfoDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Comment;
import com.searchinner.model.MessageInfo;
@Repository("messageInfoDAOImp")
public class MessageInfoDAOImp implements MessageInfoDAO {
	private JdbcTemplate jdbcTemplate;
	@Override
	public MessageInfo addMessageInfo(MessageInfo messageInfo) {
		final MessageInfo messageInfoRet = messageInfo;
		final String sql = "insert into LEARN_Message(commentId,articleId,commentUserId,commentUserName,commentedUserId,commentedUserName,commentcontext,addtime,messageStatus,messageType ) values(SEQ_LEARN_Message.Nextval,?,?,?,?,?,?,sysdate,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		try{
			this.jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		               PreparedStatement ps = connection.prepareStatement(sql,new String[]{"commentId"});  
		               ps.setString(1, messageInfoRet.getArticleId());  
		               ps.setString(2, messageInfoRet.getCommentUserId()); 
		               ps.setString(3, messageInfoRet.getCommentUserName()); 
		               ps.setString(4, messageInfoRet.getCommentedUserId()); 
		               ps.setString(5, messageInfoRet.getCommentedUserName()); 
		               ps.setString(6, messageInfoRet.getCommentcontext()); 
		               ps.setInt(7, messageInfoRet.getMessageStatus()); 
		               ps.setInt(8, messageInfoRet.getMessageType()); 
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();
		    messageInfoRet.setCommentId( generatedId); //返回id
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("添加消息出错");
		}
		return messageInfoRet;
	}

	@Override
	public MessageInfo getMessageInfo(String messageInfoId) {
		
		return null;
	}

	/**
	 * 设置成已读
	 */
	@Override
	public void deleteMessageInfo(int commentId) {
		String sql = "update LEARN_Message set messageStatus=? where commentId=?";
		Object obj[] = {
				1,commentId};
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("更新消息"+commentId + "状态为已读！");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("更新消息状态为已读失败" ,e);
		}
	}

	@Override
	public List<MessageInfo> getMessageInfos() {
		String sql = "select commentId,articleId,commentUserId,commentUserName,commentedUserId,commentedUserName,commentcontext, messageStatus,messageType from LEARN_MESSAGE t where  t.messagetype = 0 and t.messagestatus=0";
		List<MessageInfo> messageInfoListRet = new LinkedList<MessageInfo>();
		//Object obj[] = { aricleId };
		MessageInfo messageInfoRet = new MessageInfo();
		List list = this.jdbcTemplate.query(sql, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				MessageInfo messageIn = new MessageInfo();
				messageIn.setCommentId(rs.getInt("commentId"));
				messageIn.setArticleId(rs.getString("articleId"));
				messageIn.setCommentUserId(rs
						.getString("commentUserId"));
				messageIn.setCommentUserName(rs.getString("commentUserName"));
				messageIn.setCommentedUserId(rs
						.getString("commentedUserId"));
				messageIn.setCommentedUserName(rs.getString("commentedUserName"));
				messageIn.setCommentcontext(rs.getString("commentcontext"));
				messageIn.setMessageStatus(rs.getInt("messageStatus"));
				messageIn.setMessageType(rs.getInt("messageType"));
				return messageIn;
			}

		});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof MessageInfo){
					messageInfoRet = new MessageInfo();
					messageInfoRet = (MessageInfo)object;
					messageInfoListRet.add(messageInfoRet);
				}
			}
		}
		return messageInfoListRet;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
}
