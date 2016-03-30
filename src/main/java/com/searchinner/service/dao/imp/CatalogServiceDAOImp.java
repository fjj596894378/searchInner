package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.CatalogDAO;
import com.searchinner.model.Catalog;
import com.searchinner.service.dao.ICatalogServiceDAO;
@Service("catalogServiceDAOImp")
public class CatalogServiceDAOImp implements ICatalogServiceDAO{
	private CatalogDAO catalogDAOImp;
	@Override
	public Catalog addCatalog(Catalog catalog) {
		return catalogDAOImp.addCatalog(catalog);
	}

	@Override
	public Catalog updateCatalog(Catalog catalog) {
		return catalogDAOImp.updateCatalog(catalog);
	}

	@Override
	public Catalog getCatalog(String id) {
		return catalogDAOImp.getCatalog(id);
	}

	@Override
	public void deleteCatalog(String id) {
		
	}

	@Override
	public List<Catalog> getCatalogs(String id) {
		return catalogDAOImp.getCatalogs(id);
	}

	public CatalogDAO getCatalogDAOImp() {
		return catalogDAOImp;
	}
	@Resource(name="catalogDAOImp")
	public void setCatalogDAOImp(CatalogDAO catalogDAOImp) {
		this.catalogDAOImp = catalogDAOImp;
	}
}
