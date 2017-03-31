import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteGameServlet
 */
@WebServlet("/DeleteGameServlet")
public class DeleteGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletContext context = getServletContext();

		  response.setContentType ("text/html");
		 PrintWriter out = response.getWriter();
		 
	        FileWriter fileoutput = new FileWriter("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/postData.txt", true);

			String username = request.getSession(false).getAttribute("username").toString();
			int gameid = Integer.parseInt(request.getParameter("gameid"));
			out.println("Deleting game with: Username: " + username + " and GameID: " + gameid);	
			/*FileInputStream fis =
			        new FileInputStream("/Users/mac/Desktop/postData.txt");

			      InputStreamReader isr = new InputStreamReader(fis);

			      BufferedReader reader = new BufferedReader(isr);		    	
			      String line;
			      String emptier = "";
			      int li = 0;
			      int li2 = 0;
			      int alerter = 0;
			      //line.substring(10) == name)

			      while ((line = reader.readLine()) != null) {
			    	  System.out.println("works");
			    	 line = line.replace("GameID: ", "replace");
			      }/Users/mac/Desktop/postData.txt
			
			*/
			File inputFile = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/postData.txt");
			File tempFile = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/postData2.txt");

			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
			String lineToRemove = "GameID: "+ gameid;
			String currentLine;

			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
			    String trimmedLine = currentLine.trim();		
			    
			    if(trimmedLine.equals(lineToRemove)) continue;
			    	writer.write(currentLine + System.getProperty("line.separator"));	
			  
			}
			writer.close(); 
			reader.close(); 
			boolean successful = tempFile.renameTo(inputFile);

			out.println("<br><br><form method=\"GET\" action=\"BrowseGameServlet\"><button type=\"submit\">Go Back</button></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}