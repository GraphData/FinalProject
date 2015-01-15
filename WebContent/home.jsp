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
body,div {
	margin-top:10px;
	padding:0;
}
div {
}.left {
	float:left;
	width:200px;
}.right {
	float:right;
	width:200px;
}.center {
	margin-left:200px;
	margin-right:200px;
}

#head {
	height:40px;
	margin-left:10px;
}

#head p.right {
	position:absolute;
	right:0px;
	top:0px;
}

#head p.left {
	position:absolute;
	left:50px;
	top:0px;
}

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
    width: 180px;
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
    height: 26px;
    width: 134px;
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
    width: 32px;
    height: 28px;
    margin: 1px;
    background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_blue.png) no-repeat top right;
}
.search button.btn:hover {
    background: url(http://sandbox.runjs.cn/uploads/rs/339/livk7pl5/search_black.png) no-repeat bottom right;
}

</style>
</head>
<body>
	<div id="head">
		<%
	    	AccountModel account = (AccountModel)session.getAttribute("account");
	    %>
		<p class="left">欢迎：<%=account.getUsername()%></p>
		<p class="right"><a href="http://localhost:8080/GraphData/profile">个人资料</a></p>
	</div>

	<div class="left">
		<h3>搜索好友</h3>
		<form action="searchUser">
		    <fieldset class="search">
		         <input type="text" class="box" name="name" id="s" class="inputText" placeholder="" x-webkit-speech>
		          <button class="btn" title="SEARCH"> </button>
		    </fieldset>
		</form>

		<form action="newsfeed_post" method="post">
			<div id="post">
				<h3>新鲜事</h3>
		    	<input type="text" name="content"><br>
		    	<button type="submit">发布</button>
		    </div>
		</form>
		
		<table border="0" style="table-layout:fixed">
		<%
			try{
				List<AccountModel> accounts = (List<AccountModel>) request.getAttribute("accounts");
				for(int i = 0 ; i < accounts.size(); i++)
				{
					%>
					<tr>
					<td width="70%">
						<%=accounts.get(i).getUsername()%>
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
	</div>

<div class="right">
关注的人：
<table border="0" style="table-layout:fixed">
<%
	try{
		List<AccountProfile> follows = (List<AccountProfile>) request.getAttribute("follows");
		for(int i = 0 ; i < follows.size(); i++)
		{
			%>
			<tr>
			<td width="60%">
				<a href="http://localhost:8080/GraphData/home?name=<%=follows.get(i).getUsername()%>"><%=follows.get(i).getUsername()%></a>
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
</div>
<div class="center">
<table border="0" style="width:60%; margin:auto">
<%
	try{
		List<Newsfeed> news = (List<Newsfeed>) request.getAttribute("news");
		for(int i = 0 ; i < news.size(); i++)
		{
			%>
			<tr>
				<td  style="font-size:20px">
					<%=news.get(i).getPublisher() %>
				</td>
			</tr>
			<tr>
				<td style="font-size:10px">
					<%=news.get(i).getTime() %>
				</td>
			</tr>
			<tr>
				<td>
					<%=news.get(i).getContent()%>
				</td>
			</tr>
			<tr>
				<td style="height:5px">
				</td>
			</tr>
			
			<%
		}	
	}
	catch(Exception e){

	}
%> 
</table>
</div>

</body>
</html>