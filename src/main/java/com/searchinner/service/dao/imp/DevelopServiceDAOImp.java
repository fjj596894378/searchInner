package com.searchinner.service.dao.imp;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.searchinner.dao.DevelopDAO;
import com.searchinner.model.DevelopLink;
import com.searchinner.service.dao.IDevelopServiceDAO;
import com.searchinner.util.SingleThreadStdoutStderr;
@Service("developServiceDAOImp")
public class DevelopServiceDAOImp implements IDevelopServiceDAO{
	private DevelopDAO developDAOImp;
	@Override
	public DevelopLink addDevelopLink(DevelopLink developLink) {
		try{
		SingleThreadStdoutStderr single = new SingleThreadStdoutStderr(developLink.getSiteaddress(),developLink.getSiteusername(),developLink.getSitepassword());
		single.preConnectionAndAuthen(); // 连接与密码验证
		}catch(Exception e){
			System.out.println("验证账号密码失败");
			return null;
		}
		DevelopLink infoRet = developDAOImp.getPasswordByUserName(developLink);
		if(infoRet == null){
			// 添加
			return developDAOImp.addDevelopLink(developLink);
		}else{
			// 修改
			return developDAOImp.updateDevelopLink(developLink);
		}
		//return developDAOImp.addDevelopLink(developLink);
	}

	@Override
	public DevelopLink updateDevelopLink(DevelopLink developLink) {
		return developDAOImp.updateDevelopLink(developLink);
	}

	@Override
	public DevelopLink getDevelopLink(int id) {
		return developDAOImp.getDevelopLink(id);
	}

	@Override
	public List<DevelopLink> getDevelopLinks() {
		return developDAOImp.getDevelopLinks();
	}

	public DevelopDAO getDevelopDAOImp() {
		return developDAOImp;
	}

	@Resource(name="developDAOImp")
	public void setDevelopDAOImp(DevelopDAO developDAOImp) {
		this.developDAOImp = developDAOImp;
	}

	@Override
	public List<DevelopLink> getDevelopLinkInfoByAddress(String address) {
		// TODO Auto-generated method stub
		return developDAOImp.getDevelopLinkInfoByAddress(address);
	}

	public DevelopLink getPasswordByUserName(DevelopLink developLink) {
		return developDAOImp.getPasswordByUserName(developLink);
	}
	
	/**
	 * 用户点击查询按钮。
	 */
	@Override
	public Map<String, String> queryServerStatus(DevelopLink developLink) {
		//DevelopLink infoRet = developDAOImp.getPasswordByUserName(developLink); // 主要是获得密码
		SingleThreadStdoutStderr single = new SingleThreadStdoutStderr(developLink.getSiteaddress(),developLink.getSiteusername(),developLink.getSitepassword(),developLink.getCmd(),false,developLink.getServiceName());
		try {
			single.execute();
		} catch (IOException e) {
			return null;
		}
		
		List<String> listsRet = SingleThreadStdoutStderr.getTTcmdContent(single.listOut);
		Map<String, String> mapRet = new TreeMap<String, String>();
		if(listsRet != null && listsRet.size() > 0){
			String showStr = "";
			for (int i = 0; i < listsRet.size(); i++) {
				showStr =  listsRet.get(i);
				mapRet.put(showStr.substring(0,showStr.length()-1), showStr.substring(showStr.length()-1));
			}
		}
		
		return mapRet;
	}

	@Override
	public List<String> startOrStopCmd(DevelopLink developLink,boolean isStart) {
		SingleThreadStdoutStderr single = new SingleThreadStdoutStderr(developLink.getSiteaddress(),developLink.getSiteusername(),developLink.getSitepassword(),developLink.getCmd(),isStart,developLink.getServiceName());
		try {
			single.preConnectionAndAuthen();
		} catch (IOException e) {
			return null;
		} // 连接与密码验证
		single.execCommand(developLink.getCmd());
		
		return single.getListOut();
	}

}
