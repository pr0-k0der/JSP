<%@ page import="java.sql.*, java.io.*" %>
<%@ page import="jdk.internal.icu.impl.CharacterIteratorWrapper" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Students</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <center>
        <%
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
            Statement s = conn.createStatement();
            //s.executeUpdate("insert into users values ('"+req.getParameter("firstname") +"','"+req.getParameter("lastname")+"','"+req.getParameter("username")+"','"+req.getParameter("password")+"');");
            ResultSet rs = s.executeQuery("select * from users where type='Student';");
            
        %>
        <br>
        <br>
        <br>
            <table cellspacing='10' align='center'>
            <tr><td><b>First Name</b></td><td><b>Last Name</b></td><td><b>Username</b></td></tr>
        <%
            while(rs.next())
            {
                String fname = rs.getString(1);
                String lname = rs.getString(2);
                String username = rs.getString(3);
                out.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", fname, lname, username));
            }
        %>
        </table>
        <%    
        Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/java", "root", "admin");
        Statement s1 = conn2.createStatement();
        String q = String.format("select * from users where username='%s' or email='%s'", request.getParameter("username"), request.getParameter("username"));
        //String q = "select * from users where username='"+request.getParameter("username")+"';";
        //out.print(q);
        ResultSet rs1 = s1.executeQuery(q);
        rs1.next();
        String eemail = rs1.getString(6);
        out.println("<center><br><br>Your Details:<br>First Name: "+rs1.getString(1)+"<br>Last Name: "+rs1.getString(2)+"<br>Email: "+eemail+"<br></center>");
        if(eemail.equalsIgnoreCase("not generated"))
        {
            out.print("<center><br><b>Your email has not been generated.</b><br><br><a href='./generate?fname="+rs1.getString(1)+"&lname="+rs1.getString(2)+"&type="+rs1.getString(5)+"&user="+rs1.getString(3)+"'><button>Generate Email</button></a></center>");
        }
        %>
            
            <br>
            <h3>Options</h3>
        <%
            //out.print("<center><br><br><h2>Registered user</h2>");
            out.print("<center><a href='./index.html'><button>Log Out</button></a></center>");
        } catch (Exception e) {
            out.print(e);
        }
        %>
    </center>    
</body>
</html>