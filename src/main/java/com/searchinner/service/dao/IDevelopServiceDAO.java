package com.searchinner.service.dao;

import java.util.List;
import java.util.Map;

import com.searchinner.model.DevelopLink;
import com.searchinner.viewmodel.SSHLinkModel;

public interface IDevelopServiceDAO {
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
	 * 查询。操作tt命令。
	 * @param developLink
	 * @return
	 */
	public Map<String, String> queryServerStatus( DevelopLink developLink);
	
	/**
	 * 开启或者关闭。
	 * @param linkModel
	 * @param isStart
	 * @return
	 */
	public List<String> startOrStopCmd(DevelopLink linkModel,boolean isStart);
	
	/**
	 * 得到密码。
	 * @param developLink
	 * @return
	 */
	public DevelopLink getPasswordByUserName(DevelopLink developLink);
	
}
