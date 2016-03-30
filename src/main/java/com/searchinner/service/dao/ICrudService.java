package com.searchinner.service.dao;

import java.util.List;

public interface ICrudService {

	<T> T findById(Class<T> entityClass, String id);

	<T> List<T> findAll(Class<T> entityClass);

	void remove(Object obj);

	void add(Object obj);

	void saveOrUpdate(Object obj);
}
