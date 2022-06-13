package Source;

import javax.servlet.http.*;
import java.sql.*;
import java.io.PrintWriter;


class InvalidUsernameException extends Exception{
    public InvalidUsernameException(String msg)
    {
        super(msg);
    }
}

public class CreateStudent extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;
        try {
            out = res.getWriter();
            res.setContentType("text/html");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();

            String uname = req.getParameter("username");
            if(uname.contains(" "))
            {
                throw new InvalidUsernameException("<center><br><br><h2>No spaces allowed in username!</h2></center>");
            }
            
            s.executeUpdate("insert into users values ('" + req.getParameter("firstname") + "','"
                    + req.getParameter("lastname") + "','" + req.getParameter("username") + "','"
                    + req.getParameter("password") + "','" + req.getParameter("type") + "', 'not generated');");
            out.print("<center><h3>Student Created</h3><br>");
            out.print("<a href=\"/Java%20Project%20Final/view2.jsp"
            + "\"><button>Return</button></a></center>");
        } catch (InvalidUsernameException e) {
            out.print(e.getMessage());
            out.print("<center><a href=\"/Java%20Project%20Final/view2.jsp\"><button>Return</button></a></center>");
        }
        catch(Exception e)
        {
            out.print(e);
        }
    }
}
