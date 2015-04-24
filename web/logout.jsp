

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CourierExchange</title>
         <%@ include file="header.jsp" %>
       
    </head>
    <body>
   
    <%@ page session="true"%>
    <%

    session.removeAttribute("email");
    session.invalidate();
    response.sendRedirect("index.jsp");


    %> 
    <%@ include file="footer.jsp" %>
    </body>
</html>
