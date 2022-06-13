package Source;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class DeleteStudent extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;
        try {
            res.setContentType("text/html");
            out = res.getWriter();
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            String query = String.format("Delete From users where username='%s'", req.getParameter("username"));
            s.executeUpdate(query);
            out.print("<center><h3>Student Deleted</h3><br>");
            out.print("<a href=\"/Java%20Project%20Final/view2.jsp\"><button>Return</button></a></center>");

        } catch (Exception e) {
            out.print(e);

        }
    }
}
