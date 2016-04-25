package com.searchinner.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class SingleThreadStdoutStderr
{
	private String hostname;
	private String username;
	private String password;
	private Connection conn;
	private String serviceName;
	private String cmd;
	private Session sess;
	private boolean isStart; // 是开启还是关闭。
	
	//private StringBuffer sbOut = new StringBuffer();
	//private StringBuffer sbErr = new StringBuffer();
	public List<String> listOut = new LinkedList<String>();
	public List<String> listErr = new LinkedList<String>();
	
	public SingleThreadStdoutStderr() {
	}
	public SingleThreadStdoutStderr(String hostname, String username,
			String password) {
		super();
		this.hostname = hostname;
		this.username = username;
		this.password = password;
	}
	public SingleThreadStdoutStderr(String hostname, String username,
			String password, String cmd) {
		super();
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.cmd = cmd;
	}
	public SingleThreadStdoutStderr(String hostname, String username,
			String password, String cmd,boolean isStart) {
		super();
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.cmd = cmd;
		this.isStart = isStart;
	}
	
	public SingleThreadStdoutStderr(String hostname, String username,
			String password, String cmd,boolean isStart,String serviceName) {
		super();
		this.hostname = hostname;
		this.username = username;
		this.password = password;
		this.cmd = cmd;
		this.isStart = isStart;
		this.serviceName = serviceName;
	}

	public void execute() throws IOException{
		if(hostname != null && !hostname.trim().equals("")){
			try {
				// 第一步。连接
				getConnection();	
				
				// 第二步  验证账号密码
				authenticateWithPassword();
				
				// 第三步 执行linux命令
				if(cmd != null && !cmd.trim().equals("")){
					execCommand(cmd); 
				}
			} catch (IOException e) {
				System.out.println("验证账号密码失败");
				if(conn != null){
					conn.close();
				}
				throw new IOException("验证账号密码出错");
			}
		}
	}
	
	public void getConnection() throws IOException{
		conn = new Connection(hostname);
		conn.connect();
		
	}
	
	public void authenticateWithPassword() throws IOException{
		boolean isAuthenticated = false;
		isAuthenticated = conn.authenticateWithPassword(username, password);
		if (isAuthenticated == false){
			throw new IOException("验证账号密码出错");
		}
	}
	
	public void preConnectionAndAuthen() throws IOException{
		if(hostname != null && !hostname.trim().equals("")){
			try {
				// 第一步。连接
				getConnection();	
				
				// 第二步  验证账号密码
				authenticateWithPassword();
				
			} catch (IOException e) {
				System.out.println("验证账号密码失败");
				if(conn != null){
					conn.close();
				}
				throw new IOException("验证账号密码出错");
			}
		}
	}

	public void execCommand(String cmd){
		BufferedReader stdoutReader = null;
		BufferedReader stderrReader = null;
		try {
			sess = conn.openSession();
			/*sess.requestDumbPTY();
			sess.startShell();*/
			sess.execCommand(cmd);
			//final InputStream stdout = sess.getStdout();
			//final InputStream stderr = sess.getStderr();
			InputStream stdout = new StreamGobbler(sess.getStdout());
			InputStream stderr = new StreamGobbler(sess.getStderr());
			stdoutReader = new BufferedReader(new InputStreamReader(stdout,"GB2312"));
			stderrReader = new BufferedReader(new InputStreamReader(stderr,"GB2312"));
			if(!isStart){ //进行start或者restart操作
				while (true)
				{
					
					String line = stdoutReader.readLine();
					if (line != null){
						System.out.println(line);
						listOut.add(line);
						//if(line.trim().startsWith("信息: Server startup in")){
							System.out.println("退出1-1：" + line);
						//	break;
						//}
					}else{
						System.out.println("退出1：" + line);
						break;
					}
				}
			}else{
				try {
					Thread.sleep(3000);
					listOut.add("服务【" + serviceName + "】   启动成功。");
					// 等待三秒再返回
				} catch (InterruptedException e) {
					
				}
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			try {
				if(stdoutReader != null){
					stdoutReader.close();
				}
				if(stdoutReader != null){
					
						stderrReader.close();
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void main(String[] args) throws IOException
	{
		
		String hostname = "172.18.3.8";
		String username = "zss1410";
		String password = "zss1410gnnt";
		String cmd = "cd ./bin;./tt;";
		//String cmd = "echo $PATH";//./tc_warehouse_gnnt stop
		//String cmd = "cd ./bin;./tc_warehouse_gnnt restart";//restart start stop 
		SingleThreadStdoutStderr singleThread = new SingleThreadStdoutStderr(hostname, username, password,cmd,false);
		singleThread.execute();
		System.out.println(" ***********************分割线 ***********************");
		if(singleThread.listOut != null && singleThread.listOut.size() > 0){
			for (int i = 0; i < singleThread.listOut.size(); i++) {
				System.out.println(singleThread.listOut.get(i).trim().toString());
			}
		}
		//System.out.println(singleThread.sbErr.toString());
		System.out.println(" ***********************分割线 ***********************");
		//System.out.println(singleThread.sbOut.toString());
		
		//getTTcmdContent(singleThread.listOut);
	 
		singleThread.closeRes();
		
	}
	
	public static List<String> getTTcmdContent(List<String> list){
		List<String> listRet = new ArrayList<String>(list.size() - 7);
		if(list != null && list.size() > 0){
			System.out.println("remove:" +list.remove(0) );
			System.out.println("remove:" +list.remove(0) );
			System.out.println("remove:" +list.remove(list.size() - 1) );
			System.out.println("remove:" +list.remove(list.size() - 1) );
			System.out.println("remove:" +list.remove(list.size() - 1) );
			System.out.println("remove:" +list.remove(list.size() - 1) );
			System.out.println("remove:" +list.remove(list.size() - 1) );
			//System.out.println(list.size());
			String result = "";
			for (int i = 0; i < list.size(); i++) {
				//System.out.println(list.get(i).trim().replaceAll("\\s*","").toString());
				result = list.get(i).trim().replaceAll("\\s*","").toString();
				/*serviceNameMap.get(result.substring(0,result.length()-1));
				if(result.substring(0,result.length()-1).startsWith("core_mgrgetfrontuser")){
					result = "core_mgrgetfrontusers_gnnt"+ result.substring(result.length()-1);
				}else if(result.substring(0,result.length()-1).startsWith("core_timebargain_gn")){
					result = "core_timebargain_gnnt"+ result.substring(result.length()-1);
				}else if(result.substring(0,result.length()-1).startsWith("core_conditionPlug")){
					result = "core_conditionPlugin_gnnt" + result.substring(result.length()-1);
				}*/
				
				listRet.add(result);
			}
		}
		return listRet;
	}
	
	public void closeRes(){
		System.out.println("关闭连接");
		if(sess != null){
			sess.close();
		}
		
		if(conn != null){
			conn.close();
		}
		
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public List<String> getListOut() {
		return listOut;
	}
	public void setListOut(List<String> listOut) {
		this.listOut = listOut;
	}
	public List<String> getListErr() {
		return listErr;
	}
	public void setListErr(List<String> listErr) {
		this.listErr = listErr;
	}
}
