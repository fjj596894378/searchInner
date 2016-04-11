<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 提供一些公用的方法 js -->
<!-- 分頁 -->
<link rel="stylesheet" href="<%=basePath%>mgr/css/jPagesstyle.css">
<link rel="stylesheet" href="<%=basePath%>mgr/css/jPages.css">
 <!-- 消息提示 -->
 <script type="text/javascript" src="<%=basePath%>mgr/js/messageTip/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="<%=basePath%>mgr/js/messageTip/jquery.messager.js"></script>

<script>
/*  $(document).ready(function($){  
	   
		$.messager.lays(200, 100);
		$.messager.show('有新的评论回复','<a href="#">' +'</a>', 0);
	  
  });   */
</script>


<script language ="javascript">
$(document).ready(function($){
	window.globalCount = 0;
	 
	$(function(){
	    var time=20000; //30miao 查询一次
	    var interval;
	    function run1(){   
	   	 interval=setInterval(funs,time);
	    }
	    var data = {  
	    		'tmp' : time
  			  }; 
	    function funs(){
	    	 $.ajax({
		          url:"<%=basePath%>messageInfo/getMessageInfos.action",
					type : "post",
					dataType : "json",
					data:JSON.stringify(data), 
				    contentType:'application/json;charset=UTF-8',
					success : function(data) {
						if(data.status == '0'){
							 var dataObj=eval("("+data.retMessage+")"); 
							$.messager.lays(230, 100);
							var commentUserName = dataObj.commentUserName;
							var commentcontext = dataObj.commentcontext.substring(0,11) + '……<点击查看>';
							var articleId = dataObj.articleId;
							$.messager.show('有新的评论回复','<a href="<%=basePath%>edit/getArticle.action?id=' + articleId + '"' + '>' + commentUserName +'对您的文章进行了评论:'+commentcontext + '</a>', 10000); 
							//mylays(commentUserName,commentcontext);
						}else if(data.status == '1'){
							// alert("请登录");
						}else{
							// 无数据
							//alert("保存失败1，请联系管理员");
						}
						},
						error : function() {
							//alert("保存失败，请联系管理员");
						}
					});
	    };
	    run1();
	});
	
	
	
	
});</script>
<!-- 消息提示 -->
<script type="text/javascript"
	src="<%=basePath%>mgr/js/jquery-1.8.2.min.js"></script>
	<script src="<%=basePath%>mgr/js/snap/menu.js"></script>
<script type="text/javascript"
	src="<%=basePath%>mgr/js/highlight.pack.js"></script>
<script type="text/javascript" src="<%=basePath%>mgr/js/tabifier.js"></script>
<script src="<%=basePath%>mgr/js/js.js"></script>
<script src="<%=basePath%>mgr/js/jPages.js"></script>



<!-- 登录和注册 -->
<!-- 登录和注册 -->

<!-- 编辑 -->
<script charset="utf-8" src="<%=basePath%>kindeditor/kindeditor.js">
	
</script>

<script charset="utf-8" src="<%=basePath%>kindeditor/lang/zh_CN.js">
	
</script>

<script charset="utf-8"
	src="<%=basePath%>kindeditor/plugins/code/prettify.js">
</script> 
<!-- 编辑 -->
