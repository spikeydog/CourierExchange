<%-- 
    Document   : TravellingTime.jsp
    Created on : 23 Apr, 2015, 11:11:02 PM
    Author     : Chiru
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       
    </head>
    <body>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travelling Time</title>
        <form action="TravellingTime" method="GET">
            Enter the courier ID: <input type="text" name="courierID"><br>
            Enter the from Address: <input type="text" name="fromAddress"><br>            
            Enter the to Address: <input type="text" name="toAddress"><br>
        <input type="submit" value="View Travelling Time" />
    </body>
</html>
