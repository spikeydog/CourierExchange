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
    <%@include file="/user-navigation.jsp" %>
   
      <table id="ContentTab">
           <h4></h4>
           <tr>
               <td>
                   Track Your Shipment Here
               </td>
               <td>
                   Get a Quick Rate
               </td>
              <td>
                  Find Location
               </td>
           </tr>
           <tr>
               <td>
                   <img src="IMG/track.jpg" alt="picture"/>
               </td>
                <td>
                   <img src="IMG/rate.png" alt="picture"/>
               </td>
           
           </tr>
      </table>
    </body>
      <%@ include file="/footer.jsp" %>
</html>
