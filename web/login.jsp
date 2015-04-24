
<%-- 
    Document   : about
    Created on : Sep 16, 2014, 5:41:09 PM
    Author     : pooja
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./CSS/Styling.css" rel="stylesheet">
        <title>Login  Charlotte savings and loan pvt ltd!</title>
        <%@ include file="header.jsp" %>
  
    </head>
        <body>
 
    
<h1>Login Form</h1>
<p>Please enter a username and password to continue.</p>
<form action="userController" method="post">
    <table>
        <tr>
            <td>    
    <label>User Id/Email</label>
            </td>
            <td>
    <input type="text" name="email"><br>
            </td>
    </tr>
    <tr>
        <td>
    <label>Password</label>
        </td>
        <td>
    <input type="password" name="j_password"><br>
        </td>
    </tr>
    <label>&nbsp;</label>
    
    </table>
   
  <button class="button" type="submit" name="action" value="Login">Login</button>
</form>
        <%@ include file="footer.jsp" %>
    </body>
</html>