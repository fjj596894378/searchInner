<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
 <%@ include file="/mgr/public/includefiles/allincludefiles.jsp"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>开发环境管理--金网安泰内部分享系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<script>

function show_addressName(siteaddress){
	//document.all.sele.options[0].selected=true;
	 var data = {  
		  		'siteaddress' : siteaddress
		  }; 
	 $.ajax({
         url:"<%=basePath%>develop/getDevelopLinkInfoByAddress.action",
								type : "post",
								dataType : "json",
								data:JSON.stringify(data),
							    contentType:'application/json;charset=UTF-8',
								success : function(data) {
									if(data.status == '0'){
										 var dataObj=eval("("+data.listsStr+")"); 
										 var msgShow = "";
										 msgShow += '登录名：';
										  msgShow += '<select id=\"select_query_username\"  style=\"width:100px;height:30px;';
										  msgShow += '">';
										  for(var i=0;i<dataObj.length;i++){      
											  msgShow += '<option value="' + dataObj[i].siteusername;
											  msgShow += '">' + dataObj[i].siteusername + '<\/option>';
										 }
										  msgShow += '<\/select>';
										  $("#select_first").html(msgShow);
									}else if(data.status == '1'){
										alert("请登录");
									}else{
										alert(data.message);
									}
									},
									error : function() {
										alert("操作失败，请联系管理员");
									}
								});
}

function startOrStopCmd(serviceName,startOrStop,siteaddress,siteusername,sitepassword){
	document.getElementById(serviceName).disabled="disabled";
	var preName =  document.getElementById(serviceName).value;
	 if(startOrStop == 'N' || startOrStop == 'n'){
		 document.getElementById(serviceName).value="正在启动……";
	 }else if(startOrStop == 'Y' || startOrStop == 'y'){
		 document.getElementById(serviceName).value="正在关闭……";
	 }else{
		 document.getElementById(serviceName).value="状态未确定";
	 }
	//startOrStop为N,开启。startOrStop为Y，关闭
	 var data = {  
		  		'serviceName' : serviceName,
		  		'status' : startOrStop,
		  		'siteaddress' : siteaddress,
		        'siteusername' :siteusername,
		        'sitepassword' :sitepassword
		  }; 
	 $.ajax({
         url:"<%=basePath%>develop/startOrStopCmd.action",
								type : "post",
								dataType : "json",
								data:JSON.stringify(data),
							    contentType:'application/json;charset=UTF-8',
								success : function(data) {
									if(data.status == '0'){
										 var dataObj=eval("("+data.listOutStr+")"); 
										 var msgShow = '<span style=\"color:red\">';
										 //var changeBtn = "";
										  for(var i=0;i<dataObj.length;i++){      
											  msgShow += dataObj[i] + '<br />';
										  }
										  msgShow += '<\/span>';
										  $("#content_ret_detail").html(msgShow);
										 // alert(data.str);


										  var data1 = {  
											  		'siteaddress' : siteaddress,
											        'siteusername' :siteusername
											  }; 
											 $.ajax({
											        url:"<%=basePath%>develop/queryServerStatus.action",
																			type : "post",
																			dataType : "json",
																			data:JSON.stringify(data1),
																		    contentType:'application/json;charset=UTF-8',
																			success : function(data) {
																				if(data.status == '0'){
																					 var dataObj=eval("("+data.mapStr+")"); 
																					 var msgShow = '<table class=\"altrowstable\"><tr>';
																					 msgShow += '<th  style=\"width:400px';
																					 msgShow += '">';
																					 msgShow += '服务名' + '<\/th>';
																					 msgShow += '<th style=\"width:100px';
																					 msgShow += '">';
																					 msgShow += '状态' + '<\/th>';
																					 msgShow += '<th style=\"width:100px\">';
																					 msgShow += '操作' + '<\/th>' + '<\/tr>';
																					 for(var key in dataObj){
																						 msgShow +='<tr>';
																						 msgShow +='<td>' +key+ '<\/td>';
																						 msgShow +='<td>' + dataObj[key] + '<\/td>';
																						 if(dataObj[key] == 'N' || dataObj[key] == 'n'){
																							 msgShow +='<td>' +'<input type=\"button\" id=\"' + key + '\" class=\"cmdbutton cmdblue\" onclick=\"startOrStopCmd(\'' + key + '\',\'' + dataObj[key] + '\',\'' + siteaddress  + '\',\'' + siteusername  + '\',\'' + sitepassword +'\')\"'+' value=\"重启\" \/>'+ '<\/td>';
																						 }else if(dataObj[key] == 'Y' || dataObj[key] == 'y'){
																							 msgShow +='<td>' +'<input type=\"button\" id=\"' + key + '\" class=\"cmdbutton cmdred\" onclick=\"startOrStopCmd(\'' + key + '\',\'' + dataObj[key] + '\',\'' + siteaddress  + '\',\'' + siteusername  + '\',\'' + sitepassword +'\')\"'+' value=\"关闭\" \/>'+ '<\/td>';
																						 }else{
																							 msgShow +='<td>' +'状态未确定'+ '<\/td>';
																						 }
																					 }
																					 
																							  
																					  msgShow += '<\/tr>';
																					  msgShow += '<tr><td colspan="3">在重启或者关闭服务时，可能不成功。原因:<br />1.tt命令显示的服务名不完全。程序没能捕获到正确的服务名。<br />2.程序启动未完全成功，可以再次查询。<br />3.服务启动过程中报错了。<br />4.目前已知做市商（transformhq）不能成功启动<\/td><\/tr>';
																					  msgShow += '<\/table>';
																					  $("#content_show_detail").html(msgShow);
																				}else if(data.status == '1'){
																					alert("请登录");
																				}else{
																					//alert(data.message);
																				}
																				},
																				error : function() {
																					alert("操作失败，请联系管理员");
																				},
																				complete : function() {
																					alert("操作成功");
																					document.getElementById(serviceName).disabled="";
																				}
																			});
									}else if(data.status == '1'){
										alert("请登录");
									}else{
										alert(data.message);
										document.getElementById(serviceName).value=preName;
										document.getElementById(serviceName).disabled="";
									}
									},
									error : function(data) {
										alert("操作失败，请联系管理员");
									}
								});
}


 
</script>

<script>
$(function(){
    $("#addServerInfo").click(function(){
    	document.getElementById("addServerInfo").disabled="disabled";
	 var siteaddress=document.getElementById("serverDevName").value;  // 服务器地址
	 var siteusername=document.getElementById("serverloginName").value;  // 服务器登录名
	 var sitepassword=document.getElementById("serverpassword").value;  // 密码
	 var data = {  
		  		'siteaddress' : siteaddress,
		        'siteusername' :siteusername,
		        'sitepassword' :sitepassword
		  }; 
	 $.ajax({
        url:"<%=basePath%>develop/addDevelopLink.action",
								type : "post",
								dataType : "json",
								data:JSON.stringify(data),
							    contentType:'application/json;charset=UTF-8',
								success : function(data) {
									if(data.status == '0'){
										alert(data.message);
										window.location.href="<%=basePath%>develop/getList.action";
									}else if(data.status == '1'){
										alert("请登录");
									}else{
										alert(data.message);
									}
									},
									error : function() {
										alert("操作失败，请联系管理员");
									},
									complete : function() {
										document.getElementById("addServerInfo").disabled="";
									}
								});
    });
});


$(function(){
    $("#queryServerButton").click(function(){
    	$("#compnotify").html("");
    	document.getElementById("queryServerButton").disabled="disabled";
	 var siteusername=document.getElementById("select_query_username").value;  // 登录名
	 var  myselect=document.getElementById("select_query");
	 var index=myselect.selectedIndex ; 
	 var siteaddress = myselect.options[index].value;
	 if($.trim(siteaddress).length == 0) 
   	  { 
   		alert("请选择服务器");
   	  	return false;
   	  } 
	 if($.trim(siteusername).length == 0) 
  	  { 
  		alert("请选择登录名");
  	  	return false;
  	  } 
	 var data = {  
		  		'siteaddress' : siteaddress,
		        'siteusername' :siteusername
		  }; 
	 $.ajax({
        url:"<%=basePath%>develop/queryServerStatus.action",
								type : "post",
								dataType : "json",
								data:JSON.stringify(data),
							    contentType:'application/json;charset=UTF-8',
								success : function(data) {
									
									if(data.status == '0'){
										 var dataObj=eval("("+data.mapStr+")"); 
										 var msgShow = '<table class=\"altrowstable\"><tr>';
										 msgShow += '<th  style=\"width:400px';
										 msgShow += '">';
										 msgShow += '服务名' + '<\/th>';
										 msgShow += '<th style=\"width:100px';
										 msgShow += '">';
										 msgShow += '状态' + '<\/th>';
										 msgShow += '<th style=\"width:100px\">';
										 msgShow += '操作' + '<\/th>' + '<\/tr>';
										 for(var key in dataObj){
											 msgShow +='<tr>';
											 msgShow +='<td>' +key+ '<\/td>';
											 msgShow +='<td>' + dataObj[key] + '<\/td>';
											 if(dataObj[key] == 'N' || dataObj[key] == 'n'){
												 msgShow +='<td>' +'<input type=\"button\" id=\"' + key + '\" class=\"cmdbutton cmdblue\" onclick=\"startOrStopCmd(\'' + key + '\',\'' + dataObj[key] + '\',\'' + siteaddress  + '\',\'' + siteusername  + '\',\'' + data.retPassword +'\')\"'+' value=\"重启\" \/>'+ '<\/td>';
											 }else if(dataObj[key] == 'Y' || dataObj[key] == 'y'){
												 msgShow +='<td>' +'<input type=\"button\" id=\"' + key + '\" class=\"cmdbutton cmdred\" onclick=\"startOrStopCmd(\'' + key + '\',\'' + dataObj[key] + '\',\'' + siteaddress  + '\',\'' + siteusername  + '\',\'' + data.retPassword +'\')\"'+' value=\"关闭\" \/>'+ '<\/td>';
											 }else{
												 msgShow +='<td>' +'状态未确定'+ '<\/td>';
											 }
										 }
												  
										  msgShow += '<\/tr>';
										  msgShow += '<tr><td colspan="3">在重启或者关闭服务时，可能不成功。原因:<br />1.tt命令显示的服务名不完全。程序没能捕获到正确的服务名。<br />2.程序启动未完全成功，可以再次查询。<br />3.服务启动过程中报错了。<br />4.目前已知做市商（transformhq）不能成功启动<\/td><\/tr>';
										  msgShow += '<\/table>';
										  $("#content_show_detail").html(msgShow);
									}else if(data.status == '1'){
										alert("请登录");
									}else{
										alert(data.message);
									}
									},
									error : function() {
										alert("操作失败，请联系管理员");
									},
									complete : function() {
										$("#compnotify").html("查询完成");
										document.getElementById("queryServerButton").disabled="";
									}
								});
    });
});
</script>

<style type="text/css">
	table.altrowstable {
		font-family: verdana,arial,sans-serif;
		font-size:11px;
		color:#333333;
		border-width: 1px;
		border-color: #a9c6c9;
		border-collapse: collapse;
	}
	table.altrowstable th {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #a9c6c9;
	}
	table.altrowstable td {
		border-width: 1px;
		padding: 8px;
		border-style: solid;
		border-color: #a9c6c9;
	}
	.oddrowcolor{
		background-color:#d4e3e5;
	}
	.evenrowcolor{
		background-color:#c3dde0;
	}
</style>
  </head>
  
  <body>
    <%@ include file="/mgr/public/includefiles/header.jsp"%> 
    
    <div class="content">
		<div class="container">
			<!-- <h3>查询结果</h3> -->
			<%-- <a href="<%=basePath%>edit/index.action" class="button blue">+new
				Edit</a> --%>
			<div class="holder" style="margin-left:900px"></div>
			<div class="work1" >

				<!--  完整的一个模块 -->
				<div class="details">
					<div class="col-md-7 posts" style="margin-left:120px;width:1000px;">
					<div id="select_first_sel" style="float:left;">
					服务器：
					<select id="select_query" style="width:100px;height:30px;" onchange="show_addressName(this.options[this.options.selectedIndex].value);">
					 <option value="" selected = "selected">-服务器选择-</option>
					<c:forEach items="${mapRet}" var="siteAddress" varStatus="status">
                                    <option value="${siteAddress.key }" >${siteAddress.key }</option>
					</c:forEach>
					</select>
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<div id="select_first" style="float:left;margin-left:25px">
					登录名：
					<select name="sele" style="width:100px;height:30px;">
					</select>
					</div>
					
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="queryServerButton"
						name="queryServerButton" class="button blue" value="查询" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<span id="compnotify" style="color:gray"></span>			
			
				<br><br>
					
					<div class="article_content" >
						服务器：
						<input type="text"  style="width:100px;height:25px;"
							name="serverDevName" id="serverDevName" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						登录名：
						<input type="text" style="width:100px;height:25px;"
							name="serverloginName" id="serverloginName"  />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						密码：
						<input type="password" style="width:100px;height:25px;"
							name="serverpassword" id="serverpassword"  />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" id="addServerInfo"
							name="addServerInfo" class="button blue"  value="添加" />

					</div>
					
					<br><br>
					
					<div class="content_show" >
						<div id="content_show_detail"  >
							
						</div>
					</div>
				<br><br>
						<div id="content_ret_detail" style="width:600px;">
							<hr >
							
						</div>
					</div>
				</div>
				<!--  完整的一个模块 -->

			</div>
			
		</div>
	</div>
	<%@ include file="/mgr/public/includefiles/leftAndRight.jsp"%> 
  </body>
</html>
