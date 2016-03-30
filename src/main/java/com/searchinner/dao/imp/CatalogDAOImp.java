package com.searchinner.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.CatalogDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Catalog;
@Repository("catalogDAOImp")
public class CatalogDAOImp implements CatalogDAO{
	/**
	 * spring提供的jdbc操作辅助类
	 */
	private JdbcTemplate jdbcTemplate;
	@Override
	public Catalog addCatalog(Catalog catalog) {
		final Catalog catalogRet = catalog;
		final String sql = "insert into learn_catalog(catalogid,userName,catalogName,addTime) values(SEQ_learn_catalog.Nextval,?,?,sysdate)";
		
		KeyHolder keyHolder = new GeneratedKeyHolder();  
		try{
			this.jdbcTemplate.update(new PreparedStatementCreator() {  
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {  
		               PreparedStatement ps = connection.prepareStatement(sql,new String[]{"catalogid"});  
		               ps.setString(1, catalogRet.getUserName());  
		               ps.setString(2, catalogRet.getCatalogName());  
		               return ps;  
		        }  
		    }, keyHolder);  
		      
		    int generatedId = keyHolder.getKey().intValue();
		    catalogRet.setCatalogid( generatedId); //返回id
		}catch(Exception e){
			throw new MyRuntimeException("添加个人分类出错");
		}
		return catalogRet;
	}

	@Override
	public Catalog updateCatalog(Catalog catalog) {
		String sql = "update learn_catalog set catalogName=? where catalogid=?";
		Object obj[] = {
				catalog.getCatalogName(),
				catalog.getCatalogid()};
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("更新分类成功！");
		}catch(Exception e){
			throw new MyRuntimeException("更新分类失败" ,e);
		}
		return catalog;
	}

	@Override
	public Catalog getCatalog(String id) {
		String sql = "select catalogid,userName,catalogName,addTime,lastModified from learn_catalog where id=?";
		Object obj[] = { id };
		Catalog catalogRet = new Catalog();
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Catalog catalog = new Catalog();
						catalog.setCatalogid(rs.getInt("catalogid"));
						catalog.setUserName(rs.getString("userName"));
						catalog.setCatalogName(rs
								.getString("catalogName"));
						catalog.setAddTime(rs.getString("addTime")); 
						catalog.setLastModified(rs.getString("lastModified")); 
						return catalog;
					}

				});
		if(list != null && list.size() > 0){
			if(list.get(0) instanceof Catalog){
				catalogRet = new Catalog();
				catalogRet = (Catalog)list.get(0);
			}
		}
		return catalogRet;
	}

	@Override
	public void deleteCatalog(String id) {
		
	}

	@Override
	public List<Catalog> getCatalogs(String id) {
		String sql = "select catalogid,userName,catalogName,addTime,lastModified from learn_catalog where userName=?";
		Object obj[] = { id };
		List<Catalog> catalogListRet = new ArrayList<Catalog>();
		Catalog catalogRet = new Catalog();
		List list = jdbcTemplate.query(sql,obj,
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Catalog catalog = new Catalog();
						catalog.setCatalogid(rs.getInt("catalogid"));
						catalog.setUserName(rs.getString("userName"));
						catalog.setCatalogName(rs
								.getString("catalogName"));
						catalog.setAddTime(rs.getString("addTime")); 
						catalog.setLastModified(rs.getString("lastModified")); 
						return catalog;
					}

				});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof Catalog){
					catalogRet = new Catalog();
					catalogRet = (Catalog)object;
					catalogListRet.add(catalogRet);
				}
			}
		}
	 
		return catalogListRet;
	}

	
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
}
