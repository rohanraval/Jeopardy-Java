<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="   crossorigin="anonymous"></script>

<title>Jeopardy! Start Game</title>
</head>
<body>
	<%	session.setAttribute("gameid", request.getParameter("id"));
		int gameid = Integer.parseInt(session.getAttribute("gameid") + "");
		String username = (session.getAttribute("username")) + "";
	%>
	<h1>Welcome to Jeopardy Game #<% out.println(gameid); %>, <% out.println(username); %> </h1>
	<br>
	<br>
	<form method=POST action="playGame.jsp?id=<% out.println(gameid); %>">
		<label>Enter the number of teams:</label><br>
		<input type="text" name="numteams" ><br><br>
		<input hidden type="text" name="gameid" value="<% out.println(gameid); %>">
		<input type="submit" class="btn btn-success" value="Start!">
	</form>
	
</body>
</html>
