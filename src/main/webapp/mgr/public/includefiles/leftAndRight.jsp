 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>首页</title>



 <!-- 登录和注册 -->
 
<!-- 登录注册 -->
<link rel="stylesheet" href="<%=basePath%>mgr/css/login/198zone.css">
<!-- 登录注册 -->
<script type="text/javascript" src="<%=basePath%>mgr/js/login/modal.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#signup-modal").modal("hide");
	$("#login-modal").modal("hide");
	$("#self-modal").modal("hide");
});

</script>

<!-- 登录和注册 -->

 
<!-- 侧边栏选项 -->
 <link rel="stylesheet" href="<%=basePath%>mgr/css/snap/menu.css" /> 

<script language="JavaScript">

$(document).ready(function(){ 
	
	var longonP =  $("#usernamehidden").val();
	$(function(){
	    $("#regbutton").click(function(){
	  	  var userName=document.getElementById("reguserName").value;  // 用户名
	  	  var passWord=document.getElementById("regpassWord").value;  // 密码
	  	  var password1=document.getElementById("regpassword1").value;  // 确认密码
	  	 var realName=document.getElementById("regrealName").value;  // 确认密码
	  	  if($.trim(userName).length == 0) 
	  	  { 
	  		alert("用户名?");
	  	  	return false;
	  	  }
	  	 
	  	if($.trim(passWord).length == 0) 
		  { 
			alert("密码?");
		  	return false;
		  }else if($.trim(passWord).length < 6) {
			  alert("密码大于等于6位");
			  	return false;
		  } 
	  	
	  	if($.trim(password1).length == 0) 
		  { 
			alert("确认密码?");
		  	return false;
		  } 
	  	if($.trim(realName).length == 0) 
		  { 
			alert("真实姓名?");
		  	return false;
		  } 
	  	if($.trim(passWord) != $.trim(password1)) 
		  { 
			alert("两次密码不一致");
		  	return false;
		  } 
	  	  var data = {  
	  			  		'userName' : userName,
				        'passWord' :passWord,
				        'realName' : realName
	  			  };  
	      $.ajax({
	        url:"<%=basePath%>user/regUser.action",
									type : "post",
									dataType : "json",
									data:JSON.stringify(data),
								    contentType:'application/json;charset=UTF-8',
									success : function(data) {
										alert(data.message);
										window.location.reload();
										},
										error : function() {
											alert("注册失败，请联系管理员");
										}
									});
						});
	});

	$(function(){
	    $("#logonbutton").click(function(){
	  	  var userName=document.getElementById("logonuserName").value;  // 用户名
	  	  var passWord=document.getElementById("logonpassWord").value;  // 密码 
	  	  if($.trim(userName).length == 0) 
	  	  { 
	  		alert("用户名?");
	  	  	return false;
	  	  } 
	  	if($.trim(passWord).length == 0) 
		  { 
			alert("密码?");
		  	return false;
		  }
	  	  var data = {  
	  			  		'userName' : userName,
				        'passWord' :passWord
	  			  };  
	      $.ajax({
	        url:"<%=basePath%>user/logon.action",
									type : "post",
									dataType : "json",
									data:JSON.stringify(data),
								    contentType:'application/json;charset=UTF-8',
									success : function(data) {
										alert(data.message);
										window.location.reload();
										},
										error : function() {
											alert("登录失败，请联系管理员");
										}
									});
						});
	});
	
	 

 
	
	$(function(){
	    $("#articleManage").click(function(){
	    	alert("ccc");
	    	window.location="<%=basePath%>article/getArticlesByUserId.action";
		});
	    
	});
	
	function picval() {
	        var strs = new Array(); //定义一数组
	        var pic1= $("#imgFile").val();
	        strs = pic1.split('.');
	        var suffix = strs [strs .length - 1];

	        if (suffix != 'jpg' && suffix != 'gif' && suffix != 'jpeg' && suffix != 'png') {
	            alert("你选择的不是图片,请选择图片！");
	            var obj = document.getElementById('imgFile');
	            obj.outerHTML = obj.outerHTML; //这样清空，在IE8下也能执行成功
	             //obj.select(); document.selection.clear(); 好像这种方法也可以清空 input file 的value值，不过我没测试
	        }
	    } 
	
	
	$(function(){
	    $("#logonout").click(function(){
	        $.ajax({
	            url:"<%=basePath%>user/logonout.action",
	    								type : "post",
	    								dataType : "json",
	    							    contentType:'application/json;charset=UTF-8',
	    								success : function(data) {
	    									alert(data.message);
	    									window.location.reload();
	    									},
	    									error : function() {
	    										alert("注销失败，请联系管理员");
	    									}
	    								});
		});
	});


		
        $.MetroMenu({
            backicon: "<%=basePath%>mgr/images/snap/back.png",
            animation: "bounceInLeft",
            position: "left",
            color1: "#754c24",
            color2: "#634120",
            withtooltip: true,
            closeonclick: true,
            escclose: true,
            items: [{
            	link:"<%=basePath%>search/index.action",
                name: "主页",
                icon: "<%=basePath%>mgr/images/snap/office.png"
            },
            <c:if test="${empty currentUser}">
            {
            	layouttype : "layout",
            	link:"#login-modal",
                name: "登录",
                icon: "<%=basePath%>mgr/images/snap/ps.png",
            },
            {
            	layouttype : "layout",
            	link:"#signup-modal",
                name: "注册",
                icon: "<%=basePath%>mgr/images/snap/ai.png",
            },
          
            </c:if>
            {
            	link:"<%=basePath%>user/getUsers.action",
                name: "Gnnter",
                icon: "<%=basePath%>mgr/images/snap/control.png",
            },
            <c:if test="${not empty currentUser}">
            {
            	linkID : "logonout",
            	link:"#",
                name: "注销",
                icon: "<%=basePath%>mgr/images/snap/shut-down.png",
            },
            {
            	layouttype : "layout",
            	link:"#self-modal",
                name: longonP,
                icon: "<%=basePath%>mgr/images/snap/excel.png",
            },
            {
                name: "个人管理",
                icon: "<%=basePath%>mgr/images/snap/gear.png",
                items: [{
                	linkID : "articleManage",
                	link:"<%=basePath%>article/getArticlesByUserId.action",
                    name: "文章管理",
                    icon: "<%=basePath%>mgr/images/snap/taskmgr.png"
                },
                {
                	link:"<%=basePath%>catalog/getCatalogs.action",
                    name: "类别管理",
                    icon: "<%=basePath%>mgr/images/snap/outlook.png"
                },
                {
                	link:"<%=basePath%>article/getArticlesDraftByUserId.action",
                    name: "草稿箱",
                    icon: "<%=basePath%>mgr/images/snap/folder.png"
                }, 
                {
                	link:"<%=basePath%>edit/index.action",
                    name: "文章编辑",
                    icon: "<%=basePath%>mgr/images/snap/word.png"
                }]
            } </c:if>]
        });
    /* }); */
    
});
		
</script>
 <!-- 侧边栏选项 -->
 <style type="text/css">
  .file-box{ position:relative;width:340px} 
.txt{ height:22px; border:1px solid #cdcdcd; width:180px;} 
.btn{ background-color:#FFF; border:1px solid #CDCDCD;height:24px; width:70px;} 
.file{ position:absolute; top:0; right:80px; height:24px; filter:alpha(opacity:0);opacity: 0;width:260px }
</style>
<script>
function updatebuttons(){
	  var passWord=document.getElementById("updatepassWord").value;  // 密码
  	  var password1=document.getElementById("updatepassword1").value;  // 确认密码
  	  var realName=document.getElementById("updaterealName").value;  // 确认密码
	  	if($.trim(passWord).length >0) {
	  		if($.trim(passWord).length < 6) {
				  alert("密码大于等于6位");
				  	return false;
			  } 
		  } 
	  	
	  	if($.trim(passWord) != $.trim(password1)) 
		  { 
			alert("两次密码不一致");
		  	return false;
		  } 
	  	
	  	$("updateUserForm").submit();
}
</script>
</head>

<body >
<input type="hidden" name="usernamehidden" id="usernamehidden" value="${currentUser.userName}"/>
<div class="modal" id="login-modal">
	<a class="close" data-dismiss="modal">×</a>
	<h1>登录</h1>
	<form class="login-form clearfix" method="post" action="<%=basePath%>user/logon.action">
		<div class="form-arrow"></div>
		<input name="userName" type="text" id="logonuserName" placeholder="用户名：">
		<input name="passWord" type="password" id="logonpassWord" placeholder="密码：">
		<input type="input" id="logonbutton" name="type" class="button-blue login" value="登录">
		<input type="hidden" name="return-url" value="">
		<div class="clearfix"></div>
		<label class="remember"><input name="remember" type="checkbox" checked/>下次自动登录 </label>
	</form>
</div>

<div class="modal" id="signup-modal">
	<a class="close" data-dismiss="modal">×</a>
	<h1>注册</h1>
	<form class="signup-form clearfix" method="post" action="<%=basePath%>user/regUser.action">
		<p class="error"></p>
		<input name="userName" id="reguserName" type="text" placeholder="用户名：">
		<input name="passWord" type="password" id="regpassWord" placeholder="密码：">
		<input name="password1" type="password" id="regpassword1" placeholder="确认密码：">
		<input name="realName" type="text"  id="regrealName" placeholder="真实名字：">
		<input type="hidden" name="title" value="">
		<input type="hidden" name="url" value="">
		<div class="clearfix"></div>
		<input type="input" name="type" id="regbutton" class="button-blue reg" value="注册" data-action="regist">
	</form>
</div>

<div class="modal" id="self-modal">
	<a class="close" data-dismiss="modal">×</a>
	<h1>个人信息编辑</h1>

	 <form id="updateUserForm" class="signup-form clearfix" method="post"  enctype="multipart/form-data"  action="<%=basePath%>user/updateUsers.action" >
	 <p class="error"></p>
	 	<input name="passWord" type="password" id="updatepassWord" placeholder="新密码：">
		<input name="realName" type="text"  id="updaterealName" placeholder="真实名字：">
		<div class="clearfix"></div>
		<span>头像：</span>
		<input type="file" name="imgFile" id="imgFile" /> 
		<input type="submit" name="type" id="updatebutton" class="button-blue reg" value="更新资料" >
		
	</form>
</div>

</body>
</html>
 