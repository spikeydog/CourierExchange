<%-- 
    Document   : out
    Created on : Apr 24, 2015, 6:39:58 PM
    Author     : Spikeydog
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Messages!</h1>
        <%
            java.util.List<String> messages = (java.util.List<String>) session.getAttribute("messages");
            for (String m : messages) {
                out.write(m + "<br>");
            }
            common.user.User user = (common.user.User) session.getAttribute("user");
        %>
        <form action="SendMessage" method="post">
            <input type="number" name="userid"/>
            <input type="text" name="message"/>
            <input type="submit" value="Send"/>
        </form>
        
    </body>
</html>
