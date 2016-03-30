package com.searchinner.service.dao.imp;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.searchinner.dao.SearchDAO;
import com.searchinner.model.Article;
import com.searchinner.model.SearchParam;
import com.searchinner.service.dao.ISearchService;
@Service("searchServiceDAOImp")
public class SearchServiceDAOImp implements ISearchService{

	private SearchDAO searchDAOImp;
	@Override
	public List<Article> querySolrData(SearchParam sp) throws ParseException {
		List<Article> articleList  = searchDAOImp.querySolrData(sp);
		return articleList;
	}
	
	@Cacheable(value="indexDataCache",key="'queryIndex.KEY'")
	public List<Article> queryIndexData() throws ParseException {
		List<Article> articleList  =searchDAOImp.queryIndexData();
		return articleList;
	}
	
	
	public SearchDAO getSearchDAOImp() {
		return searchDAOImp;
	}
	
	@Resource(name="searchDAOImp")
	public void setSearchDAOImp(SearchDAO searchDAOImp) {
		this.searchDAOImp = searchDAOImp;
	}
	
}
