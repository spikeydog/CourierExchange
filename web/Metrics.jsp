
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="common.delivery.DeliveryRequest"%>
<%@page import="java.util.List"%>
<%@page import="java.lang.StringBuilder"%>
<%@page import="common.rating.metrics.Metrics"%>
<%@page import="common.rating.metrics.Metrics"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Metrics Page</title>
    </head>
    <body>
        <h1>Metrics for all the Couriers</h1>
        <table cellpadding="5px" border="1px">
            <tr>
                <td>
                    Courier Id
                </td>
                <td>
                    Overall Rating
                </td>
                <td>
                    Professionalism Rating
                </td>
                <td>
                    Delivery Person Rating
                </td>
                <td>
                    Number of Bids Placed
                </td>
                <td>
                    No of delivered requests
                </td>
                <td>
                    Delivered on Time
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
                List<Metrics> metricsList = (List<Metrics>) session.getAttribute("metricsList");
                StringBuilder scribe = new StringBuilder();
                for (Metrics metrics : metricsList) {
                    scribe.append(rc).append(metrics.getCourierID())
                            .append(coc).append(metrics.getAverageOverallRating())
                            .append(coc).append(metrics.getAverageProfesionalismRating())
                            .append(coc).append(metrics.getAverageDeliveryPersonRating())
                            .append(coc).append(metrics.getNoOfBidsPlaced())
                            .append(coc).append(metrics.getTotalRequestsDelivered())
                            .append(coc).append(metrics.getPercentageRequestsDeliveredOnTime()).append("%")                     
                            .append(coro);
                    
                }
                return scribe.toString();
            }
            %>
            <%= buildTable(session) %>
        </table>
    </body>
</html>