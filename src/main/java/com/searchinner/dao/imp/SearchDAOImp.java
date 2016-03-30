package com.searchinner.dao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Collation;
import org.apache.solr.client.solrj.response.SpellCheckResponse.Correction;
import org.apache.solr.common.SolrDocument;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.searchinner.dao.SearchDAO;
import com.searchinner.exception.MyRuntimeException;
import com.searchinner.model.Article;
import com.searchinner.model.SearchParam;
import com.searchinner.util.ServerFactory;
import com.searchinner.util.Util;
@Repository("searchDAOImp")
public class SearchDAOImp implements SearchDAO{
	private Map<Integer, String> queryType;
	
	/**
	 * spring提供的jdbc操作辅助类
	 */
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Article> querySolrData(SearchParam sp) throws ParseException {
		List<Article> articleList = new ArrayList<Article>(); // sp.getPageCount()
		HttpSolrServer solrClient = ServerFactory.getHttpSolrClient();
		String queryType = Util.getQueryTypeByNumber(sp.getSearchType());
		String queryStr = "";
		for (int i = 0; i < sp.getParamArr().length; i++) {
			queryStr += sp.getParamArr()[i] + ",";
		}
		if(queryStr != null && !queryStr.equals("")){
		  queryStr = queryStr.substring(0,queryStr.length() -1);
		}
		SolrQuery query = new SolrQuery(queryType + ":" + queryStr);
		/*拼写检查 */
		/*query.set("defType","edismax");//加权
		query.set("spellcheck", "true");  
        
     //   query.set("qt", "/spell");  
        query.set("spellcheck.build", "true");//遇到新的检查词，会自动添加到索引里面  
        query.set("spellcheck.count", 5);*/
        /*拼写检查 */
        
		/* 開啟高亮 */
		query.setHighlight(true);// 开启高亮功能
		
		query.addHighlightField("articleTitle");// 高亮字段
		query.addHighlightField("articleContentPre");
		query.addHighlightField("articleAuthor");
		query.addHighlightField("articleTag");
		query.setHighlightSimplePre("<font color='red'>");// 渲染标签
		query.setHighlightSimplePost("</font>");// 渲染标签
		query.setHighlight(true).setHighlightSnippets(1);
		query.setHighlightFragsize(150);
		/* 開啟高亮 */
		// 设置排序
		// 分頁
		query.setStart(0);
		query.setRows(1000); // sp.getPageCount()
		// 分頁
		// facet
		query.setFacet(true);// 设置facet=on
		query.setFacetLimit(100);// 限制facet返回的数量
		query.setFacetMissing(false);// 不统计null的值
		query.setFacetMinCount(1);// 设置返回的数据中每个分组的数据最小值，比如设置为1，则统计数量最小为1，不然不显示

		query.addFacetField(new String[] { "articleTitle", "articleContentPre",
				"articleTag" });// 设置需要facet的字段
		/* 分组开始 */

		QueryResponse response = null;
		try {
			response = solrClient.query(query);
			
		} catch (SolrServerException e) {
			new MyRuntimeException("【查询】出错：SolrServerException");
			e.printStackTrace();
		}
		//查询时间
		System.out.println("查询时间：" + response.getQTime());
		// 搜索得到的结果数
		System.out.println("Find:" + response.getResults().getNumFound());
		if(response.getResults().getNumFound() == 0){
			SpellCheckResponse spellCheckResponse = response.getSpellCheckResponse();
			// 第一种
			if(spellCheckResponse != null){
				List<Collation> collationResults = 	spellCheckResponse.getCollatedResults();
				for(Collation collation : collationResults){
					long numberOfHits = collation.getNumberOfHits();
					System.out.println("推荐词语个数" + numberOfHits);
					List<Correction> misspellingsAndCorrections = collation.getMisspellingsAndCorrections();
					for(Correction correction : misspellingsAndCorrections){
						System.out.println("原始：" + correction.getOriginal() + " 推荐：" + correction.getCorrection());
					}
				}
			}
			
			// 第二种
			/*
			List<Suggestion> suggestions = spellCheckResponse.getSuggestions();
			for(Suggestion suggestion : suggestions){
				int numberFound = suggestion.getNumFound();
				System.out.println("推荐词语个数" + numberFound);
				List<String> alternatives = suggestion.getAlternatives();
				for(String str : alternatives){
					System.out.println(str);
				}
			}
			*/
		}
		// 输出结果
		List<FacetField> facets = response.getFacetFields();// 返回的facet列表  
        for (FacetField facet : facets) {  
            System.out.println(facet.getName());  
            System.out.println("----------------");  
            List<Count> counts = facet.getValues();  
            for (Count countitem : counts) {  
                System.out.println(countitem.getName() + ":"  
                        + countitem.getCount());  
            }  
            System.out.println();  
        }  
        // 根据时间段来获取数据  
        Map<String, Integer> maps = response.getFacetQuery();  
        for (Entry<String, Integer> entry : maps.entrySet()) {  
            System.out.println(entry.getKey() + ":" + entry.getValue());  
        }  
		
		
		Article articleEnty = null;
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 小写的mm表示的是分钟
*/		Map<String, Map<String, List<String>>> highlightMap = response
				.getHighlighting();
		String tmpId = "";
		List<String> titleList = null;
		List<String> contentList = null;
		List<String> authorList = null;
		List<String> articleTagList = null;
		StringBuffer sb = new StringBuffer();
		String tag = null;
		for (SolrDocument doc : response.getResults()) {
			articleEnty = new Article();
			tmpId = doc.getFieldValue("id").toString();
			articleEnty.setId(tmpId);

			/*articleEnty.setLastModified(doc.getFieldValue("last_modifiedtime")
					.toString());*/
			articleEnty.setAddTime(doc.getFieldValue("addTime")
					.toString());
			titleList = highlightMap.get(tmpId).get("articleTitle");
			contentList = highlightMap.get(tmpId).get("articleContentPre");
			authorList = highlightMap.get(tmpId).get("articleAuthor");
			articleTagList = highlightMap.get(tmpId).get("articleTag");
			 
			if (titleList != null && titleList.size() > 0) {
				// 获取并设置高亮的字段title
				articleEnty.setArticleTitle(titleList.get(0));
			} else {
				articleEnty.setArticleTitle(doc.getFieldValue("articleTitle")
						.toString());
			}
		 
			if(doc.getFieldValue("articleAuthorId") !=null){
				articleEnty.setArticleAuthorId(doc.getFieldValue("articleAuthorId")
						.toString());
			}
			
			
			if (contentList != null && contentList.size() > 0) {
				// 获取并设置高亮的字段title
				articleEnty.setArticleContentPre(contentList.get(0));
			} else {
				articleEnty.setArticleContentPre(doc.getFieldValue(
						"articleContentPre").toString());
			}
			if (authorList != null && authorList.size() > 0) {
				// 获取并设置高亮的字段title
				articleEnty.setArticleAuthor(authorList.get(0));
			} else {
				articleEnty.setArticleAuthor(doc.getFieldValue("articleAuthor")
						.toString());
			}
			
			if (articleTagList != null && articleTagList.size() > 0) {
				// 获取并设置高亮的字段title
				articleEnty.setArticleTag(articleTagList.get(0));
			} else {
				if(doc.getFieldValue("articleTag") !=null){
					articleEnty.setArticleTag(doc.getFieldValue("articleTag")
						.toString());
				}
			}
			// 标签“，”处理
			tag = articleEnty.getArticleTag();
			if(tag != null && tag.length() > 0){
				String[]  strArr = tag.split(",");
				for(String str : strArr){
					sb.append(str + "&nbsp;&nbsp;");
				}
			}
			tag = "";
			articleEnty.setArticleTag(sb.toString());
			sb.delete(0,sb.length());
			// 标签“，”处理
			
			articleList.add(articleEnty);
		}
		
		return articleList;
	}

	@Override
	public List<Article> queryIndexData() throws ParseException {
		
		String sql = "select * from (select id,articleTitle,articleContent,articleAuthor,articleAuthorId,lastModified,isdelete,to_char(addTime,'yy-mm-dd hh24:mi:ss') as addTime,articleContentPre,articleTag from learn_article where isdelete=0 and status=0 ORDER BY addTime desc) where rownum < 11 ";
		List<Article> articleListRet = new ArrayList<Article>(10);
		Article articleRet = new Article();
		final StringBuffer sb = new StringBuffer();
		List list = this.jdbcTemplate.query(sql, new RowMapper<Object>() {
			@Override
			public Object mapRow(ResultSet rs, int row) throws SQLException {
				// TODO Auto-generated method stub
				Article article = new Article();
				String tag = null;
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
				article.setArticleAuthorId(rs
						.getString("articleAuthorId"));
				
				article.setArticleTag(rs
						.getString("articleTag"));
				
				// 标签“，”处理
				tag = article.getArticleTag();
				if(tag != null && tag.length() > 0){
					String[]  strArr = tag.split(",");
					for(String str : strArr){
						sb.append(str + "&nbsp;&nbsp;");
					}
				}
				tag = "";
				article.setArticleTag(sb.toString());
				sb.delete(0,sb.length());
				// 标签“，”处理
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

	public Map<Integer, String> getQueryType() {
		return queryType;
	}
	/*@Resource(name = "queryType")*/
	public void setQueryType(Map<Integer, String> queryType) {
		this.queryType = queryType;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Resource(name = "jdbcTemplate")
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	

}
