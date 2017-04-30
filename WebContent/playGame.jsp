<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="jeopardy.*, java.io.File, java.util.*, javax.xml.parsers.DocumentBuilder, javax.xml.parsers.DocumentBuilderFactory, org.omg.CORBA.portable.InputStream, org.w3c.dom.Document, org.w3c.dom.Element, org.w3c.dom.Node, org.w3c.dom.NodeList " %>

<%
	String gameid = session.getAttribute("gameid") + "";
	session.setAttribute("numteams", request.getParameter("numteams"));
	String numteams = session.getAttribute("numteams") + "";
	// Number of teams input validation
		// if number of teams is not entered correctly, redirect to startGame.jsp
	if(numteams == null || numteams == "" || Integer.parseInt(numteams) > 4 || Integer.parseInt(numteams) < 1) {
		response.sendRedirect("http://localhost:8080/Jeopardy_v4/startGame.jsp?id=" + gameid);
	}
%>
<%
	ArrayList<questionBean> qList = new ArrayList<questionBean>();

	int rowMax = 0;
	int colMax = 0;
	
	try {
		File inputFile = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/gameData.xml");
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	    Document doc = docBuilder.parse(inputFile);
	    Element rootElement = doc.getDocumentElement();
	    

	    NodeList nList = doc.getElementsByTagName("game");
	    for (int i = 0; i < nList.getLength(); i++) 
	    {
	       Node nd = nList.item(i);
	       if (nd.getNodeType() == Node.ELEMENT_NODE) 
	       {
	          Element ele = (Element)nd;
	      	System.out.println(gameid);
	          if(ele.getAttribute("game_id").equals(gameid)) {
	        	  NodeList cList = ele.getChildNodes();
	        	  for(int j = 0; j < cList.getLength(); j++ ) {
	        		  Node cd = cList.item(j);
	        		  if (cd.getNodeType() == Node.ELEMENT_NODE)
	        		  {

	        	          Element ce = (Element)cd;
	        			  String question = ce.getElementsByTagName("question").item(0).getTextContent();
	        			  String answer = ce.getElementsByTagName("answer").item(0).getTextContent();
	        			  int row = Integer.parseInt(ce.getElementsByTagName("row").item(0).getTextContent());
	        			  int col = Integer.parseInt(ce.getElementsByTagName("col").item(0).getTextContent());
	        			  int score = Integer.parseInt(ce.getElementsByTagName("score").item(0).getTextContent());

	        			  if(row > rowMax)
	        				  rowMax = row;
	        			  if(col > colMax)
	        				  colMax = col;
	        			  
	        			  questionBean qb = new questionBean(question, answer, row, col, score);
	        			  qList.add(qb); 
	        		  }
	        		  
				
	        	  }
	        	  //questionBean bean = new questionBean();
	          }
	    	  
	       }
	    }
	 } catch (Exception e) {
	    e.printStackTrace();
	 }
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Play Game</title>
</head>
<body>
	<%
		int[][] board_scores = new int[rowMax+1][colMax+1];
		String[][] board_questions = new String[rowMax+1][colMax+1];
		String[][] board_answers = new String[rowMax+1][colMax+1];
	
		//set grid cells to blank first
	    for(int currRow = 1; currRow <= rowMax; currRow++) {
			for(int currCol = 1; currCol <= colMax; currCol++) {
				board_scores[currRow][currCol] = 0;
				board_questions[currRow][currCol] = "hi";
				board_answers[currRow][currCol] = "hello";
			}
		}
		
	  //populate required grid cells to proper score value
		for(int i = 0; i < qList.size(); i++) {
			board_scores[qList.get(i).getRow()][qList.get(i).getColumn()] = qList.get(i).getScore();
			board_questions[qList.get(i).getRow()][qList.get(i).getColumn()] = qList.get(i).getQuestion();
			board_answers[qList.get(i).getRow()][qList.get(i).getColumn()] = qList.get(i).getAnswer();
		}
	  
	  	for(int i = 0; i < board_questions.length; i++){
	  		for(int j =0; j < board_questions[i].length; j++){
	  			System.out.println("Row:" + i + " Col:" + j + " question:" + board_questions[i][j]);
	  		}
	  	}
	  	System.out.println(rowMax + "," + colMax);
	  
  	%>
  	<center>
	<table style="background-color: blue; width:50%;">
		<%
			for(int currRow = 1; currRow <= rowMax; currRow++) {
				out.println("<tr style =\"border:1px solid black;\" width = \"100%\">");
		%>
		<form method=POST action="questionInfo.jsp">
		<%
				for(int currCol = 1; currCol <= colMax; currCol++) {
					out.println("<td style =\"border:1px solid black;\" ><font color=\"yellow\"><br><center><input type=\"submit\" name=\"score\" value=\"" + board_scores[currRow][currCol] + "\"></center></br></font></td>");
					System.out.println("Score:" + board_scores[currRow][currCol]);
					out.println("<input hidden type=\"text\" name=\"question\" value=\"" + board_questions[currRow][currCol] + "\" >");
					System.out.println("Question:" + board_questions[currRow][currCol]);
					out.println("<input hidden type=\"text\" name=\"answer\" value=\"" + board_answers[currRow][currCol] + "\" >");
					System.out.println("Answer:" + board_answers[currRow][currCol]);

				}
	    		out.println("</tr>");
	    %> 	
	    </form>
	    <%
			}
		%>
	</table>
	</center>
</body>
</html>

	