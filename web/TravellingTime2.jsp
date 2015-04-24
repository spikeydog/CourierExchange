<%-- 
    Document   : TravellingTime2.jsp
    Created on : 23 Apr, 2015, 11:26:53 PM
    Author     : Chiru
--%>

<%@page import="common.rating.travellingTime.TravellingTime"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Travelling Times</title>
    </head>
    <body>
        
        <%
         TravellingTime travellingTime =  (TravellingTime)session.getAttribute("travellingTime");         
        %>
        <br>
        Average Over All Travelling Time is
        <b><%= travellingTime.getOverallTravellingTime() %> </b>    
        <br>
        Average Personal All Travelling Time is
        <b><%= travellingTime.getPersonalTravellingTime() %></b>
    </body>
</html>
