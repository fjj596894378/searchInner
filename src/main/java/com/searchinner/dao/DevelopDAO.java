package com.searchinner.dao;

import java.util.List;

import com.searchinner.model.Catalog;
import com.searchinner.model.DevelopLink;

public interface DevelopDAO {
	/**
	 * 添加服务器信息
	 * @param developLink 服务器信息类
	 * @return
	 */
	public DevelopLink addDevelopLink( DevelopLink developLink);
	
	/**
	 * 修改服务器信息
	 * @param developLink
	 * @return
	 */
	public DevelopLink updateDevelopLink( DevelopLink developLink);
	
	/**
	 * 通过服务器信息id获得服务器信息
	 * @param id
	 * @return
	 */
	public DevelopLink getDevelopLink(int id);
	
	/**
	 * 通过服务器地址获得服务器信息
	 * @param address
	 * @return
	 */
	public List<DevelopLink> getDevelopLinkInfoByAddress(String address);
	
	/**
	 * 获得服务器信息全部
	 * @param id
	 * @return
	 */
	public List<DevelopLink> getDevelopLinks();
	
	/**
	 * 修改服务器信息
	 * @param developLink
	 * @return
	 */
	public DevelopLink getPasswordByUserName( DevelopLink developLink);
}
