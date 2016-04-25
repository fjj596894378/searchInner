<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
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
  </body>
</html>
