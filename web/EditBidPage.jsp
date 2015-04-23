<%-- 
    Document   : EditBidPage
    Created on : Apr 22, 2015, 10:49:27 AM
    Author     : sedog
--%>

<%@page import="delivery.jsp.ViewDeliveryRequestDetailsIO"%>
<%@page import="common.bidding.Bid"%>
<%@page import="common.delivery.DeliveryRequest"%>
<%@page import="common.delivery.DeliveryRequestCE"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DeliveryRequest fakeDR = new DeliveryRequestCE();
    fakeDR.setDescription("Presentation DVD");
    fakeDR.setPickUpTime(new java.sql.Timestamp(System.currentTimeMillis()));
    fakeDR.setDropOffTime(new java.sql.Timestamp(System.currentTimeMillis()));
    fakeDR.setPostTime(new java.sql.Timestamp(System.currentTimeMillis()));
    fakeDR.setPickUpAddress("123 Oak Street San Francisco");
    fakeDR.setDropOffAddress("345 Maple Lane San Francisco");
    fakeDR.setWeight((float) .25);
    session.setAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name, fakeDR);
    // Real code afterwards
    Bid bid = (Bid) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_BID.name);
    DeliveryRequest delivery = (DeliveryRequest) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Bid Page</title>
    </head>
    <body>
        <h1><%= (null==bid)? "Place" : "Update" %> Bid</h1>
        <h2>Delivery Request Details</h2>
        <table>
            <tr>
                <td>
                    <label>Delivery Description:</label>
                </td>
                <td>
                    <label><%= delivery.getDescription() %></label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Pick Up Address:</label>
                </td>
                <td>
                    <label>
                        <%= delivery.getPickUpAddress() %>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Drop Off Address:</label>
                </td>
                <td>
                    <label><%= delivery.getDropOffAddress() %></label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Pick Up Time:</label>
                </td>
                <td>
                    <label>
                        <% String pTime = delivery.getPickUpTime().toString();
                            out.write(pTime.substring(0,pTime.lastIndexOf('.',pTime.length() - 1)));
                        %>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Drop Off Time:</label>
                </td>
                <td>
                    <label>
                        <% String dTime = delivery.getDropOffTime().toString();
                            out.write(dTime.substring(0,dTime.lastIndexOf('.',dTime.length() - 1)));
                        %>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Posted Time:</label>
                </td>
                <td>
                    <label>
                        <% String postTime = delivery.getPostTime().toString();
                            out.write(postTime.substring(0,postTime.lastIndexOf('.',postTime.length() - 1)));
                        %>
                    </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label>Package Weight:</label>
                </td>
                <td>
                    <label><%= delivery.getWeight() %></label>
                </td>
            </tr>
        </table>
        <br>
        <h2>Your Bid</h2>
        <table>
        <form action="BidEditor" method="post" onsubmit="setTime()">
            <tr>
                <td>
            <label for="pick_up_date">Pick-Up Date</label>
                </td>
                <td>
            <input id="pick_up_date" 
                   type="date"
                   value="<%= delivery.getPickUpTime().toString().substring(0,10)%>"/>
                </td>
                <td>
            <label for="pick_up_time">Pick-Up Time</label>
                </td>
                <td>
            <input id="pick_up_time" type="time"/>
                </td>
                <td>
            <input id="set_pick_up_time" 
                   name="<%= bidding.jsp.EditBidIO.PARA_PICKUP_TIME.name %>"
                   type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>
            <label for="drop_off_date">Drop-Off Date</label>
                </td>
                <td>
            <input id="drop_off_date" 
                   type="date"
                   value="<%= delivery.getDropOffTime().toString().substring(0,10)%>"/>
                </td>
                <td>
            <label for="drop_off_time">Drop-Off Time</label>
                </td>
                <td>
            <input id="drop_off_time" type="time"/>
                </td>
                <td>
            <input id="set_drop_off_time" 
                   name="<%= bidding.jsp.EditBidIO.PARA_DROP_OFF_TIME.name %>"
                   type="hidden"/>
                </td>
            </tr>
            <tr>
                <td>
            <label for="fee">Delivery Fee</label>
                </td>
                </td>
                <td>
            <input id="fee"
                   name="<%= bidding.jsp.EditBidIO.PARA_FEE.name %>"
                   type="text"
                   />
                </td>
            </tr>
            <tr>
                <td>
            <input type="submit" value="Submit"/>
                </td>
            </tr>
        </form>
        </table>           
                   <script>
            function setTime() {
                var pickDate = document.getElementById("pick_up_date");
                var pickTime = document.getElementById("pick_up_time");
                var pickSet = document.getElementById("set_pick_up_time");
                pickSet.value = pickDate.value + " " + pickTime.value;
                var dropDate = document.getElementById("drop_off_date");
                var dropTime = document.getElementById("drop_off_time");
                var dropSet = document.getElementById("set_drop_off_time");
                dropSet.value = dropDate.value + " " + dropTime.value;
            }
                   </script>
    </body>
</html>
