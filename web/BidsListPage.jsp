<%-- 
    Document   : BidsListPage
    Created on : Apr 22, 2015, 10:49:01 AM
    Author     : sedog
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.delivery.DeliveryRequest"%>
<%@page import="bidding.jsp.AcceptBidIO"%>
<%@page import="delivery.jsp.ViewDeliveryRequestDetailsIO"%>
<%@page import="bidding.jsp.ListBidsIO"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.StringBuilder"%>
<%@page import="common.bidding.Bid"%>
<%
    DeliveryRequest delivery = (DeliveryRequest) session.getAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
    List<Bid> bids = (List<Bid>) session.getAttribute(ListBidsIO.SESSION_BID_LIST.name);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bids List Page</title>
    </head>
    <body>
        <h1>Current Bids</h1>
        <table cellpadding="5px" border="1px">
            <tr>
                <td>
                    Pick-Up Time
                </td>
                <td>
                    Drop-Off Time
                </td>
                <td>
                    Delivery Fee
                </td>
            </tr>
            <%!
            private String formatTime(final java.sql.Timestamp time) {
                String s = time.toString();
                String r = s.substring(0,s.lastIndexOf('.',s.length() - 1));
                return r;
            }
            private String buildTable(HttpSession session) {
                String r = "<tr>";
                String ro = "</tr>";
                String c = "<td>";
                String rc = r + c;
                String co = "</td>";
                String ror = ro + r;
                String coc = co + c;
                String coro = co + ro;
                String rco = ro + co;
                List<Bid> bids = (List<Bid>) session.getAttribute(ListBidsIO.SESSION_BID_LIST.name);
                StringBuilder scribe = new StringBuilder();
                int i = 0;
                for (Bid bid : bids) {
                    scribe.append(rc).append(formatTime(bid.getPickUpTime()))
                            .append(coc).append(formatTime(bid.getDropOffTime()))
                            .append(coc).append(String.valueOf(bid.getFee()))
                            .append(coc).append("<form action='BidAccepter'><input type='submit' value='Accept Bid'/>")
                            .append("<input type='hidden' name='").append(AcceptBidIO.PARA_BID_LIST_INDEX.name).append("' value='")
                            .append(String.valueOf(i)).append("'/></form>").append(coro);
                    i++;
                    
                }
                return scribe.toString();
            }
            %>
            <%= buildTable(session) %>
        </table>
    </body>
</html>
