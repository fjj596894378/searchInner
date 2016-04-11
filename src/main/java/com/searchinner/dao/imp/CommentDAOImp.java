package com.searchinner.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.CommentDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Article;
import com.searchinner.model.Catalog;
import com.searchinner.model.Comment;

@Repository("commentDAOImp")
public class CommentDAOImp implements CommentDAO{
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Comment addComment(Comment comment) {
		final Comment commentRet = comment;
		final String sql = "insert into learn_comment(commentId,articleId,commentUserId,commentUserName,commentedUserId,commentedUserName,commentcontext,addtime,isdelete,commentPid ) values(SEQ_LEARN_Comment.Nextval,?,?,?,?,?,?,sysdate,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		try{
			this.jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		               PreparedStatement ps = connection.prepareStatement(sql,new String[]{"commentId"});  
		               ps.setString(1, commentRet.getArticleId());  
		               ps.setString(2, commentRet.getCommentUserId()); 
		               ps.setString(3, commentRet.getCommentUserName()); 
		               ps.setString(4, commentRet.getCommentedUserId()); 
		               ps.setString(5, commentRet.getCommentedUserName()); 
		               ps.setString(6, commentRet.getCommentcontext()); 
		               ps.setInt(7, commentRet.getIsDelete()); 
		               ps.setInt(8, commentRet.getCommentPid()); 
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();
		    commentRet.setCommentId( generatedId); //返回id
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("添加评论出错");
		}
		return commentRet;
	}

	@Override
	public Comment getComment(String commentId) {
		return null;
	}

	@Override
	public void deleteComment(String commentId) {
		
	}

	@Override
	public List<Comment> getComments(String aricleId) {
		String sql = "select commentId,articleId,commentUserId,commentUserName,commentedUserId,commentedUserName,commentcontext,t.addtime,t.isdelete,commentPid,u.imagepath  from learn_comment t,learn_user u where t.isdelete=0 and articleId=? and u.username=commentUserId ORDER BY t.addTime asc ";
		List<Comment> commentListRet = new LinkedList<Comment>();
		Object obj[] = { aricleId };
		Comment commentRet = new Comment();
		List list = this.jdbcTemplate.query(sql,obj, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// TODO Auto-generated method stub
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("commentId"));
				comment.setArticleId(rs.getString("articleId"));
				comment.setCommentUserId(rs
						.getString("commentUserId"));
				comment.setCommentUserName(rs.getString("commentUserName"));
				comment.setAddTime(rs.getString("addTime"));
				comment.setIsDelete(rs.getInt("isdelete"));
				comment.setCommentedUserId(rs
						.getString("commentedUserId"));
				comment.setCommentedUserName(rs.getString("commentedUserName"));
				comment.setCommentcontext(rs.getString("commentcontext"));
				comment.setCommentPid(rs.getInt("commentPid"));
				comment.setImagePath(rs.getString("imagepath"));
				return comment;
			}

		});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof Comment){
					commentRet = new Comment();
					commentRet = (Comment)object;
					commentListRet.add(commentRet);
				}
			}
		}
		return commentListRet;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
