package com.searchinner.dao;

import java.text.ParseException;
import java.util.List;

import com.searchinner.model.Article;
import com.searchinner.model.SearchParam;

public interface SearchDAO {
	/**
	 * 搜索
	 * @param sp 参数
	 * @return
	 * @throws ParseException
	 */
	public List<Article> querySolrData(SearchParam sp) throws ParseException ;
	//获得最新的十条
	public List<Article> queryIndexData() throws ParseException ;
}
