<%-- 
    Document   : index
    Created on : Mar 6, 2015, 5:18:28 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <link href="./CSS/Styling.css" rel="stylesheet">
        <title>Online freeLance courier Exchange</title>
         <%@ include file="header.jsp" %> 
    </head>
     <body>
   <form action="userController" method="post">
   <table>
        <tr>
            <td>       
     <label>Email</label>
            </td>
            <td>
    <input type="email" name="email" required><br>
            </td>
        </tr>
         <tr>
             <td>
    <label>First Name</label>
             </td>
             <td>
    <input type="text" name="firstName" required><br>
             </td>
         </tr>
         <tr>
             <td>
    <label>Last Name</label>
             </td>
             <td>
    <input type="text" name="lastName" required><br>  
             </td>
         </tr>
           <tr>
             <td>
    <label>Address One</label>
             </td>
             <td>
    <input type="text" name="addOne" required><br>  
             </td>
         </tr>
    <label>&nbsp;</label>
       <tr>
             <td>
    <label>Address Two</label>
             </td>
             <td>
    <input type="text" name="addTwo" ><br>  
             </td>
         </tr>
         <tr>
    <label>&nbsp;</label>
           <td>
    <label>City</label>
             </td>
             <td>
    <input type="text" name="city" required ><br>  
             </td>
         </tr>
         <tr>
    <label>&nbsp;</label>
           <td>
    <label>State</label>

             <td>
    <input type="text" name="state" required ><br>  
             </td>
         </tr>
    <label>&nbsp;</label>
      <tr>
    <label>&nbsp;</label>
           <td>
    <label>Pin</label>

             <td>
    <input type="text" name="pin" required ><br>  
             </td>
         </tr>
    <label>&nbsp;</label>
       <tr>
    <label>&nbsp;</label>
           <td>
    <label>Country</label>

             <td>
    <input type="text" name="country" required ><br>  
             </td>
         </tr>
    <label>&nbsp;</label>
     <tr>
    <label>&nbsp;</label>
           <td>
    <label>Password</label>

             <td>
    <input type="password" name="pwd" required ><br>  
             </td>
         </tr>
    <label>&nbsp;</label>
    <tr>
    <label>&nbsp;</label>
           <td>
    <label>Confirm Password</label>

             <td>
    <input type="password" name="cpwd" required ><br>  
             </td>
         </tr>
         
    <label>&nbsp;</label>
    <tr>
    <label>&nbsp;</label>
           <td>
    <label>User Type</label>

             <td>
            <select name="userType" size="1">
            <option value="Courier">Courier</option>
            <option value="Customer">Customer</option>
         
          </select>
             </td>
         </tr>
    </table>
    <button class="button" type="submit" name="action" value="Register">Register</button>
    <%@ include file="footer.jsp" %>
    </form>
     </body>
   
     <%@ include file="/footer.jsp" %> 
</html>
