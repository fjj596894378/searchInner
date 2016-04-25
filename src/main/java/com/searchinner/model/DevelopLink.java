package com.searchinner.model;

import java.io.Serializable;
import java.util.Date;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class DevelopLink  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049907209925321217L;
	private int siteId;
	private String siteaddress;
	private String siteusername;
	private String sitepassword;
	private String lastmodified;
	//private Connection conn;
	private String cmd;
	
	private String serviceName; //服务名
	private String status;	// 状态N,Y
	
	//private Session sess;
	
	/*public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}*/
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	/*public Session getSess() {
		return sess;
	}
	public void setSess(Session sess) {
		this.sess = sess;
	}*/
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getSiteaddress() {
		return siteaddress;
	}
	public void setSiteaddress(String siteaddress) {
		this.siteaddress = siteaddress;
	}
	public String getSiteusername() {
		return siteusername;
	}
	public void setSiteusername(String siteusername) {
		this.siteusername = siteusername;
	}
	public String getSitepassword() {
		return sitepassword;
	}
	public void setSitepassword(String sitepassword) {
		this.sitepassword = sitepassword;
	}
	public String getLastmodified() {
		return lastmodified;
	}
	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
