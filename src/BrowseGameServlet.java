

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.InputStream;

/**
 * Servlet implementation class BrowseGameServlet
 */
@WebServlet("/BrowseGameServlet")
public class BrowseGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseGameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//login validation
		if(request.getSession(false) == null) { //user is not logged in!
	    	response.sendRedirect("http://localhost:8080/Jeopardy_v4/LoginServlet"); //redirect to login page
	    	return;
		}
		
		ServletContext context = getServletContext();
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>"
        			+ "<head>"
        			+ "<meta charset=\"utf-8\">"
					+ "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
					+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">"
					+ "<title>Jeopardy!</title>"
					+ "<!-- Bootstrap -->"
					+ "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">"
					+ "</head>");
        
        out.println("<body> "
        		+ " <center> "
        		+ "	<form method=\"post\">"
        	     + " <table class=\"table\" border = \"3\"  table-align =\"center\"> " + "<h3> Browse Menu for " + request.getSession().getAttribute("username") + "</h3>");        

		FileInputStream fis =
		        new FileInputStream("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/postData.txt");
		      InputStreamReader isr = new InputStreamReader(fis);
		      BufferedReader reader = new BufferedReader(isr);		    	

		      String line = null;
		      String user = null;
		      int tempID = 0;
		      int gameID = 0;
		      boolean setter = false;
		      out.println("<tr>"
		    			+ "<th style = \"padding: 0\"><h3>Username</h3></th>" 
          				+ "<td style = \"padding: 0\"><h3>Game ID</h3></td>"
          				+ "<td style = \"padding: 0\"><h3>Options</h3></td>"
          				+ "</tr>");
		      while ((line = reader.readLine()) != null) {
		    	  if(line.contains("GameID")) {
		    		  gameID = Integer.parseInt(line.substring(8));
		    		  tempID = gameID;
		    		  System.out.println(tempID);
		    		  setter = true;
		    		  
		    	  }
		    	  if(line.contains("Username:") && setter == true ) { 
		    		  setter = false;
		    		  user = line.substring(10);
			    	  out.println("<tr>"
			    			+ "<th>" + user + "</th>" 
	          				+ "<td>" + tempID + "&nbsp;&nbsp;"
      						+ "</td>"
      						+ "<td>" 
	          				+ "<form method=\"GET\">");
		    		  out.println("<input hidden name=\"gameid\" value=\"" + gameID + "\" >");
	                  out.println(	"<button class=\"btn btn-primary\" formaction=\"startGame.jsp\" method=\"POST\" > Play </button>");    
			    	  if(request.getSession(false).getAttribute("username").equals(user)) {
			    		  out.println("<button class=\"btn btn-primary\" formaction=\"UpdateGameServlet\"> Update </button>          			"
		                    		+ "<button class=\"btn btn-primary\" formaction=\"DeleteGameServlet\"> Delete </button>");
			          }
			    	  out.println("</form>"
	          				+ "</td>"
	                    	+ "<input hidden name=\"username\" value =\"" + user + "\">"
	                    	+ "<input type = \"hidden\" name=\"username\" value =\"" + line.substring(8) + "\">"
	          				+ "</tr>");	
			    	  }
		      }
        out.println("</table>"
        		+ "<button type=\"submit\" formaction=\"CreateGameServlet\" formmethod=\"GET\" class=\"btn btn-primary\"> Create New Game </button> &nbsp;&nbsp;"
        		+ "<button type=\"submit\" formmethod=\"GET\" formaction=\"LogoutServlet\" class=\"btn btn-primary\"> Logout </button></form> "
            	+ "</center>"
        		+ "</body>"
        		+ "</html>");
        out.close();
        
       // set session to most recent gameID
       HttpSession session = request.getSession(false);
       session.setAttribute("highest_gameid", gameID);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
