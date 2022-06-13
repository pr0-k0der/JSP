<%@ page import="java.sql.*" %>
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
            ResultSet rs = s.executeQuery("select * from users;"); 
        %>
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
        //out.print(q);
        ResultSet rs1 = s1.executeQuery(q);
        rs1.next();
        String eemail = rs1.getString(6);
        out.println("<center><br><br>Your Details:<br>First Name: "+rs1.getString(1)+"<br>Last Name: "+rs1.getString(2)+"<br>Email: "+eemail+"<br></center>");
        if(eemail.equalsIgnoreCase("not generated"))
        {
            out.print("<center><br><b>Your email has not been generated.</b><br><br><a href='./generate?fname="+rs1.getString(1)+"&lname="+rs1.getString(2)+"&type="+rs1.getString(5)+"&user="+rs1.getString(3)+"'><button>Generate Email</button></a></center>");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
        <br>
        <br>
        <h3>Adminstrator Options</h3>        
        <a href='./create.html'><button>Create a Student Record</button></a>
        <a href='./modify.html'><button>Modify a Student Record</button></a>
        <a href='./delete.html'><button>Delete a Student Record</button></a>
        <a href='./index.html'><button>Log Out</button></a>
    </center>    
</body>
</html>