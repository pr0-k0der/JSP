package Source;

import java.io.PrintWriter;
// import java.net.http.HttpRequest;
// import java.net.http.HttpResponse;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class LoginVerf extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            resp.setContentType("text/html");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            out.println("<html><head><link rel=\"stylesheet\" href='./styles.css'></head><body>");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            out.println("<center><br><br>");
            ResultSet rs = s.executeQuery("select * from users where (username = '" + username + "' or email='"+ username +"') and password='"+password+"';");
            if (!rs.next()) {
                // out.print("");
                out.println("<h1>Invalid credentials</h1></center></html>");
            } else {
                out.print("<br><br><br>Logged in User: " + username);
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String type = rs.getString(5);
                out.print("<br>Name: " + fname + " " + lname + "<br>Type: " + type+"</center><br><br>");
                // out.print("<center>You have successfully logged in!<br><br>");
                if (type.equalsIgnoreCase("student")) {
                    out.println(
                        "<center>You have successfully logged in!<br><br><a href=\"/Java%20Project%20Final/view.jsp?username="
                        + username +"&type=Student"
                        + "\"><button>View user details!</button></a></center>");
                } else if (type.equalsIgnoreCase("administrator")) {
                    out.println(
                            "<center>You have successfully logged in!<br><br><a href='/Java%20Project%20Final/view2.jsp?username="
                                    + username +"&type=Administrator'><button>View user details!</button></a></center>");
                }
            }
        } catch (Exception e) {
            out.print(e);
        }
        finally
        {
            out.print("</body></html>");
        }
    }

}
