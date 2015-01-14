<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.GraphData.Model.AccountModel" %>
<%@ page import="com.GraphData.Model.AccountProfile" %>
<%@ page import="com.GraphData.Model.Newsfeed" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<style type="text/css">
<!--
body {
    font-family:'Lucida Sans Unicode', 'Lucida Grande', sans-serif;
    font-size:14px;
}
 
h1 {
    margin-top:0px;
    margin-bottom:8px;
}
 
/* 链接 */
a {
    text-decoration:none;
    color:#1c00ff;
}
 
a:hover {
    color:#5f00e4;
}
fieldset.search {
    padding: 0px;
    border: none;
    width: 232px;
    background:#e0e0e0;
}
 
fieldset.search:hover {
    background: #a8a8a8;
}
.search input, .search button {
    border: none;
    float: left;
}
.search input.box {
    height: 28px;
    width: 200;
    margin-right: 0px;
    padding-right: 0px;
    background: #e0e0e0;
    margin: 1px;
}
.search input.box:focus {
    background: #e8e8e8 ;
    outline: none;
}
.search button.btn {
    border: none;
    width: 28px;
    height: 28px;
    margin: 0px auto;
    margin: 1px;
    background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_blue.png) no-repeat top right;
}
.search button.btn:hover {
    background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_black.png) no-repeat bottom right;
}
 
/* 文章样式 */
.article {
 
}
-->
</style>
</head>
<body>
<div>
<h2>搜索框</h2>
<form action="searchUser">
    <fieldset class="search">
         <input type="text" class="box" name="name" id="s" class="inputText" placeholder="" x-webkit-speech>
          <button class="btn" title="SEARCH"> </button>
    </fieldset>
</form>
</div>
<p><a href="http://localhost:8080/GraphData/profile">个人资料</a></p>

<form action="newsfeed_post" method="post">
	    	新鲜事:<input type="text" name="content"><br>
	    <input type="submit">
	</form>
<table border="1">
<%
	try{
		List<AccountModel> accounts = (List<AccountModel>) request.getAttribute("accounts");
		for(int i = 0 ; i < accounts.size(); i++)
		{
			%>
			<tr>
			<td>
				<%=accounts.get(i).getUsername()%>
			</td>
			<td>
				<%=accounts.get(i).getPassword() %>
			</td>
			<td>
				<form action="follow" method="post">
	    			<button type="submit" value="<%=accounts.get(i).getUsername()%>" name="name">关注</button>
				</form>
			</td>
			</tr>
			<%
		}	
	}
	catch(Exception e){

	}
%> 
</table>
<table border="1">
<%
	try{
		List<Newsfeed> news = (List<Newsfeed>) request.getAttribute("news");
		for(int i = 0 ; i < news.size(); i++)
		{
			%>
			<tr>
			<td>
				<%=news.get(i).getContent()%>
			</td>
			<td>
				<%=news.get(i).getPublisher() %>
			</td>
			<td>
				<%=news.get(i).getTime() %>
			</td>
			</tr>
			<%
		}	
	}
	catch(Exception e){

	}
%> 
</table>
关注的人：
<table border="1">
<%
	try{
		List<AccountProfile> follows = (List<AccountProfile>) request.getAttribute("follows");
		for(int i = 0 ; i < follows.size(); i++)
		{
			%>
			<tr>
			<td>
				<%=follows.get(i).getUsername()%>
			</td>
			<td>
				<form action="cancel_follow" method="post">
	    			
	    			<button type="submit" value="<%=follows.get(i).getUsername()%>" name="name">取消关注</button>
				</form>
			</td>

			</tr>
			<%
		}	
	}
	catch(Exception e){

	}
%> 
</table>
</body>
</html>