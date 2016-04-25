<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- Custom Theme files -->
<link href="<%=basePath%>mgr/css/bootstrap.css" rel='stylesheet'
	type='text/css' />
<link href="<%=basePath%>mgr/css/style.css" rel='stylesheet'
	type='text/css' />
<link href="<%=basePath%>mgr/css/search.css" rel='stylesheet'
	type='text/css' />
<link rel="stylesheet"
	href="<%=basePath%>kindeditor/themes/default/default.css" />
<!-- 分頁 -->
<style type="text/css">
.holder {
	margin: 15px 0;
}

.holder a {
	font-size: 12px;
	cursor: pointer;
	margin: 0 5px;
	color: #333;
}

.holder a:hover {
	background-color: #222;
	color: #fff;
}

.holder a.jp-previous {
	margin-right: 15px;
}

.holder a.jp-next {
	margin-left: 15px;
}

.holder a.jp-current,a.jp-current:hover {
	color: #FF4242;
	font-weight: bold;
}

.holder a.jp-disabled,a.jp-disabled:hover {
	color: #bbb;
}

.holder a.jp-current,a.jp-current:hover,.holder a.jp-disabled,a.jp-disabled:hover
	{
	cursor: default;
	background: none;
}

.holder span {
	margin: 0 5px;
}

form {
	float: right;
	margin-right: 10px;
}

form label {
	margin-right: 5px;
}
</style>
<!-- 分頁 -->

<!-- 添加新文章按钮 -->
<style>
.button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}


.cmdblue {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#EAE2E0),
		to(#ED723C) );
	background: -moz-linear-gradient(top, #FAFDFD, #E04806);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}

.cmdred {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#F73406),
		to(#EC2921) );
	background: -moz-linear-gradient(top, #F73406, #EC2921);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}
.blue:hover {
	background: #f47c20;
	background: -webkit-gradient(linear, left top, left bottom, from(#f88e11), to(#f06015));
	background: -moz-linear-gradient(top,  #f88e11,  #f06015);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11', endColorstr='#f06015');
}
.blue:active {
	color: #fcd3a5;
	background: -webkit-gradient(linear, left top, left bottom, from(#f47a20), to(#faa51a));
	background: -moz-linear-gradient(top,  #f47a20,  #faa51a);
	filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20', endColorstr='#faa51a');
}
</style> 
<!-- 添加新文章按钮 -->

<!-- 登录注册 -->
<link rel="stylesheet" href="<%=basePath%>mgr/css/login/198zone.css">
<!-- 登录注册 -->
<!-- 编辑 -->
<link rel="stylesheet"
	href="<%=basePath%>kindeditor/plugins/code/prettify.css" />
<!-- 编辑 -->

<!-- 提交按钮 -->
<style>
.button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.cmdbutton {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 10px/100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .22em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}
.cmdblue {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#EAE2E0),
		to(#ED723C) );
	background: -moz-linear-gradient(top, #FAFDFD, #E04806);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}

.cmdred {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#F73406),
		to(#EC2921) );
	background: -moz-linear-gradient(top, #F73406, #EC2921);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}
 .button:hover {
	text-decoration: none;
}

.button:active {
	position: relative;
	top: 1px;
} 

.blue {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a),
		to(#f47a20) );
	background: -moz-linear-gradient(top, #faa51a, #f47a20);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}

.blue:hover {
	background: #f47c20;
	background: -webkit-gradient(linear, left top, left bottom, from(#f88e11),
		to(#f06015) );
	background: -moz-linear-gradient(top, #f88e11, #f06015);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11',
		endColorstr='#f06015' );
}

.blue:active {
	color: #fcd3a5;
	background: -webkit-gradient(linear, left top, left bottom, from(#f47a20),
		to(#faa51a) );
	background: -moz-linear-gradient(top, #f47a20, #faa51a);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20',
		endColorstr='#faa51a' );
}
</style>
<!-- 提交按钮 -->