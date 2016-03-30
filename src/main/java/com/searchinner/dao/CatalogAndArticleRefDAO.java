package com.searchinner.dao;

import java.util.List;

import com.searchinner.model.Catalog;
import com.searchinner.model.CatalogAndArticleRef;

public interface CatalogAndArticleRefDAO {
	/**
	 * 添加“分类和文章”的映射关系
	 * @param ref ”分类和文章“实体类
	 * @return
	 */
	public CatalogAndArticleRef addCatalogAndArticleRef( CatalogAndArticleRef ref);

	/**
	 * 修改 “文章和分类”
	 * @param ref
	 */
	public void updateCatalogAndArticleRef(CatalogAndArticleRef ref);

	/**
	 * 获得当前用户当前分类下的所有文章
	 * @param ref
	 * @return
	 */
	public List<CatalogAndArticleRef> getCatalogAndArticleRef(CatalogAndArticleRef ref);

	/**
	 * 删除文章映射关系。删除对应的那条文章
	 * @param ref
	 */
	public void deleteCatalogAndArticleRef(CatalogAndArticleRef ref);

	/**
	 * 获得所有的文章和分类
	 * @return
	 */
	public List<CatalogAndArticleRef> getCatalogAndArticleRefs();
	
	/**
	 * 批量添加文章和分类记录。一篇文章对应多个分类
	 * @param refList
	 * @return
	 */
	public CatalogAndArticleRef addCatalogAndArticleRef( List<CatalogAndArticleRef> refList) ;
	
	/**
	 * 获得分类下的所有文章
	 * @param catalog
	 * @return
	 */
	public List<CatalogAndArticleRef> getArticleBycatalogId(Catalog catalog);
}
