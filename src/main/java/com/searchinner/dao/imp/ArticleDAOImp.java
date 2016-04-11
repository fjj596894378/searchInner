package com.searchinner.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.ArticleDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Article;
import com.searchinner.util.Util;

@Repository("articleDAOImp")
public class ArticleDAOImp implements ArticleDAO {
	/**
	 * spring提供的jdbc操作辅助类
	 */
	private JdbcTemplate jdbcTemplate;

	@Override
	public Article addArticle(Article article) {
		String uuid = Util.getUUID();
		String sql = "insert into learn_article(id,articleTitle,articleContent,articleAuthor,addTime,articleContentPre,articleTag,articleCatalog,articleAuthorId,status) values(?,?,?,?,sysdate,?,?,?,?,?)";
		article.setArticleContentPre(article.getArticleContentPre().substring(0,article.getArticleContentPre().length() <230?article.getArticleContentPre().length(): 230));
		Object obj[] = {
				uuid,
				article.getArticleTitle(),
				article.getArticleContent(),
				article.getArticleAuthor(),
				article.getArticleContentPre().replaceAll("\\s*", ""),
				article.getArticleTag().replaceAll("，", ","),//标签把中文，换成,
				article.getArticleCatalog(),/*分类*/
				article.getArticleAuthorId(),
				article.getStatus()};
		
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("插入成功！");
			article.setId(uuid); // 获得主键
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("添加文章失败" ,e);
		}
		return article;
	}

	@Override
	public Article updateArticle(Article article) {
		String sql = "update learn_article set articleTitle=?,articleContent=?,articleContentPre=?,articleTag=?,articleCatalog=?,status=? where id=?";
		article.setArticleContentPre(article.getArticleContentPre().substring(0,article.getArticleContentPre().length() <230?article.getArticleContentPre().length(): 230));
		
		Object obj[] = {
				article.getArticleTitle(),
				article.getArticleContent(),
				article.getArticleContentPre().replaceAll(" +", ""),
				article.getArticleTag().replaceAll("，", ","),//标签把中文，换成,
				article.getArticleCatalog(),
				article.getStatus(),article.getId()};
		try{
			this.jdbcTemplate.update(sql, obj);
			System.out.println("更新成功！");
			article.setId(article.getId()); // 获得主键
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("更新文章失败" ,e);
		}
		return article;
	}

	@Override
	public Article getArticle(String id) {
		// TODO Auto-generated method stub
		String sql = "select id,articleTitle,articleContent,articleAuthor,lastModified,isdelete,addTime,articleTag,articleCatalog,articleAuthorId,status from learn_article where isdelete=0 and id=?";// "+id;
		Object obj[] = { id };
		Article articleRet = new Article();
		List list = jdbcTemplate.query(sql, obj,
				new RowMapper<Object>() {

					@Override
					public Object mapRow(ResultSet rs, int arg1)
							throws SQLException {
						Article article = new Article();
						article.setId(rs.getString("id"));
						article.setArticleTitle(rs.getString("articleTitle"));
						article.setArticleContent(rs
								.getString("articleContent"));
						article.setArticleAuthor(rs.getString("articleAuthor"));
						article.setLastModified(rs.getString("lastModified"));
						article.setAddTime(rs.getString("addTime"));
						article.setIsDelete(rs.getInt("isdelete"));
						article.setArticleTag(rs.getString("articleTag"));
						article.setArticleCatalog(rs.getString("articleCatalog"));
						article.setArticleAuthorId(rs.getString("articleAuthorId"));
						article.setStatus(rs.getInt("status"));
						
						return article;
					}

				});
		if(list != null && list.size() > 0){
			if(list.get(0) instanceof Article){
				articleRet = new Article();
				articleRet = (Article)list.get(0);
			}
		}
		return articleRet;
	}

	@Override
	public void deleteArticle(String id) {
		String sql = "update learn_article set isdelete =1  where id=?";
		Object obj[] = { id };
		try{
			this.jdbcTemplate.update(sql,obj);
			System.out.println("删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			throw new MyRuntimeException("删除文章失败" ,e);
		}
	}

	@Override
	public List<Article> getArticles() {
		String sql = "select id,articleTitle,articleContent,articleAuthor,lastModified,articleCatalog,isdelete,addTime,articleContentPre from learn_article where isdelete=0 and status=0 ORDER BY addTime desc limit 10 ";
		List<Article> articleListRet = new ArrayList<Article>(10);
		Article articleRet = new Article();
		List list = this.jdbcTemplate.query(sql, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// TODO Auto-generated method stub
				Article article = new Article();
				article.setId(rs.getString("id"));
				article.setArticleTitle(rs.getString("articleTitle"));
				article.setArticleContent(rs
						.getString("articleContent"));
				article.setArticleAuthor(rs.getString("articleAuthor"));
				article.setLastModified(rs.getString("lastModified"));
				article.setAddTime(rs.getString("addTime"));
				article.setIsDelete(rs.getInt("isdelete"));
				article.setArticleContentPre(rs
						.getString("articleContentPre"));
				article.setArticleCatalog(rs.getString("articleCatalog"));
				return article;
			}

		});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof Article){
					articleRet = new Article();
					articleRet = (Article)object;
					articleListRet.add(articleRet);
				}
			}
		}
		return articleListRet;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<Article> getArticlesByUserId(String userId) {
		String sql = "select id,articleTitle,articleAuthor,lastModified,articleCatalog,isdelete,addTime,articleAuthorId from learn_article where isdelete=0 and status=0 and articleAuthorId=? ORDER BY addTime desc ";
		List<Article> articleListRet = new LinkedList<Article>();
		Object obj[] = { userId };
		Article articleRet = new Article();
		List list = this.jdbcTemplate.query(sql,obj, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// TODO Auto-generated method stub
				Article article = new Article();
				article.setId(rs.getString("id"));
				article.setArticleTitle(rs.getString("articleTitle"));
				
				article.setArticleAuthor(rs.getString("articleAuthor"));
				article.setArticleAuthor(rs.getString("articleAuthorId"));
				article.setLastModified(rs.getString("lastModified"));
				article.setAddTime(rs.getString("addTime"));
				article.setIsDelete(rs.getInt("isdelete"));
				
				article.setArticleCatalog(rs.getString("articleCatalog"));
				return article;
			}

		});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof Article){
					articleRet = new Article();
					articleRet = (Article)object;
					articleListRet.add(articleRet);
				}
			}
		}
		return articleListRet;
	}
	
	@Override
	public List<Article> getArticlesByArticleId(List<String> articleids) {
		String sql = "select id,articleTitle,articleAuthor,lastModified,articleCatalog,isdelete,addTime,articleContentPre from learn_article where  isdelete=0 and status=0 and id in(:id) ORDER BY addTime desc";
		List<Article> articleListRet = new LinkedList<Article>();
		Map<String,Object> parameters = new HashMap<String,Object>();
	    parameters.put("id", articleids);
	    
		Article articleRet = new Article();
		List<Map<String,Object>> list = new NamedParameterJdbcTemplate(jdbcTemplate).queryForList(sql, parameters);
		for (Map<String, Object> map : list) {
			articleRet = new Article();
			articleRet.setId(map.get("id").toString());
			articleRet.setArticleTitle(map.get("articleTitle").toString());
			articleRet.setArticleAuthor(map.get("articleAuthor").toString());
			articleRet.setLastModified(map.get("lastModified").toString());
			articleRet.setAddTime(map.get("addTime").toString());
			articleRet.setArticleContentPre(map.get("articleContentPre").toString());
			articleRet.setArticleCatalog(map.get("articleCatalog").toString());
			articleListRet.add(articleRet);
		}
		
		return articleListRet;
	}

	@Override
	public List<Article> getArticlesDraftByUserId(String userId) {
		String sql = "select id,articleTitle,articleAuthor,lastModified,articleCatalog,isdelete,addTime,articleAuthorId from learn_article where isdelete=0 and status=1 and articleAuthorId=? ORDER BY addTime desc ";
		List<Article> articleListRet = new LinkedList<Article>();
		Object obj[] = { userId };
		Article articleRet = new Article();
		List list = this.jdbcTemplate.query(sql,obj, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// TODO Auto-generated method stub
				Article article = new Article();
				article.setId(rs.getString("id"));
				article.setArticleTitle(rs.getString("articleTitle"));
				
				article.setArticleAuthor(rs.getString("articleAuthor"));
				article.setArticleAuthor(rs.getString("articleAuthorId"));
				article.setLastModified(rs.getString("lastModified"));
				article.setAddTime(rs.getString("addTime"));
				article.setIsDelete(rs.getInt("isdelete"));
				
				article.setArticleCatalog(rs.getString("articleCatalog"));
				return article;
			}

		});
		if(list != null && list.size() > 0){
			for (Object object : list) {
				if(object instanceof Article){
					articleRet = new Article();
					articleRet = (Article)object;
					articleListRet.add(articleRet);
				}
			}
		}
		return articleListRet;
	}

}
