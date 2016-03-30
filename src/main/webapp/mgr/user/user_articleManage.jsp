<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

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

<title>文章管理--金网安泰内部分享系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
<meta name="keywords"
	content="Curriculum Vitae Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
  
<script>
	/* when document is ready */
	$(function() {

		/* initiate plugin */
		$("div.holder").jPages({
			containerID : "itemContainer",
			perPage : 10
		});

		/* on select change */
		$("select").change(function() {
			/* get new nº of items per page */
			var newPerPage = parseInt($(this).val());

			/* destroy jPages and initiate plugin again */
			$("div.holder").jPages("destroy").jPages({
				containerID : "itemContainer",
				perPage : newPerPage
			});
		});

	});
	
	
</script>

<!-- 分頁 -->
<script language="JavaScript">

$(document).ready(function(){
	var longonPs =  $("#usernamehidden").val();
	$(function(){
      $("#spanOfArticle a").click(function(){
    	  var articleid=$(this).attr("myarticleid");//删除的articleid
    	  var data = {  
			        'articleId' : articleid
    			  };  
        $.ajax({
          url:"<%=basePath%>edit/deleteArticle.action",
								type : "post",
								dataType : "json",
								data:JSON.stringify(data),
							    contentType:'application/json;charset=UTF-8',
								success : function(data) {
									if(data.status == '0'){
										alert(data.message);
										var infos = data.info;
										window.location.href="<%=basePath%>article/getArticlesByUserId.action";
									}else if(data.status == '1'){
										alert("请登录");
									}else{
										alert(data.message);
									}
									},
									error : function() {
										alert("删除失败，请联系管理员");
									}
								});
        return false;
					});
	});
});
	</script>
<!-- 首页加载 -->

<style type="text/css">
div.col-md-7.posts h5{ color:#5C3317}
div.col-md-7.posts a h5  {
  text-decoration: underline;
}

.container a{ color:#00F}
</style>
<!-- 首页加载 -->

 
</head>

<body>
      
	<!-- <div class="top-header"></div> -->
	<div class="container">
		 <div class="header">
		<a href="<%=basePath%>search/index.action"><h1>gnnt</h1></a>
		</div> 
		<!-- 登录和注册 -->
		<%--  <c:if test="${not empty currentUser}">
		<a id="yourself" href="#"><font color=red>欢迎您: ${currentUser.userName}</font> </a>
		&nbsp;&nbsp;
		 </c:if> --%>
	<%--  |&nbsp;&nbsp;<a href="<%=basePath%>edit/index.action" target="_blank">写新文章 </a> --%>
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
			<%-- <a href="<%=basePath%>edit/index.action" class="button blue">+new Edit</a> --%>
			<div class="holder" style="margin-left:900px"></div>
			
			 <c:if test="${empty otherAuth}">
				 <c:if test="${empty articleList}">
				 	这里还没有你的足迹,要不<a href="<%=basePath%>edit/index.action">编辑</a>一下?
				 </c:if>
			 </c:if>
			  <c:if test="${not empty otherAuth}">
			  	<c:if test="${empty articleList}">
				 	这个人很懒，什么都没写
				 </c:if>
			  </c:if>
			<div class="work">

				<!--  完整的一个模块 -->
				<ul id="itemContainer" style="list-style-type:none">
				
					<c:forEach items="${articleList}" var="article">
						<li>
							<div class="details">
								<%-- <div class="col-md-3 city">
									<h5 >${article.articleAuthor}</h5>
								</div> --%>
								<div class="col-md-7 posts">
								<a href="<%=basePath%>edit/getArticle.action?id=${article.id }" >
								<h5 style="font-size: medium;font-weight: normal;line-height: 1.54">${article.articleTitle}</h5></a>
								(${article.addTime})
								</div>
								 <c:if test="${empty otherAuth}">
								<div class="col-md-2 date">
									<%-- <p>${article.addTime}</p> --%>
									<a href="<%=basePath%>edit/reedit.action?id=${article.id }" >编辑</a>&nbsp;&nbsp;|&nbsp;&nbsp;
									<span id="spanOfArticle">
									<a href="#" myarticleid="${article.id }" id="deleteArticleBtn_${article.id }" >删除</a>
									</span>
								</div>
								</c:if>
								<div class="clearfix"></div>
							</div>
						</li>

					</c:forEach>
				</ul>
				<!--  完整的一个模块 -->

			</div>

			<!-- 登录和注册 -->
		</div>
	</div>
 <%@ include file="/mgr/public/includefiles/leftAndRight.jsp"%> 
 
</body>
</html>
