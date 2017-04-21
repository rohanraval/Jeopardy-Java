<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>

<%
	// Number of teams input validation
		// if number of teams is not entered correctly, redirect to startGame.jsp
	if(request.getParameter("numteams") == null || request.getParameter("numteams") == "" || Integer.parseInt(request.getParameter("numteams")) > 4 || Integer.parseInt(request.getParameter("numteams")) < 1) {
		response.sendRedirect("http://localhost:8080/Jeopardy_v4/startGame.jsp?id=" + request.getParameter("id"));
	}
%>

