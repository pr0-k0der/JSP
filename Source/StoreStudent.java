package Source;

import javax.servlet.http.*;
// import javax.servlet.*;

import java.io.*;
import java.sql.*;

class InvalidUsernameException extends Exception{
    public InvalidUsernameException(String msg)
    {
        super(msg);
    }
}

class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String msg)
    {
        super(msg);
    }
}

public class StoreStudent extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        PrintWriter out = null;
        try {
            out = res.getWriter();
            res.setContentType("text/html");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            String uname = req.getParameter("username");
            String pword = req.getParameter("password");
            if(uname.contains(" "))
            {
                throw new InvalidUsernameException("<center><br><br><h2>No spaces allowed in username! :( </h2></center>");
            }
            if (pword.length()<5)
            {
                throw new InvalidPasswordException("<center><br><br><h2>Password not long enough! :( </center>");
            }
            s.executeUpdate("insert into users values ('" + req.getParameter("firstname") + "','"
                    + req.getParameter("lastname") + "','" + req.getParameter("username") + "','"
                    + req.getParameter("password") + "','" + req.getParameter("type") + "', 'not generated');");
            out.print("<center><br><br><h2>Registered user!</h2>");
            out.print("<br><br><h3>Generate Email for this user.</h3>");
            out.print("<a href='./generate?fname="+req.getParameter("firstname")+"&lname="+req.getParameter("lastname")+"&type="+req.getParameter("type")+"&user="+req.getParameter("username")+"'><button>Generate</button></a></center>");
            out.print("<br><br><center><h3>Click on the button to go back to main page and log in!</h3>");
            out.print("<a href='./index.html'><button>Login</button></a></center>");
        } catch (InvalidUsernameException e) {
            out.println(e.getMessage());
            out.print("<center><br><br><br><a href='./register.html'><button>Try Again</button></a></center>");
        }
        catch (InvalidPasswordException e) {
            out.println(e.getMessage());
            out.print("<center><br><br><br><a href='./register.html'><button>Try Again</button></a></center>");
        }
        catch(Exception e)
        {
            out.print(e);
        }
    }
}