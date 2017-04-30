<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
</head>
<body style="background-color:blue">
	<% 
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
	%>
	<h2 style="color:yellow; top:50px; left:50px;"><% out.println(question); %></h2>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
	    $("#show").click(function(){
	        $("p").show();
	    });
	});
	</script>
	<p style="display:none; color: yellow;"><% out.println(answer); %></p>
	


	<button id="show" > Show Answer!</button>
	<form action="playGame.jsp">
		<input type="submit" value="Go Back">
	</form>
	
	
	
</body>
</html>