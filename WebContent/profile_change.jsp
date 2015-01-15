<%@ page language="java" import="com.GraphData.Model.AccountModel" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<%
    	AccountModel account = (AccountModel)session.getAttribute("account");
    %>
	姓名:<%= account.getUsername()%>
	<br>
    <form action="fillProfile" method="post">
		生日:<input type="date" name="birthday"><br>
		大学:<input type="text" name="college"><br>
    	专业:<input type="text" name="major"><br>
    	爱好:<input type="text" name="hobby"><br>
    	<input type="submit">
    </form>
</body>
</html>