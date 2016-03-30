package com.searchinner.service.dao.imp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.CatalogAndArticleRefDAO;
import com.searchinner.model.Article;
import com.searchinner.model.Catalog;
import com.searchinner.model.CatalogAndArticleRef;
import com.searchinner.service.dao.IArticleServiceDAO;
import com.searchinner.service.dao.ICatalogAndArticleRefServiceDAO;
@Service("catalogAndArticleRefServiceDAOImp")
public class CatalogAndArticleRefServiceDAOImp implements ICatalogAndArticleRefServiceDAO{
	private CatalogAndArticleRefDAO catalogAndArticleRefDAOImp;
	private IArticleServiceDAO articleServiceDAOImp;
	@Override
	public CatalogAndArticleRef addCatalogAndArticleRef(CatalogAndArticleRef ref) {
		return catalogAndArticleRefDAOImp.addCatalogAndArticleRef(ref);
	}

	@Override
	public void updateCatalogAndArticleRef(CatalogAndArticleRef ref) {
		catalogAndArticleRefDAOImp.updateCatalogAndArticleRef(ref);
	}

	@Override
	public List<CatalogAndArticleRef> getCatalogAndArticleRef(CatalogAndArticleRef ref) {
		return catalogAndArticleRefDAOImp.getCatalogAndArticleRef(ref);
	}

	@Override
	public void deleteCatalogAndArticleRef(CatalogAndArticleRef ref) {
		catalogAndArticleRefDAOImp.deleteCatalogAndArticleRef(ref);
	}

	@Override
	public List<CatalogAndArticleRef> getCatalogAndArticleRefs() {
		return catalogAndArticleRefDAOImp.getCatalogAndArticleRefs();
	}

	@Override
	public CatalogAndArticleRef addCatalogAndArticleRef(
			List<CatalogAndArticleRef> refList) {
		return catalogAndArticleRefDAOImp.addCatalogAndArticleRef(refList) ;
	}

	@Override
	public List<Article> getArticleBycatalogId(Catalog catalog) {
		List<CatalogAndArticleRef> refList = catalogAndArticleRefDAOImp.getArticleBycatalogId(catalog);
		List<String> articleList= new ArrayList<String>(refList.size());
		for(CatalogAndArticleRef ref : refList){
			articleList.add(ref.getArticleid());
		}
		List<Article> articleRetList = new LinkedList<Article>();
		if(articleList != null && articleList.size() > 0){
			articleRetList = articleServiceDAOImp.getArticlesByArticleId(articleList);
		}
		return articleRetList;
	}

	public CatalogAndArticleRefDAO getCatalogAndArticleRefDAOImp() {
		return catalogAndArticleRefDAOImp;
	}
	@Resource(name="catalogAndArticleRefDAOImp")
	public void setCatalogAndArticleRefDAOImp(
			CatalogAndArticleRefDAO catalogAndArticleRefDAOImp) {
		this.catalogAndArticleRefDAOImp = catalogAndArticleRefDAOImp;
	}
	
	public IArticleServiceDAO getArticleServiceDAOImp() {
		return articleServiceDAOImp;
	}
	@Resource(name="articleServiceDAOImp")
	public void setArticleServiceDAOImp(IArticleServiceDAO articleServiceDAOImp) {
		this.articleServiceDAOImp = articleServiceDAOImp;
	}

}
