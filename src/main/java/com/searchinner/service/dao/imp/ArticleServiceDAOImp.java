package com.searchinner.service.dao.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.searchinner.dao.ArticleDAO;
import com.searchinner.model.Article;
import com.searchinner.model.CatalogAndArticleRef;
import com.searchinner.model.User;
import com.searchinner.service.dao.IArticleServiceDAO;
import com.searchinner.service.dao.ICatalogAndArticleRefServiceDAO;
import com.searchinner.viewmodel.ArticleAndCatalog;
@Service("articleServiceDAOImp")
public class ArticleServiceDAOImp implements IArticleServiceDAO{

	private ArticleDAO articleDAOImp;
	private ICatalogAndArticleRefServiceDAO catalogAndArticleRefServiceDAOImp;
	
	@CacheEvict(value = "indexDataCache", key="'queryIndex.KEY'",allEntries = true) 
	public Article addArticle(Article article) {
		return articleDAOImp.addArticle(article);
	}

	@Override
	public Article updateArticle(Article article) {
		return articleDAOImp.updateArticle(article);
	}

	@Override
	public Article getArticle(String id) {
		return articleDAOImp.getArticle(id);
	}
	
	@CacheEvict(value = "indexDataCache", key="'queryIndex.KEY'",allEntries = true) 
	public void deleteArticle(String id) {
		articleDAOImp.deleteArticle(id);
	}

	@Override
	public List<Article> getArticles() {
		return articleDAOImp.getArticles();
	}

	public ArticleDAO getArticleDAOImp() {
		return articleDAOImp;
	}

	@Resource(name="articleDAOImp")
	public void setArticleDAOImp(ArticleDAO articleDAOImp) {
		this.articleDAOImp = articleDAOImp;
	}
	@CacheEvict(value = "indexDataCache", key="'queryIndex.KEY'",allEntries = true,condition="#articleAndCatalog.getIsdraft() == 0 ")  
	public Article addOrUpdateArticle(ArticleAndCatalog articleAndCatalog,User user,boolean isUpdate) {
		CatalogAndArticleRef catalogAndArticleRef = new CatalogAndArticleRef();
		List<CatalogAndArticleRef> catalogAndArticleRefList = new ArrayList<CatalogAndArticleRef>();
		Article article = new Article();
		if(articleAndCatalog.getIsdraft() == 1){
			article.setStatus(1);
		}
		article.setArticleAuthor(user.getRealName());
		article.setArticleAuthorId(user.getUserName());
		String removeStr = null;
		String catalogIdInfo = null;
		String[] strArr = null;
		if(articleAndCatalog.getArticleCatalog() != null && articleAndCatalog.getArticleCatalog().length() > 0){
			removeStr = articleAndCatalog.getArticleCatalog().substring(0, articleAndCatalog.getArticleCatalog().length()-1);
			catalogIdInfo = articleAndCatalog.getCatalogId();
		}else{
			articleAndCatalog.setArticleCatalog("默认分类");
			removeStr = "默认分类";
			articleAndCatalog.setCatalogId(user.getDefaultCatalogid() + "");
		}
		
		article.setArticleCatalog(removeStr);
		article.setArticleTitle(articleAndCatalog.getArticleTitle());
		article.setArticleContent(articleAndCatalog.getArticleContent());
		article.setArticleContentPre(articleAndCatalog.getArticleContentPre().replaceAll("\\s*",""));
		String[]  tagArr = articleAndCatalog.getArticleTag().split(",");
		/*for(String str : tagArr){
			articleTagList.add(str);
		}*/
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tagArr.length; i++) {
			if(i > 4){
				break;
			}
			sb.append(tagArr[i] + ",");
		}
		String dealTag = sb.toString();
		// String dealTag = articleAndCatalog.getArticleTag();
		if(dealTag != null && !dealTag.replaceAll("\\s*", "").equals("")){
			dealTag = dealTag.replaceAll("\\s*", "");
			if(dealTag.endsWith(",") || dealTag.endsWith("，")){
				dealTag = dealTag.substring(0,dealTag.length() -1);
			}
		}
		article.setArticleTag(dealTag);
		Article articleRet = null;
		if(isUpdate){
			// 更新
			article.setId(articleAndCatalog.getArticleId());
			articleRet = this.updateArticle(article);
		}else{
			// 添加
			articleRet = this.addArticle(article);
		}
		
		/**************CatalogAndArticleRef**************************/
		if(isUpdate){
			// 如果是更新。把之前的关联删掉再添加
			catalogAndArticleRef = new CatalogAndArticleRef();
			catalogAndArticleRef.setArticleid(articleRet.getId());
			catalogAndArticleRefServiceDAOImp.deleteCatalogAndArticleRef(catalogAndArticleRef);
		}
		// 1.去掉最后一个,
		// 2.根据,把他们拆分成数组
		
		if(catalogIdInfo != null){
			strArr = catalogIdInfo.substring(0, catalogIdInfo.length()-1).split(",");
		}
		if(strArr != null && strArr.length > 0){
			for(String str :strArr){
				catalogAndArticleRef = new CatalogAndArticleRef();
				catalogAndArticleRef.setCatalogid(Integer.valueOf(str.substring(8)));
				catalogAndArticleRef.setUserName(user.getUserName());
				catalogAndArticleRef.setArticleid(articleRet.getId());
				catalogAndArticleRefList.add(catalogAndArticleRef);
			}
			catalogAndArticleRefServiceDAOImp.addCatalogAndArticleRef(catalogAndArticleRefList);
		}else{
			catalogAndArticleRef.setCatalogid(user.getDefaultCatalogid());
			catalogAndArticleRef.setUserName(user.getUserName());
			catalogAndArticleRef.setArticleid(articleRet.getId());
			catalogAndArticleRefServiceDAOImp.addCatalogAndArticleRef(catalogAndArticleRef);
		}
		/**************CatalogAndArticleRef**************************/
		return articleRet;
	}
	
	public ICatalogAndArticleRefServiceDAO getCatalogAndArticleRefServiceDAOImp() {
		return catalogAndArticleRefServiceDAOImp;
	}
	@Resource(name = "catalogAndArticleRefServiceDAOImp")
	public void setCatalogAndArticleRefServiceDAOImp(
			ICatalogAndArticleRefServiceDAO catalogAndArticleRefServiceDAOImp) {
		this.catalogAndArticleRefServiceDAOImp = catalogAndArticleRefServiceDAOImp;
	}

	@Override
	public List<Article> getArticlesByUserId(String userId) {
		
		return articleDAOImp.getArticlesByUserId(userId);
	}

	@Override
	public List<Article> getArticlesByArticleId(List<String> articleIds) {
		return articleDAOImp.getArticlesByArticleId(articleIds);
	}

	@Override
	public List<Article> getArticlesDraftByUserId(String userId) {
		return articleDAOImp.getArticlesDraftByUserId(userId);
	}
}
