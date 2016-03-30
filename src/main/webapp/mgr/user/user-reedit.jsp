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

<title>草稿编辑--金网安泰内部分享系统</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
  
<!-- 富文本框显示 -->
<script>
var editortmp;
	KindEditor.ready(function(K) {
		var editor = K.create('textarea[name="articleContent"]', {
		
			cssPath : '<%=basePath%>kindeditor/plugins/code/prettify.css',

			uploadJson : '<%=basePath%>kindeditor/jsp/upload_json.jsp',

			fileManagerJson : '<%=basePath%>kindeditor/jsp/file_manager_json.jsp',

									allowFileManager : true,

									afterCreate : function() {

										var self = this;


									}

								});
		/* editor.html('${article.articleContent}'); */
				prettyPrint();
				editortmp = editor;
			});
	
	
	 $(function(){
	      $("#subEditButton").click(function(){
	    	  var editTitle=document.getElementById("editTitle").value;  // 文章标题
	    	  var articleid=document.getElementById("articleid").value;  // 文章标题
	    	  
	    	  var editTag=document.getElementById("editTag").value;  // 文章标签
	    	  var str=""; // ************************************文章分类*******************************
	    	  obj = document.getElementsByName("tagofckbox");
	    	  var articleCatalog = "";
	    	  var catalogId = "";
	    		for(k in obj){
	    			if(obj[k].checked){ 
	    				/* articleCatalog.push(obj[k].value);
	    				catalogId.push(obj[k].id);   */
	    				articleCatalog += obj[k].value + ",";
	    				catalogId += obj[k].id + ",";
	    			}
	    		}
	    	  /* var editCatalog=document.getElementById("editCatalog").value;  // 文章分类 */
	    	  if($.trim(editTitle).length == 0) 
	    	  { 
	    		alert("鹏哥叫你写标题");
	    	  	return false;
	    	  } 
	    	  if($.trim(editortmp.text()).length == 0) 
	    	  { 
	    		alert("不能偷懒。内容一个字都没有");
	    	  	return false;
	    	  } 
	    	  var data = {  
	    			    'articleTitle' : editTitle,
				        'articleContent' : editortmp.html(),
				        'articleContentPre' : editortmp.text() + "....",
				        'articleTag' : editTag,
				        'articleCatalog' : articleCatalog,
				        'catalogId' : catalogId,
				        'articleId' : articleid
	    			  };  
	        $.ajax({
	          url:"<%=basePath%>edit/updateArticle.action",
									type : "post",
									dataType : "json",
									data:JSON.stringify(data),
								    contentType:'application/json;charset=UTF-8',
									success : function(data) {
										if(data.status == '0'){
											alert(data.message);
											var infos = data.info;
											window.location.href="<%=basePath%>edit/getArticle.action?id="+ infos;
										}else if(data.status == '1'){
											alert("请登录");
										}else{
											alert(data.message);
										}
										},
										error : function() {
											alert("添加失败，请联系管理员");
										}
									});
						});
	});
</script>
<!-- 富文本框显示 -->
<!-- 分类管理 -->
<script>
			KindEditor.ready(function(K) {
				K('#create1').click(function() {
					var dialog = K.dialog({
						width : 500,
						title : '分类管理',
						body : '<div style="margin:10px;">分类名：<input type=\"text\" id=\"catalogName\" name=\"catalogName\" style=\"font-size : 0.5em;width:200px; height:20px; float:left;\" maxlength="10"> </div>',
						closeBtn : {
							name : '关闭',
							click : function(e) {
								dialog.remove();
							}
						},
						yesBtn : {
							name : '确定添加',
							click : function(e) {
								 var catalogName=document.getElementById("catalogName").value;  // 分类名
								if($.trim(catalogName).length == 0) 
							  	  { 
							  		alert("分类名为空？");
							  	  	return false;
							  	  } 
								 var data = {  
						    			  'catalogName' : catalogName
						    			  };  
								$.ajax({
							          url:"<%=basePath%>catalog/addCatalog.action",
											type : "post",
											dataType : "json",
											data:JSON.stringify(data),
										    contentType:'application/json;charset=UTF-8',
											success : function(data) {
												if(data.status == '0'){
													alert(data.message);
													 var dataObj=eval("("+data.listStr+")"); 
													 var msgShow = "";
												       for(var i=0;i<dataObj.length;i++){      
												            msgShow += '<input id=\"chk_tag_';
												            msgShow += dataObj[i].catalogid;
												            msgShow += '" name="tagofckbox" type="checkbox" value=\"';
												            msgShow += dataObj[i].catalogName;
												            msgShow += '">';
												            msgShow += '<label id="lab_tag_';
												            msgShow += dataObj[i].catalogid;
												            msgShow += '"for="chk_tag_';
												            msgShow += dataObj[i].catalogid;
												            msgShow += '" >';
												            msgShow += dataObj[i].catalogName + '<\/label><\/input>';
												       } 
												        //$("#tagofckbox_first").hide();
												       // $("#tagofckbox_second").show();
												        $("#tagofckbox_first").html(msgShow);
													dialog.remove();
												}else if(data.status == '1'){
													alert("请登录");
													dialog.remove();
												}else if(data.status == '2'){
													alert(data.message);
													dialog.remove();
												}
												},
												error : function() {
													alert("添加失败，请联系管理员");
													dialog.remove();
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
<!-- 分类管理 -->

<!-- 禁止输入特殊字符 -->
<script language ="javascript">
        function ValidateEditTag(textbox)
        {
             var IllegalString = "\`~@#;。、】【；‘’·，！@#￥%……&*（）+——.!#$%^&*()+{}|\\:\"<>?-=/\'";
             var textboxvalue = textbox.value;
             /* var index = textboxvalue.length - 1;
             
             var s = textbox.value.charAt(index);  */
             var s = textboxvalue.split(""); //字符串转化为数组
             for (var i = 0; i < s.length; i++) {
            	 if (IllegalString.indexOf(s[i]) >=0) {
            		 textbox.value = "";
            	 }
             }
        }
    </script>
<!-- 禁止输入特殊字符 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Curriculum Vitae Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
</head>

<style>
.subtit {
	padding: 12px 4px 2px 4px;
	color: #999;
	font-weight: bold;
	border-bottom: solid 1px #f5f5f5;
	clear: both;
}

.subtit a{ color:#00F}
.button .blue a{ color:#FFF}
</style>
<body>
<input type="hidden" name="articleid" id="articleid" value="${article.id}"/>
	<!-- <div class="top-header" ></div> -->
	<div class="container" >
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
		<div class="container" >
			<!-- <h3>查询结果</h3> -->
			<%-- <a href="<%=basePath%>edit/index.action" class="button blue">+new
				Edit</a> --%>
			<div class="holder" ></div>
			<div class="work1" style="padding-left:430px">

				<!--  完整的一个模块 -->
				<div class="details">
					<div class="col-md-7 posts" style="margin-left:100px"> <!-- 100 -->
						<form name="example" method="post" action="article_add.action">

							<p class="subtit">文章标题</p>
							<input type="text" id="editTitle" name="articleTitle"
								style="font-size : 0.5em;width:560px; height:20px; float:left;"
								maxlength="100" value="${article.articleTitle}" > <br />
							<p class="subtit">文章内容</p>

							<textarea id="editContent" name="articleContent"
								style="width:800px;height:400px;visibility:hidden;"  >${article.articleContent}</textarea>
							<br /> 
							<p class="subtit">文章标签（添加Tag，你的内容能被更多人看到）<span style="color:red" >（最多添加5个标签，多个标签之间用“,”分隔【禁止输入特殊字符】）</span></p><!-- 我们为什么要打Tag？ -->
							<input type="text" id="editTag" name="articleTag"
								style="font-size : 0.5em;width:560px; height:20px; float:left;"
								maxlength="100" onkeyup = "ValidateEditTag(this)" onafterpaste="ValidateEditTag(this)" value="${article.articleTag}" /> 
							<br />
							<p class="subtit"> 个人分类<a id="create1" >【分类管理】</a></p><!-- 我们为什么要打Tag？ -->
							<!-- <input type="text" id="editCatalog" name="articleCatalog"
								style="font-size : 0.5em;width:560px; height:20px; float:left;"
								maxlength="100">  -->
								<div id="tagofckbox_first">
								 <c:forEach items="${catalogList}" var="catalog">
								 <label id="lab_tag_${catalog.catalogid}" for="chk_tag_${catalog.catalogid}">
								 		<!-- 这个 是想要把选中的分类打上勾 -->
								 		<c:set var="mycatalogid" value="${refMap[catalog.catalogid]}"></c:set>
										 	<c:choose>
												<c:when test="${not empty mycatalogid }">
												 <input name="tagofckbox" id="chk_tag_${catalog.catalogid}" type="checkbox" value="${catalog.catalogName}" checked="true" />
												 	${catalog.catalogName}</label>
												</c:when>
												<c:otherwise>
												 <input name="tagofckbox" id="chk_tag_${catalog.catalogid}" type="checkbox" value="${catalog.catalogName}"  />
												 	${catalog.catalogName}</label>
												</c:otherwise>
											</c:choose>
								 </c:forEach>
								</div>
								
							<br />
							<br />
							<input type="button" id="subEditButton"
								name="subEditButtonName" class="button blue" value="更新" />
								<br /><br />
							提示：发表的文章不会马上更新索引，可能会有一分钟的延迟

						</form>
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
