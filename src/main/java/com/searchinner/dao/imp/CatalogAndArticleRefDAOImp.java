package com.searchinner.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.CatalogAndArticleRefDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Catalog;
import com.searchinner.model.CatalogAndArticleRef;
@Repository("catalogAndArticleRefDAOImp")

public class CatalogAndArticleRefDAOImp implements CatalogAndArticleRefDAO {
	/**
	 * spring提供的jdbc操作辅助类
	 */
	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public CatalogAndArticleRef addCatalogAndArticleRef(CatalogAndArticleRef ref) {
		String sql = "insert into learn_catalog_article(catalogid,userName,articleid) values(?,?,?)";
		Object obj[] = {
				ref.getCatalogid(),
				ref.getUserName(),
				ref.getArticleid()};
		try{
			this.jdbcTemplate.update(sql, obj);
		}catch(Exception e){
			throw new MyRuntimeException("添加文章关联失败",e);
		}
		return ref;
	}

	@Override
	public void updateCatalogAndArticleRef(CatalogAndArticleRef ref) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CatalogAndArticleRef> getCatalogAndArticleRef(CatalogAndArticleRef ref) {
		String sql = "select catalogid,userName,articleid from learn_catalog_article where userName=? and articleid=?";
		Object obj[] = {
				ref.getUserName(),
				ref.getArticleid()};
		CatalogAndArticleRef refRet = new CatalogAndArticleRef();
		List<CatalogAndArticleRef> refListRet = new LinkedList<CatalogAndArticleRef>();
		List list = this.jdbcTemplate.query(sql,obj, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				CatalogAndArticleRef ref = new CatalogAndArticleRef();
				ref.setCatalogid(rs.getInt("catalogid"));
				ref.setUserName(rs
						.getString("userName"));
				ref.setArticleid(rs.getString("articleid"));
				return ref;
			}

		});
		
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof CatalogAndArticleRef){
					refRet = new CatalogAndArticleRef();
					refRet = (CatalogAndArticleRef)object;
					refListRet.add(refRet);
				}
			}
		}
		return refListRet;
	}

	@Override
	public void deleteCatalogAndArticleRef(CatalogAndArticleRef ref) {
		String sql = "delete from learn_catalog_article where articleid=?";
		Object obj[] = {
				ref.getArticleid()};
		try{
			this.jdbcTemplate.update(sql, obj);
		}catch(Exception e){
			throw new MyRuntimeException("删除文章关联失败");
		}
	}

	@Override
	public List<CatalogAndArticleRef> getCatalogAndArticleRefs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatalogAndArticleRef addCatalogAndArticleRef(
			List<CatalogAndArticleRef> refList) {
		List<Object[]> batch = new ArrayList<Object[]>(refList.size());
	    for (CatalogAndArticleRef ref : refList) {
	      Object[] values = new Object[] {
	    		  ref.getCatalogid(),
	    		  ref.getUserName(),
	    		  ref.getArticleid()};
	      batch.add(values);
	    }
	    try{
    	jdbcTemplate.batchUpdate(
    	        "insert into learn_catalog_article(catalogid,userName,articleid) values(?,?,?)",
    	        batch);
	    }catch(Exception e){
	    	throw new MyRuntimeException("添加文章关联失败（list）");
	    }
		return refList.get(0);
	}

	@Override
	public List<CatalogAndArticleRef> getArticleBycatalogId(Catalog catalog) {
		String sql = "select catalogid,userName,articleid from learn_catalog_article where userName=? and catalogid=?";
		Object obj[] = {
				catalog.getUserName(),
				catalog.getCatalogid()};
		CatalogAndArticleRef refRet = new CatalogAndArticleRef();
		List<CatalogAndArticleRef> refListRet = new LinkedList<CatalogAndArticleRef>();
		List list = this.jdbcTemplate.query(sql,obj, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				CatalogAndArticleRef ref = new CatalogAndArticleRef();
				ref.setCatalogid(rs.getInt("catalogid"));
				ref.setUserName(rs
						.getString("userName"));
				ref.setArticleid(rs.getString("articleid"));
				return ref;
			}

		});
		
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof CatalogAndArticleRef){
					refRet = new CatalogAndArticleRef();
					refRet = (CatalogAndArticleRef)object;
					refListRet.add(refRet);
				}
			}
		}
		return refListRet;
	}

}
