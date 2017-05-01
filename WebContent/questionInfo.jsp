<%@ page import="jeopardy.*" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
</head>
<body style="background-color:blue">
	<% 
		int numteams = Integer.parseInt(session.getAttribute("numteams").toString());

		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String rowcol = request.getParameter("rowcol");
		session.setAttribute("answered", session.getAttribute("answered").toString() + rowcol);
		String ans = session.getAttribute("answered").toString();
		System.out.println(ans);
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
		<input hidden type = "text" name ="numteams" value = <%=numteams%>>
		<input type="submit" value="Go Back">
	</form>
	
	<table cellspacing=15 style="color:yellow">
		<tr>
		<% 
		// Display team names
		for(int i = 1; i <= numteams; i++) { %>
		    <th> Team <%=i%></th> 
		<%
		} %>
		 
		<tr>
		<% for(int i = 1; i <= numteams; i++) { %>
		    <td id = "team<%=i%>">0</td>      
		<% } %>
		</tr>
	     <tr>
		<% for(int i = 1; i <= numteams; i++) {%>    
		    <td>
		    <button onclick="inc(<%=i%>)" > + </button> <button onclick="dec(<%=i%>)"> - </button>  </td> 
		   
		    <% } %>
    	 </tr>
		</table>
		
		
		<script type="text/javascript">
		
			function inc(x) {
				var temp = parseInt(document.getElementById("team"+x).innerText);
				temp += 100;
		
				    if((document.getElementById("team"+x).innerText) >= 0)
				       (document.getElementById("team"+x).innerText)=(temp.toString());
			}
			
			function dec(x) {
				var temp = parseInt(document.getElementById("team"+x).innerText);
				temp -= 100;
				if((document.getElementById("team"+x).innerText) > 0)
			   		(document.getElementById("team"+x).innerText)=(temp.toString());
			}
			
	    </script>
	
</body>
</html>