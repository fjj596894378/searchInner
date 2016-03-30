package com.searchinner.dao;

import java.util.List;

import com.searchinner.model.User;

public interface UserDAO {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser( User user);

	/**
	 * 用户信息修改
	 * @param user
	 */
	public User updateUser(User user);

	/**
	 * 通过用户名获得用户信息
	 * @param userName
	 * @return
	 */
	public User getUser(String userName);
	
	/**
	 * 登录
	 * @param user
	 * @return
	 */
	public User logon(User user);

	/**
	 * 删除用户
	 * @param userName
	 */
	public void deleteUser(String userName);

	/**
	 * 获得所有用户
	 * @return
	 */
	public List<User> getUsers();
	
	/**
	 * 修改用户的默认分类
	 * @param user
	 */
	public void updateUserOfCatalog(User user);
}
