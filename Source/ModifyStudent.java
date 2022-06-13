package Source;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.http.*;

public class ModifyStudent extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;
        try {
            out = res.getWriter();
            String uname = req.getParameter("username");
            String newf = req.getParameter("newfirstname");
            String newl = req.getParameter("newlastname");
            res.setContentType("text/html");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            String query = String.format("Update users set fname = '%s', lname='%s' where username='%s';", newf, newl,
                    uname);
            // out.print(query);
            s.executeUpdate(query);
            out.print("<center><h3>User updated</h3><br>");
            out.print("<a href=\"/Java%20Project%20Final/view2.jsp\"><button>Return</button></a></cenmer>");
        } catch (Exception e) {
            out.print(e);
        }
    }
}
