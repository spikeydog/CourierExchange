<%-- 
    Document   : EditDeliveryRequest
    Created on : Apr 23, 2015, 11:35:24 PM
    Author     : Prasun
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
    <table id="ContentTab">    
        <h1><b><u>Edit Delivery Request</u></b></h1><br>
        
        <tr>
            <td>    
    <label>Package Information:</label>
            </td>
            <td>
                <input type="text" name="CourierID"><br><br>
            </td>
    </tr>
    <tr>
        <td>
   <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr>
    
    <tr>
            <td>    
    <label>From Location:</label>
            </td>
            <td>
                <input type="text" name="FromLocation"><br><br>
            </td>
    </tr>
   <tr>
        <td>
   <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr> 
    
    <tr>
            <td>    
    <label>Drop Location:</label>
            </td>
            <td>
                <input type="text" name="DropLocation"><br><br>
            </td>
    </tr>
    <tr>
        <td>
   <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr>
    
    <tr>
            <td>    
    <label>Drop Off Address:</label>
            </td>
            <td>
                <input type="text" name="DropOffAddress"><br><br>
            </td>
    </tr>
   <tr>
        <td>
            <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr>
    
    
    <tr>
            <td>    
    <label>Drop Off Date:</label>
            </td>
            <td>
                <input type="text" name="DropOffDate"><br><br>
            </td>
    </tr>
   <tr>
        <td>
            <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr>
    
    
    <tr>
            <td>    
    <label>Drop Off Time:</label>
            </td>
            <td>
                <input type="text" name="DropOffTime"><br><br>
            </td>
    </tr>
   <tr>
        <td>
            <button class="button" type="submit" name="action" value="Edit">Edit</button>
        </td>
    </tr>
    
    
        <tr>
        <td>
   <button class="button" type="submit" name="action" value="Save">Save</button>
        </td>
        <td>
   <button class="button" type="submit" name="action" value="Cancel">Cancel</button>
        </td>
    </tr>
       
   </table>     
    </body>
    <%@ include file="/footer.jsp" %>
</html>