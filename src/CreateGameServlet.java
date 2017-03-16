

import java.io.BufferedReader;
import java.io.FileInputStream;
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
					+ "<!-- jQuery library -->"
					+ "<script src=\"https://code.jquery.com/jquery-3.1.1.min.js\" integrity=\"sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=\"   crossorigin=\"anonymous\"></script>"
					+ "<!-- Javascript file -->"
					+ "<script src=\"create_question.js\"></script>"
					+ "<!-- CSS file -->"
					+ "<link href=\"stylesheet.css\" rel=\"stylesheet\">"
					+ "</head>");

        out.println("<body> "
					+ "		<table class=\"table-bordered\" border = \"1\">");

        URL url = new URL("http://plato.cs.virginia.edu/~rsr3ve/cs4640/Jeopardy_v3/submission.txt");
        java.io.InputStream is = url.openStream();
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            String text;
            ArrayList<String> submissions = new ArrayList<String>();

            // We read the file line by line into fileData.
            while ((text = reader.readLine()) != null) {
							if(text.isEmpty()) {
            		out.println("</td>"
            				+ "<td><label>Row:</label> <input type = \"text\" name = \"row\" class=\"form-control form-control-inline\"></input></td>"
            				+ "<td><label>Column:</label> <input type = \"text\" name = \"column\" class=\"form-control form-control-inline\"></td>"
            				+ "<td><label>Score:</label> <input type = \"text\" name = \"score\" class=\"form-control form-control-inline\"></td>"
            				+ "</tr>");
            	} else if (text.contains("Submission")) {
            		out.println("<tr><td>");
            	} else if (text.contains("Type"))
            			{
            	}
            	else if (text.contains("Options"))
            			{
            	}
            	else
            	{
            		out.println(text + "<br>");
            	}
        }

        out.println("</table>"
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
