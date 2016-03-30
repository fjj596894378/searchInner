package com.searchinner.service.test;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.searchinner.model.test.Article;

public class TestQueryBean {
	public static final String SOLR_URL = "http://127.0.0.1:8080/solr";

	public static void main(String[] args) throws SolrServerException,
			IOException {
		// http://172.168.63.233:8983/solr/collection1/select?q=description%3A%E6%80%BB%E7%9B%AE%E6%A0%87&facet=true&facet.field=author_s
		HttpSolrServer server = new HttpSolrServer(SOLR_URL);
		server.setMaxRetries(1);
		server.setConnectionTimeout(5000); // 5 seconds to establish TCP

		SolrQuery query = new SolrQuery();
		query.setQuery("articleContent:改革");
		query.setStart(0);
		query.setRows(5);
		//query.setFacet(true);
		//query.addFacetField("author_s");

		QueryResponse response = server.query(query);
		// 搜索得到的结果数
		System.out.println("Find:" + response.getResults().getNumFound());
		// 输出结果
		int iRow = 1;

		// response.getBeans存在BUG,将DocumentObjectBinder引用的Field应该为
		// org.apache.solr.client.solrj.beans.Field
		SolrDocumentList list = response.getResults();
		DocumentObjectBinder binder = new DocumentObjectBinder();
		List<Article> beanList = binder.getBeans(Article.class, list);
		for (Article news : beanList) {
			System.out.println(news.getId());
		}

		for (SolrDocument doc : response.getResults()) {
			System.out.println("----------" + iRow + "------------");
			System.out.println("id: " + doc.getFieldValue("id").toString());
			System.out.println("articleTitle: " + doc.getFieldValue("articleTitle").toString());
			System.out.println("articleContent: " + doc.getFieldValue("articleContent").toString());
			System.out.println("articleAuthor: " + doc.getFieldValue("articleAuthor").toString());
			System.out.println("lastModified: " + doc.getFieldValue("last_modified").toString());
			System.out.println("addTime: " + doc.getFieldValue("first_modified").toString());
			//iRow++;
		}
		/*for (FacetField ff : response.getFacetFields()) {
			System.out.println(ff.getName() + "," + ff.getValueCount() + ","
					+ ff.getValues());
		}*/
	}
}
