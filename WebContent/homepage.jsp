<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.GraphData.Model.Newsfeed" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>homepage</title>
</head>
<body>
<table border="0">
<%
	try
	{	
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
</body>
</html>