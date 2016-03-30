package com.searchinner.service.dao;

import java.util.List;

import com.searchinner.model.User;

public interface IUserServiceDAO {
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
	 * 登录
	 * @param user
	 * @return
	 */
	public User logon(User user);
}
