<%-- 
    Document   : EditBidPage
    Created on : Apr 22, 2015, 10:49:27 AM
    Author     : sedog
--%>

<%@page import="delivery.jsp.ViewDeliveryRequestDetailsIO"%>
<%@page import="common.bidding.Bid"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Bid bid = (Bid) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_BID.name);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Bid Page</title>
    </head>
    <body>
        <h1><%= (null==bid)? "Place" : "Update" %></h1>
        <table>
            <tr>
                <td>
                    <label>Description</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Pick Up Address</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Drop Off Address</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Pick Up Time</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Drop Off Time</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Posted Time</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Package Weight</label>
                </td>
                <td>
                    
                </td>
            </tr>
            <tr>
                <td>
                    <label>Delivery ID</label>
                </td>
                <td>
                    
                </td>
            </tr>
            
        </table>
    </body>
</html>
