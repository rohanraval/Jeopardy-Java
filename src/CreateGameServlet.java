// Vamshi Garikapati (vkg5xt) and Rohan Raval (rsr3ve)

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.InputStream;

/**
 * Servlet implementation class CreateGameServlet
 */
@WebServlet("/CreateGameServlet")
public class CreateGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGameServlet() {
        super();
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
					+ "<!-- jQuery library -->"
					+ "<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\" integrity=\"sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=\"   crossorigin=\"anonymous\"></script>"
					+ "<!-- Javascript file -->"
					+ "<script src=\"create_question.js\"></script>"
					+ "<!-- CSS file -->"
					+ "<link href=\"stylesheet.css\" rel=\"stylesheet\">"
					+ "</head>");
        
        out.println("<body> "
        		+ " <center> "
        		+ "	<form method=\"post\">"
					+ "	<table class=\"table\" border = \"3\" table-align =\"center\"> " + "<h3> Q/A Jeopardy Menu by Vamshi Garikapati and Rohan Raval </h3>");
        
        URL url = new URL("http://plato.cs.virginia.edu/~rsr3ve/cs4640/Jeopardy_v3/submission.txt");
        java.io.InputStream is = url.openStream();
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text;
            int counter = 0;
                        
            while ((text = reader.readLine()) != null) {
            	counter++;
            	if(text.isEmpty()) {
            		out.println("</td>"
            				+ "<td><label>Row </label> <input type = \"text\" name = \"row" + counter + "\" style=\"width:40%\"></input></td>"
            				+ "<td><label>Column </label> <input type = \"text\" name = \"column" + counter + "\" style=\"width:40%\"></input></td>"
            				+ "<td><label>Score </label> <input type = \"text\" name = \"score" + counter + "\" style=\"width:40%\"></input></td>"
            				//+ "<td><label>Category:</label> <input type = \"text\" name = \"category" + "\"  style=\"td-align:justify; width:80px;\"></input></td>"
            				+ "</tr>");
            	} else if (text.contains("Submission")) {
            		out.println("<tr><td name=\"submission\" >");
            	} else if(text.contains("Submitted Question")) { 
            		out.println("<strong>"+text.substring(0,19)+"</strong>  <input name=\"question" + counter + "\" value=\" " + text.substring(22)+"\" style=\"width:70%\"><br>");
            	} else if(text.contains("Correct Option")) {
            		out.println("<strong>"+text.substring(0,15)+"</strong>  <input name=\"answer" + counter + "\" value=\" " + text.substring(18)+"\" style=\"width:70%\"><br>");
            	} else if(text.contains("Submitted Answer")) {
            		out.println("<strong>"+text.substring(0,17)+"</strong>  <input name=\"answer" + counter + "\" value=\" " + text.substring(20)+"\" style=\"width:70%\"><br>");
            	}
        	}
            out.println("<input hidden name=\"count\" value=\"" + counter + "\">");
        }
        out.println("</table>"
        		+ "<button type=\"submit\" class=\"btn btn-primary\" formaction=\"http://plato.cs.virginia.edu/~rsr3ve/cs4640/Jeopardy_v3/create_question.php\">Add Q/A</button>  "
        		+ "<input type=\"submit\" value=\"Create Game\" class=\"btn btn-primary\">"
        		+ " </form>"
        		+ "</center>"
        		+ "</body>"
        		+ "</html>");
        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext context = getServletContext();
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter();
        
        // FILE OUTPUT BASED ON POST DATA
        
        FileWriter fileoutput = new FileWriter("/Users/Rohan/Documents/cs4640/apache/webapps/cs4640/Jeopardy_v4/src/postData.txt");
        
//        String[] rows = request.getParameterValues("row");
//        String[] cols = request.getParameterValues("column");
//        String[] scores = request.getParameterValues("scores");
//        String[] questions = request.getParameterValues("question");
//        String[] answers = request.getParameterValues("answer");
        
        for(int i = 0; i <= Integer.parseInt(request.getParameter("count")); i++) {
        	String question = request.getParameter("question" + i);
        	String answer = request.getParameter("answer" + i);
        	String row = request.getParameter("row" + i);
        	String col = request.getParameter("column" + i);
        	String score = request.getParameter("score" + i);
        	
        	if(row != null && row != "" && col != null && col != "" && score != null && score != "") {
        		fileoutput.write("Question: " + question + "; ");
        		fileoutput.write("Answer: " + answer + "; ");
        		fileoutput.write("Row: " + row + "; ");
        		fileoutput.write("Col: " + col + "; ");
        		fileoutput.write("Score: " + score + ". \n");
        	}
        	
        }
        
        
        /*String[][] positions = new String[rows.length-1][cols.length-1];
        
        //FILL GRID WITH POST DATA
    	for(int currRow = 0; currRow < rows.length-1; currRow++) {
    		for(int currCol = 0; currCol < cols.length-1; currCol++) {
    			
    		}
    	}*/
    	fileoutput.close();
	}

}