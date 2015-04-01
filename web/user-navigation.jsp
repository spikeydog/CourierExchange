
<%-- 
    Document   : user-navigation
    Created on : Mar 6, 2015, 5:19:07 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="#">Create a Shipment</a> <br>
        <a href="#">Schedule and Manage Pickups</a> <br>
        <a href="#">Order Shipping Supplies</a> <br>
          <aside>
  <div id="side">

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
   
    <tr>
        <td>
   <button class="button" type="submit" name="action" value="Login">Login</button>
        </td>
    </tr>

      </table>

  </div>
          </aside>
    </body>
</html>
