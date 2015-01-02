<%@ page language="java" import="com.GraphData.Model.*" contentType="text/html; charset=utf-8"
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
    username:<%= account.getUsername()%>
     <br>
     password:<%= account.getPassword() %>
<%
    AccountProfile profile = (AccountProfile)request.getAttribute("profile");
    %>
    	生日:<%= profile.getBirthday()%>
    	爱好:<%= profile.getHobby()%>
    	学校:<%= profile.getCollege()%>
    	专业:<%= profile.getMajor()%>
     <br>
     <p><a href="http://localhost:8080/GraphData/changeProfile">修改资料</a></p>
</body>
</html>