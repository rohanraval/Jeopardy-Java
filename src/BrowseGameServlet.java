

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
        	     + " <table class=\"table\" border = \"3\"  table-align =\"center\"> " + "<h3> Browse Menu </h3>");        

		String filename = "/Users/mac/Desktop/user-info.txt";
		FileInputStream fis =
		        new FileInputStream("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v5/src/postData.txt");
		      InputStreamReader isr = new InputStreamReader(fis);
		      BufferedReader reader = new BufferedReader(isr);		    	

		      String line = null;
		      String user = null;
		      out.println("<tr>"
		    			+ "<th><h3> Username</h3></th>" 
          				+ "<td style = \"padding: 0\"><h3> GameID  </h3></td>"
          				+ "</tr>");
		      while ((line = reader.readLine()) != null) {
		    	  if(line.contains("Username"))
		    	  {
		    	  user = line.substring(10);
		    	  
		    	  }
		    	  if(line.contains("GameID"))
		    	  { 
		    	  out.println("<tr>"
		    			+ "<th >" + user + "</th>" 
          				+ "<td style = \"padding: 0\">     " + line.substring(8) 
          				+ "<form method=\"GET\" action = \"CreateGameServlet\">										"
                    	+ "				<button class=\"btn btn-primary\" formaction=\"UpdateGameServlet\"> Update </button>          			"
          				+ "<button class=\"btn btn-primary\" formaction=\"DeleteGameServlet\"> Delete </button>         			 "
                    	+ "<button class=\"btn btn-primary\"> Play </button>   				 "    
                    	+ " </form>"
          				+ "</td>"
                    	+ "<input hidden name=\"username\" value =\"" + user + "\">"
                    	+ "<input type = \"hidden\" name=\"username\" value =\"" + line.substring(8) + "\">"
          				+ "</tr>");	
		    	  }
		      }
        out.println("</table>"
            	+ "</center>"
        		+ "</body>"
        		+ "</html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
