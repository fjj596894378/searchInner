package com.searchinner.service.dao;

import java.util.List;

import com.searchinner.model.Catalog;

public interface ICatalogServiceDAO {
	/**
	 * 添加分类
	 * @param catalog 分类实体类
	 * @return
	 */
	public Catalog addCatalog( Catalog catalog);

	/**
	 * 修改分类
	 * @param catalog
	 * @return
	 */
	public Catalog updateCatalog(Catalog catalog);

	/**
	 * 通过分类id获得分类
	 * @param id
	 * @return
	 */
	public Catalog getCatalog(String id);

	/**
	 * 删除分类
	 * @param id
	 */
	public void deleteCatalog(String id);
	
	/**
	 * 获得当前用户下的所有分类
	 * @param id
	 * @return
	 */
	public List<Catalog> getCatalogs(String id);
}
