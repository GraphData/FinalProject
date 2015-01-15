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
<%
    AccountProfile profile = (AccountProfile)request.getAttribute("profile");
%>
	<table border="0" style="table-layout:fixed">
		<tr>
			<td width="30%">
				姓名:
			</td>
			<td>
				<%= account.getUsername()%>
			</td>
		</tr>
		<tr>
			<td width="30%">
				生日:
			</td>
			<td>
				<%=profile.getBirthday()%>
			</td>
		</tr>
		<tr>
			<td width="30%">
				爱好:
			</td>
			<td>
				<%=profile.getHobby()%>
			</td>
		</tr>
		<tr>
			<td width="30%">
				学校:
			</td>
			<td>
				<%=profile.getCollege()%>
			</td>
		</tr>
		<tr>
			<td width="30%">
				专业:
			</td>
			<td>
				<%=profile.getMajor()%>
			</td>
		</tr>
		<tr>
			<td width="30%">
				<p><a href="http://localhost:8080/GraphData/changeProfile">修改资料</a></p>
			</td>
		</tr>
	</table>

     
</body>
</html>