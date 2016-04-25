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
<!-- 评论框 -->
<script>
			var editor;
			
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="commontcontent"]', {
					resizeType : 1,
					allowPreviewEmoticons : false,
					allowImageUpload : false,
					items : [
						]
				});
			});
			
			/* 提交评论 */
			 $(function(){
			      $("#commontButton").click(function(){
			    	  var commentcontext=editor.text();
			    	  var articleId =  document.getElementById("ret_articleId").value;  // 文章id
			    	  var commentedUserId =  document.getElementById("ret_commentedUserId").value;  // 被评论人id
			    	  var commentedUserName =  document.getElementById("ret_commentedUserName").value;  // 被评论人姓名
				    	 
			    	  if($.trim(commentcontext).length == 0) 
			    	  { 
			    		alert("评论内容?");
			    	  	return false;
			    	  } 
			    
			    	  // 'isdraft' : 0 表示正式发表
			    	  var data = {  
			    			    'articleId' : articleId,
			    			    'commentedUserId' : commentedUserId,
						        'commentedUserName' : commentedUserName,
						        'commentcontext' : commentcontext
			    			  };  
			        $.ajax({
			          url:"<%=basePath%>comment/addComment.action",
											type : "post",
											dataType : "json",
											data:JSON.stringify(data),
										    contentType:'application/json;charset=UTF-8',
											success : function(data) {
												if(data.status == '0'){
													alert(data.message);
													window.location.href="<%=basePath%>edit/getArticle.action?id="+ articleId;
												}else if(data.status == '1'){
													alert("请登录");
												}else{
													alert(data.message);
												}
												},
												error : function() {
													alert("请登录");
												}
											});
								});
			});
		</script>
		<!-- 评论框 -->
<!-- 回复管理 -->
<script>

	 
			KindEditor.ready(function(K) {
				K('.replayS').click(function(e) {
					var articleId =  document.getElementById("ret_articleId").value;  // 文章id
					var commentedUserId =  document.getElementById("ret_commentedUserId").value;  // 被评论人id
					var commentedUserName =  document.getElementById("ret_commentedUserName").value;  // 被评论人姓名
					var commentPid = $(e.target).attr("id").substr(11);  // 被评论人的评论
					
					
					var dialog = K.dialog({
						width : 500,
						title : '回复--回复评论',
						body : '<div style="margin:10px;"><input type=\"text\" id=\"replayInfo\" name=\"replayInfo\" style=\"font-size : 0.5em;width:200px; height:20px; float:left;\" maxlength="90"> </div>',
						closeBtn : {
							name : '关闭',
							click : function(e) {
								dialog.remove();
							}
						},
						yesBtn : {
							name : '确定回复',
							click : function(e) {
								 var replayInfo=document.getElementById("replayInfo").value;  // 回复信息
								if($.trim(replayInfo).length == 0) 
							  	  { 
							  		alert("分类名为空？");
							  	  	return false;
							  	  } 
								<%-- window.location.href="<%=basePath%>catalog/addCatalog.action?catalogName="+catalogName; --%>
								 var data = {  
										  'articleId' : articleId,
						    			    'commentedUserId' : commentedUserId,
									        'commentedUserName' : commentedUserName,
						    			  'commentcontext' : replayInfo,
						    			  'commentPid' : commentPid
						    			  };  
								$.ajax({
							          url:"<%=basePath%>comment/addComment.action",
											type : "post",
											dataType : "json",
											data:JSON.stringify(data),
										    contentType:'application/json;charset=UTF-8',
											success : function(data) {
												if(data.status == '0'){
													alert(data.message);
													window.location.href="<%=basePath%>edit/getArticle.action?id="+ articleId;
												}else if(data.status == '1'){
													alert("请登录");
												}else{
													alert(data.message);
												}
												},
												error : function() {
													alert("评论失败，请联系管理员");
												}
											});
								
							}
						},
						noBtn : {
							name : '取消',
							click : function(e) {
								dialog.remove();
							}
						}
					});
				});
			});
		</script>
<!-- 回复管理 -->
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


<style type="text/css">
/* 评论 */
 article, aside, figure, footer, header, hgroup, 
menu, nav, section { display: block; }



p {
    margin: 0 0 1em;
}

.comment {
    overflow: hidden;
    padding: 0 0 1em;
    border-bottom: 1px solid #ddd;
    margin: 0 0 1em;
    *zoom: 1;
}

.comment-img {
    float: left;
    margin-right: 33px;
    border-radius: 5px;
    overflow: hidden;
}

.comment-img img {
    display: block;
}

.comment-body {
    overflow: hidden;
}

.comment .text {
    padding: 10px;
    border: 1px solid #e5e5e5;
    border-radius: 5px;
    background: #fff;
}

.comment .text p:last-child {
    margin: 0;
}

.comment .attribution {
    margin: 0.5em 0 0;
    font-size: 14px;
    color: #666;
}

/* Decoration */

.comments,
.comment {
    position: relative;
}

.comments:before,
.comment:before,
.comment .text:before {
    content: "";
    position: absolute;
    top: 0;
    left: 65px;
}

.comments:before {
    width: 3px;
    top: -20px;
    bottom: -20px;
    background: rgba(0,0,0,0.1);
}

.comment:before {
    width: 9px;
    height: 9px;
    border: 3px solid #fff;
    border-radius: 100px;
    margin: 16px 0 0 -6px;
    box-shadow: 0 1px 1px rgba(0,0,0,0.2), inset 0 1px 1px rgba(0,0,0,0.1);
    background: #ccc;
}

.comment:hover:before {
    background: orange;
}

.comment .text:before {
    top: 18px;
    left: 78px;
    width: 9px;
    height: 9px;
    border-width: 0 0 1px 1px;
    border-style: solid;
    border-color: #e5e5e5;
    background: #fff;
    -webkit-transform: rotate(45deg);
    -moz-transform: rotate(45deg);
    -ms-transform: rotate(45deg);
    -o-transform: rotate(45deg);
}

.article_content p{
font-family:Courier New,Console,Verdana,"微软雅黑";font:normal 14px/20px Arial;
}
</style>
<body>
				
	<!-- <div class="top-header"></div> -->
	 <%@ include file="/mgr/public/includefiles/header.jsp"%> 
	
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
					<c:if test="${not empty articleTagList}">
					标签：
					</c:if>
					<c:forEach items="${articleTagList}" var="articleTagStr">
					<a href="<%=basePath%>search/searchData.action?strParam=${articleTagStr }" >${articleTagStr }</a>&nbsp;
					</c:forEach>
					
				<!-- 	<a style="margin-left:750px" href="javascript:void(0);" onclick="javascript:document.getElementsByTagName('BODY')[0].scrollTop=document.getElementsByTagName('BODY')[0].scrollHeight;">
				我要评论</a> -->
				
					 <hr style="height:10px;border:none;border-top:10px groove rgb(236, 223, 89);" />
						<div class="article_content" >${article.articleContent }</div>
					</div>
				</div>
				<!--  完整的一个模块 -->

			</div>
			<input type="hidden" name="ret_articleId" id="ret_articleId" value="${article.id}" />
			<input type="hidden" name="ret_commentedUserId" id="ret_commentedUserId" value="${article.articleAuthorId}" />
			<input type="hidden" name="ret_commentedUserName" id="ret_commentedUserName" value="${article.articleAuthor}" />
			<input type="hidden" name="ret_commentPid" id="ret_commentPid" value="" />
		</div>
	</div>
		<c:if test="${not empty commentsRet}">
			<div class="clearfix"></div>
			<div class="content" style="margin-top:200px">
				<div class="container" style="margin-left:500px;width:1000px">
					<section class="comments">
						<c:forEach items="${commentsRet}" var="comments">
							    	<c:set var="mycomments" value="${mapsRet[comments.commentId]}"></c:set>
							    	<c:choose>
								    	<c:when test="${not empty mycomments}">
								    	<article class="comment">
										      <a class="comment-img" href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${comments.commentUserId}">
										        <img src="<%=basePath%>${comments.imagePath}" alt="" width="50" height="50">
										      </a>
										      <div class="comment-body">
										        <div class="text">
										          <p>${comments.commentcontext}</p>
										        </div>
										        <p class="attribution">by <a href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${comments.commentUserId}">${comments.commentUserName}</a> at ${comments.addTime}
										        &nbsp;&nbsp;&nbsp;<a class="replayS" id="mycomments_${comments.commentId}" >回复</a>
										        </p>
										      </div>
										    </article>
								    	 <c:forEach items="${mycomments}" var="mycomment">
								    	 	<c:if test="${mycomment.commentPid == comments.commentId}">
												     <article class="comment" style="margin-left:100px">
												      <a class="comment-img" href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${mycomment.commentUserId}">
												        <img src="<%=basePath%>${mycomment.imagePath}" alt="" width="50" height="50">
												      </a>
												      <div class="comment-body">
												        <div class="text">
												          <p>${mycomment.commentcontext}</p>
												        </div>
												        <p class="attribution">by <a href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${mycomment.commentUserId}">${mycomment.commentUserName}</a> at ${mycomment.addTime}
												        </p>
												      </div>
												    </article>
											 </c:if>
										   </c:forEach>
									   </c:when>
									   <c:otherwise>
											<article class="comment">
										      <a class="comment-img" href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${comments.commentUserId}">
										        <img src="<%=basePath%>${comments.imagePath}" alt="" width="50" height="50">
										      </a>
										      <div class="comment-body">
										        <div class="text">
										          <p>${comments.commentcontext}</p>
										        </div>
										        <p class="attribution">by <a href="<%=basePath%>article/getArticlesByOtherUserId.action?userName=${comments.commentUserId}">${comments.commentUserName}</a> at ${comments.addTime}
										        &nbsp;&nbsp;&nbsp;<a class="replayS" id="mycomments_${comments.commentId}" >回复</a>
										        </p>
										      </div>
										    </article>   
									   </c:otherwise>
								   </c:choose>
						</c:forEach>
					
				  </section> 
				</div>
			</div>
		</c:if>
		 <c:if test="${article.status==0}"> 
			<div class="clearfix"></div>
			<div class="content" style="margin-top:100px">
				<div class="container" style="margin-left:500px">
					<textarea name="commontcontent" style="width:700px;height:200px;visibility:hidden;"></textarea>
					<br />
					<input type="button" id="commontButton"
					name="commontButton" class="button blue" value="评论" />
				</div>
			</div>
		 </c:if> 
	<!---->
	 <%@ include file="/mgr/public/includefiles/leftAndRight.jsp"%> 
</body>
</html>
