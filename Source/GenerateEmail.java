package Source;

import javax.servlet.http.*;

import java.io.PrintWriter;
import java.sql.*;

public class GenerateEmail extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res)
    {
        PrintWriter out = null;
        try
        {
            out = res.getWriter();
            res.setContentType("text/html");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            String email = null;
            if(req.getParameter("type").equalsIgnoreCase("student"))
            {
                email = String.format("%s.%s@vitstudent.ac.in", req.getParameter("fname").toLowerCase(), req.getParameter("lname").toLowerCase());
            }
            else if(req.getParameter("type").equalsIgnoreCase("administrator"))
            {
                email = String.format("%s.%s@vit.ac.in", req.getParameter("fname").toLowerCase(), req.getParameter("lname").toLowerCase());
            } 
            String query = String.format("update users set email='%s' where username='%s';",email, req.getParameter("user"));
            s.executeUpdate(query);
            out.print("<center><br><br><br><h3>Email generated and stored.</h3><br><br> Your email now: <h3>"+email+"</h3></center>");
        }
        catch(Exception e)
        {
            out.print(e);
        }
    }
}
