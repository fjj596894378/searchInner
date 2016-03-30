package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.searchinner.service.dao.ICrudService;

/*@Service("crudService")*/
public class CrudServiceDAOImp implements ICrudService {
	
	private MongoTemplate mongoTemplate;

	/**
	 * 根据主键id返回对象
	 * 
	 * @param id
	 *            唯一标识
	 * @return T 对象
	 */
	@Override
	public <T> T findById(Class<T> entityClass, String id) {
		return this.mongoTemplate.findById(id, entityClass);
	}

	/**
	 * 根据类获取全部的对象列表
	 * 
	 * @param entityClass
	 *            返回类型
	 * @return List<T> 返回对象列表
	 */
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
		return this.mongoTemplate.findAll(entityClass);
	}

	/**
	 * 删除一个对象
	 * 
	 * @param obj
	 *            要删除的Mongo对象
	 */
	@Override
	public void remove(Object obj) {
		this.mongoTemplate.remove(obj);
	}

	/**
	 * 添加对象
	 * 
	 * @param obj
	 *            要添加的Mongo对象
	 */
	@Override
	public void add(Object obj) {
		this.mongoTemplate.insert(obj);
	}

	/**
	 * 修改对象
	 * 
	 * @param obj
	 *            要修改的Mongo对象
	 */
	@Override
	public void saveOrUpdate(Object obj) {
		this.mongoTemplate.save(obj);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	@Resource(name="mongoTemplate")
	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	
}
