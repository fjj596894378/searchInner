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

import com.searchinner.dao.DevelopDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.DevelopLink;
@Repository("developDAOImp")
public class DevelopDAOImp implements DevelopDAO{
	private JdbcTemplate jdbcTemplate;
	@Override
	public DevelopLink addDevelopLink(DevelopLink developLink) {
		final DevelopLink developLinkRet = developLink;
		final String sql = "insert into LEARN_developSite(siteId,siteaddress,siteusername,sitepassword) values(SEQ_LEARN_developSite.Nextval,?,?,?)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		try{
			this.jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		               PreparedStatement ps = connection.prepareStatement(sql,new String[]{"siteId"});  
		               ps.setString(1, developLinkRet.getSiteaddress());  
		               ps.setString(2, developLinkRet.getSiteusername()); 
		               ps.setString(3, developLinkRet.getSitepassword()); 
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();
		    developLinkRet.setSiteId( generatedId); //返回id
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("添加服务器信息出错");
		}
		return developLinkRet;
	}

	@Override
	public DevelopLink updateDevelopLink(DevelopLink developLink) {
		String sql = "update LEARN_developSite set sitepassword=? where siteaddress=? and siteusername=?";
		/*String password = user.getUserName() + user.getPassWord();
		password = Util.encrypt_MD5(password);*/
		Object obj[] = {
				developLink.getSitepassword(),
				developLink.getSiteaddress(),
				developLink.getSiteusername()};
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("更新成功！");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("更新服务器信息失败" ,e);
		}
		return developLink;
	}

	@Override
	public DevelopLink getDevelopLink(int id) {
		String sql = "select siteId,siteaddress,siteusername,sitepassword,lastmodified from LEARN_developSite where  siteId=?";// "+id;
		Object obj[] = {id };
		DevelopLink developLinkRet = null;
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						DevelopLink model = new DevelopLink();
						model.setSiteId(rs.getInt("siteId"));
						model.setSiteaddress(rs
								.getString("siteaddress"));
						model.setSiteusername(rs.getString("siteusername"));
						model.setSitepassword(rs.getString("sitepassword"));
						model.setLastmodified(rs.getString("lastmodified"));
						return model;
					}

				});
		if(list != null && list.size() > 0){
			developLinkRet = (DevelopLink) list.get(0);
		}
		return developLinkRet;
	}

	@Override
	public List<DevelopLink> getDevelopLinks() {
		String sql = "select  siteId,siteaddress,siteusername from LEARN_developSite";// "+id;
		DevelopLink developLinkRet = null;
		List<DevelopLink> developLinkList = new LinkedList<DevelopLink>();
		List list = jdbcTemplate.query(sql, 
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						DevelopLink model = new DevelopLink();
						model.setSiteId(rs.getInt("siteId"));
						model.setSiteaddress(rs
								.getString("siteaddress"));
					model.setSiteusername(rs.getString("siteusername"));
					/*		model.setSitepassword(rs.getString("sitepassword"));
						model.setLastmodified(rs.getString("lastmodified"));*/
						return model;
					}

				});
	
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof DevelopLink){
					developLinkRet = new DevelopLink();
					developLinkRet = (DevelopLink)object;
					developLinkList.add(developLinkRet);
				}
			}
		}
		return developLinkList;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<DevelopLink> getDevelopLinkInfoByAddress(String address) {
		String sql = "select  siteId,siteaddress,siteusername,sitepassword from LEARN_developSite where siteaddress =?";// "+id;
		DevelopLink developLinkRet = null;
		Object obj[] = {address };
		List<DevelopLink> developLinkList = new LinkedList<DevelopLink>();
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						DevelopLink model = new DevelopLink();
						model.setSiteId(rs.getInt("SITEID"));
						model.setSiteaddress(rs
								.getString("SITEADDRESS"));
						model.setSiteusername(rs.getString("SITEUSERNAME"));
						model.setSitepassword(rs.getString("SITEPASSWORD"));
						/*model.setLastmodified(rs.getString("lastmodified"));*/
						return model;
					}

				});
	
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof DevelopLink){
					developLinkRet = new DevelopLink();
					developLinkRet = (DevelopLink)object;
					developLinkList.add(developLinkRet);
				}
			}
		}
		return developLinkList;
	}

	@Override
	public DevelopLink getPasswordByUserName(DevelopLink developLink) {
		String sql = "select  siteId,siteaddress,siteusername,sitepassword from LEARN_developSite where siteaddress =? and siteusername =?";// "+id;
		DevelopLink developLinkRet = null;
		Object obj[] = {developLink.getSiteaddress(),developLink.getSiteusername() };// IP地址 + 登录名
		List<DevelopLink> developLinkList = new LinkedList<DevelopLink>();
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						DevelopLink model = new DevelopLink();
						model.setSiteId(rs.getInt("SITEID"));
						model.setSiteaddress(rs
								.getString("SITEADDRESS"));
						model.setSiteusername(rs.getString("SITEUSERNAME"));
						model.setSitepassword(rs.getString("SITEPASSWORD"));
						/*model.setLastmodified(rs.getString("lastmodified"));*/
						return model;
					}

				});
	
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof DevelopLink){
					developLinkRet = new DevelopLink();
					developLinkRet = (DevelopLink)object;
				}
			}
		}
		return developLinkRet;
	}
}
