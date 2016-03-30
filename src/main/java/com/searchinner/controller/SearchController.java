package com.searchinner.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.searchinner.model.Article;
import com.searchinner.model.SearchParam;
import com.searchinner.service.dao.ISearchService;
import com.searchinner.util.Util;

@Controller
@RequestMapping("/search")
public class SearchController {
	private ISearchService searchServiceDAOImp;
	
	/**
	 * 首页
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/index")
	public ModelAndView index() throws Exception {
		ModelAndView modelView = new ModelAndView();
		List<Article> articleList = searchServiceDAOImp.queryIndexData();
		
		modelView.addObject("articleList",articleList); 
		modelView.addObject("firstInit",1);
		modelView.setViewName("index");
		return modelView;
	}
	
	/**
	 * 查询
	 * @param sp
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/searchData")
	public ModelAndView search(SearchParam sp) throws Exception {
		ModelAndView modelView = new ModelAndView();
		if(sp.getStrParam() != null && "".equals(sp.getStrParam().trim())){
			modelView.setViewName("index");
			return modelView;
		}
		String paramParse = Util.escapeQueryChars(sp.getStrParam());
		sp.setParamArr( paramParse.split(" +"));
		List<Article> articleList = searchServiceDAOImp.querySolrData(sp);
		modelView.addObject("articleList",articleList); 
		modelView.addObject("retSearch", sp.getStrParam());
		modelView.addObject("firstInit",1);
		modelView.setViewName("index");
		return modelView;
	}

	public ISearchService getSearchServiceDAOImp() {
		return searchServiceDAOImp;
	}
	@Resource(name = "searchServiceDAOImp")
	public void setSearchServiceDAOImp(ISearchService searchServiceDAOImp) {
		this.searchServiceDAOImp = searchServiceDAOImp;
	}
}
