package jeopardy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
     
        out.println("<body style=\"padding-left:30px;\">"
        		+ "		<h3>Welcome to Jeopardy!</h3>"
        		+ "		<h4>by Vamshi Garikapati (vkg5xt) and Rohan Raval (rsr3ve) </h4><br>"
        		+ "		<form method='post' class=\"form\">"
        		+ "			<h5>Please Login if you have an account</h5>"
        		+ "			<label>Username: </label> <input type = \"text\" name = \"username\" /><br>"
        		+ "			<label>Password: </label> <input type = \"text\" name = \"password\" /><br><br>"
        		+ "			<input class=\"btn btn-primary\" name=\"login\" type = \"submit\" value = \"Login\" />"
        		+ "		</form> "
        		+ "		<br/><br/>"
        		+ "		<form method='post' class=\"form\">"
        		+ "			<h5>Or Sign Up for a new account</h5>"
        		+ "			<label>Username: </label> <input type = \"text\" name = \"username\" /><br>"
        		+ "			<label>Password: </label> <input type = \"text\" name = \"password\" /><br><br>"
        		+ "			<input class=\"btn btn-primary\" name=\"login\" type = \"submit\" value = \"Sign Up\" />"
        		+ "		</form>"
        		+ "	</body>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext context = getServletContext();
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
    	String password = request.getParameter("password");
        
        // check if user already has account
        if(request.getParameter("login").equals("Login")) { 
        	
        	boolean isLoginValid = false;
        	
//        	try { // try to read in user-info.txt file, line by line, to check if account info is valid
//                File file = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/user-info.txt");
//                FileReader fr = new FileReader(file);
//                BufferedReader br = new BufferedReader(fr);                                                 
//                String data;
//
//                while((data = br.readLine()) != null) 
//                {
//                    if(data.equals(loginInfo)) {
//                    	isLoginValid = true;
//                    	break;
//                    }
//                }                                
//            } catch(IOException e) {
//                System.out.println("invalid filepath!");
//            }
        	
        	try {
            	File inputFile = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/user-info.xml");
            	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    	        Document doc = docBuilder.parse(inputFile);
                Element rootElement = doc.getDocumentElement();
                
                NodeList nList = doc.getElementsByTagName("user");
                for (int i = 0; i < nList.getLength(); i++) 
                {
                   Node nd = nList.item(i);
                   if (nd.getNodeType() == Node.ELEMENT_NODE) 
                   {
                      Element ele = (Element)nd;
                      String this_username = ele.getAttribute("username");
                      String this_password = ele.getAttribute("password");
                      if (this_username.equals(username) && this_password.equals(password))
                    	  isLoginValid = true;
                   }
                }
             } catch (Exception e) {
                e.printStackTrace();
             }
        	
        	
        	if(isLoginValid == false) { //if login invalid... 
        		out.println("<p>INVALID LOGIN!</p>");			// FIGURE THIS OUT!!!!!
        		HttpSession session = request.getSession(false);
        		
            	if(session != null) {
            		System.out.println("invalidated");
            		session.invalidate(); // if a session exists, delete it
            	}
            	response.sendRedirect("http://localhost:8080/Jeopardy_v4/LoginServlet");
            	
        	} else {
        		//if old session exists, delete it
            	HttpSession session = request.getSession(false);
            	if(session != null)
            		session.invalidate(); // if a session exists, delete it
            	
            	// create a new session
            	session = request.getSession(true);
            	// put username data into session
        	    session.setAttribute("username", request.getParameter("username"));
        	    session.setAttribute("password", request.getParameter("password"));

        		response.sendRedirect("http://localhost:8080/Jeopardy_v4/BrowseGameServlet");

        	}
        }
        
        // else user is signing up (creating new account)
        else {
        	// Write Sign Up Post data to user-info.xml file    
        	
        	File inputFile = new File("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/user-info.xml");

            try{
    	        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    	        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    	        Document doc;
    	        Element rootElement;
    	        
    	        if (! inputFile.exists()) {
    	            doc = docBuilder.newDocument();
    	            // root element
    	            rootElement = doc.createElement("users");
    	            doc.appendChild(rootElement);
    	        }
    	        
    	        else {
    	            doc = docBuilder.parse(inputFile);
    	            rootElement = doc.getDocumentElement();
    	        }
    	        
    	        // create new game element
    	        Element user = doc.createElement("user");
    	        user.setAttribute("username", username);
    	        user.setAttribute("password", password);
    	        rootElement.appendChild(user);
    		
        		// write the content into xml file
    	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
    	        Transformer transformer = transformerFactory.newTransformer();
    	        DOMSource source = new DOMSource(doc);
    	        StreamResult result = new StreamResult(inputFile);
    	        transformer.transform(source, result);
            } catch(Exception e) {
                e.printStackTrace();
            }

        	
        	
        	//if old session exists, delete it
        	HttpSession session = request.getSession(false);
        	if(session != null) {
        		session.invalidate(); // if a session exists, delete it
        	}
        	
        	// create a new session
        	session = request.getSession(true);
        	// put username data into session
    	    session.setAttribute("username", request.getParameter("username"));

    		response.sendRedirect("http://localhost:8080/Jeopardy_v4/BrowseGameServlet");

        }
		
	}

}