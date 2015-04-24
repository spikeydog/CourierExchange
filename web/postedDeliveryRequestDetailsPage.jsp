<%-- 
    Document   : postedDeliveryRequestDetailsPage
    Created on : Apr 22, 2015, 10:40:14 AM
    Author     : sedog
--%>
<%@page import="delivery.jsp.ViewDeliveryRequestDetailsIO"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="common.delivery.DeliveryRequest"%>
<%@page import="common.delivery.DeliveryRequestCE"%>
<%@page import="common.user.User"%>
<%@page import="common.user.UserCE"%>
<%@page import="common.bidding.Bid"%>
<%@page import="common.user.UserType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    DeliveryRequest fakeDR = new DeliveryRequestCE();
    fakeDR.setDeliveryRequestID(4);
    fakeDR.setDescription("Presentation DVD");
    fakeDR.setPickUpTime(Timestamp.valueOf("2015-04-24 18:00:00"));
    fakeDR.setDropOffTime(Timestamp.valueOf("2015-04-24 18:30:00"));
    fakeDR.setPostTime(Timestamp.valueOf("2015-04-24 17:00:00"));
    fakeDR.setPickUpAddress("123 Oak Street San Francisco");
    fakeDR.setDropOffAddress("345 Maple Lane San Francisco");
    fakeDR.setWeight((float) .25);
    User fakeUser = new UserCE();
    fakeUser.setUserID(2);
    //fakeUser.setUserType(UserType.COURIER);
    fakeUser.setUserType(UserType.CUSTOMER);
    session.setAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name, fakeDR);
    session.setAttribute("user", fakeUser);
    // Real code afterwards
    DeliveryRequest delivery = (DeliveryRequest) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
    User user = (User) session.getAttribute("user");
    Bid bid = (Bid) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_BID.name);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Posted Delivery Request Details</title>
    </head>
    <body>
        <h1>Posted Delivery Request Details</h1>
        <h2>Delivery Request Details</h2>
        <table>
            <tr>
                <td>
                    <label>Delivery ID:</label>
                </td>
                <td>
                    <label><%= delivery.getDeliveryRequestID() %></label>
                </td>
            </tr>
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
            <tr>
                <td>
                <%= createButton(user, !(null==bid)) %>
                </td>
            </tr>
        </table>
        <br>
        
        <%! 
            private String createButton(User user, boolean bidExists ) {
                String s = null;
                if (UserType.COURIER == user.getUserType()) {
                    s = "<form action='EditBidPage.jsp' method='post'>" +
                    "<input type='submit' value='" 
                    + (bidExists? "Update" : "Place") + " Bid'/>"
                    + "</form>";
                } else {
                    s = "<form action='BidLister' method='post'>" +
                    "<input type='submit' value='List Bids'/>" 
                    + "</form>";
                }
                return s;
            }

            %>
    </body>
</html>
