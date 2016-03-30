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

<title>金网安泰内部分享系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<!-- Custom Theme files -->
<link href="<%=basePath%>mgr/css/bootstrap.css" rel='stylesheet'
	type='text/css' />
<link href="<%=basePath%>mgr/css/style.css" rel='stylesheet'
	type='text/css' />
<link href="<%=basePath%>mgr/css/search.css" rel='stylesheet'
	type='text/css' />

<%-- <script type="text/javascript" src="<%=basePath%>mgr/js/jquery-1.9.js"></script> --%>
<!-- syntaxhighlighter 代码显示插件-->
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/shCore.js"></script>
 
<link type="text/css" rel="stylesheet"
	href="<%=basePath%>mgr/js/syntaxhighlighter/styles/shCoreDefault.css" />
 <!-- 公用语言  -->
 <script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushJava.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushJScript.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushPhp.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushPython.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushSql.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushXml.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushCss.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushCSharp.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushBash.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/syntaxhighlighter/scripts/shAutoloader.js"></script>

<!-- syntaxhighlighter 代码显示插件-->

<script type="text/javascript">
	$(document).ready(function() {
		SyntaxHighlighter.autoloader(
			    'java            <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushJava.js',
			    'php            <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushPhp.js',
			    'html xml           <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushXml.js',
			    'css            <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushCss.js',
			    'js jscript javascript          <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushJScript.js',
			    'bash shell            <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushBash.js',
			    'C#            <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushCSharp.js',
			    'sql          <%=basePath%>mgr/js/syntaxhighlighter/scripts/shBrushSql.js'
			);
			SyntaxHighlighter.all();
	});
</script>
 <!-- 公用语言  -->
<script type="text/javascript">SyntaxHighlighter.defaults['toolbar'] = false;</script>
<!-- syntaxhighlighter 代码显示插件-->
</script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Curriculum Vitae Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>

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
</head>

<style>
.subtit {
	padding: 12px 4px 2px 4px;
	color: #999;
	font-weight: bold;
	border-bottom: solid 1px #f5f5f5;
	clear: both;
}
div.col-md-7.posts a:hover{color:#00F }

</style>
<body>
				
	<!-- <div class="top-header"></div> -->
	<div class="container">
	
		<div class="header">
		
			 <a href="<%=basePath%>search/index.action"><h1>gnnt</h1></a>  
		</div>
		<div class="header-right">
			<div class="list"></div>

			<form action="<%=basePath%>search/searchData.action" name="searchform"
				id="searchform" method="post">
				<table border="0" align="center" cellpadding="0" cellspacing="0"
					class="tab_search">
					<tr>
						<td><input type="text" name="strParam" title="Search"
							class="searchinput" id="searchinput"
							onkeydown="if (event.keyCode==13) {}"
							onfocus="if(this.value=='- Search Products -')value='';"
							value="${retSearch }" size="10" /></td>
						<td><input type="image" class="searchaction"
							onclick="if(document.forms['search'].searchinput.value=='- Search Products -')document.forms['search'].searchinput.value='';"
							alt="Search" src="<%=basePath%>mgr/images/magglass.gif"
							border="0" hspace="2" /></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="clearfix"></div>
	</div>
	<!---->
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
					<p style="color: #000; font: normal 25px/30px 'Microsoft YaHei';">${article.articleTitle}</p>
					标签：
					<c:forEach items="${articleTagList}" var="articleTagStr">
					<a href="<%=basePath%>search/searchData.action?strParam=${articleTagStr }" >${articleTagStr }</a>&nbsp;
					</c:forEach>
					 <hr style="height:10px;border:none;border-top:10px groove rgb(236, 223, 89);" />
						${article.articleContent }
						</div>
				</div>
				<!--  完整的一个模块 -->

			</div>
			
		</div>
	</div>
	<!---->
	 <%@ include file="/mgr/public/includefiles/leftAndRight.jsp"%> 
</body>
</html>
