package com.searchinner.service.dao.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.CatalogDAO;
import com.searchinner.dao.UserDAO;
import com.searchinner.model.Catalog;
import com.searchinner.model.User;
import com.searchinner.service.dao.IUserServiceDAO;

@Service("userServiceDAOImp")
public class UserServiceDAOImp implements IUserServiceDAO{
	private UserDAO userDAOImp;
	private CatalogDAO catalogDAOImp;
	public UserDAO getUserDAOImp() {
		return userDAOImp;
	}
	@Resource(name="userDAOImp")
	public void setUserDAOImp(UserDAO userDAOImp) {
		this.userDAOImp = userDAOImp;
	}
	@Override
	public User addUser(User user) {
		User userRet = userDAOImp.addUser(user);		//1.添加用户信息
		Catalog catalog = new Catalog(); 
		catalog.setCatalogName("默认分类");
		catalog.setUserName(userRet.getUserName());
		catalog = catalogDAOImp.addCatalog(catalog); //添加	//2.添加用户对应分类
		userRet.setDefaultCatalogid(catalog.getCatalogid());
		userDAOImp.updateUserOfCatalog(userRet);	// 3.把用户对应分类关联起来。
		return userRet;
	}
	@Override
	public User updateUser(User user) {
		return userDAOImp.updateUser(user);
		
	}
	@Override
	public User getUser(String userName) {
		return userDAOImp.getUser(userName);
	}
	@Override
	public void deleteUser(String userName) {
		userDAOImp.deleteUser(userName);
		
	}
	@Override
	public List<User> getUsers() {
		return userDAOImp.getUsers();
	}
	@Override
	public User logon(User user) {
		return userDAOImp.logon(user);
	}
	public CatalogDAO getCatalogDAOImp() {
		return catalogDAOImp;
	}
	@Resource(name="catalogDAOImp")
	public void setCatalogDAOImp(CatalogDAO catalogDAOImp) {
		this.catalogDAOImp = catalogDAOImp;
	}

	
}
