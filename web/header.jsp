<%-- 
    Document   : header
    Created on : Mar 6, 2015, 5:20:14 PM
    Author     : pooja
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        <header>
             <img src="./IMG/back.jpg" alt="Welcome">
             <%    HttpSession name=request.getSession();
                   String loginName=(String) name.getAttribute("Email");
                 //  User user = (User) session.getAttribute("TheUser"); 
                    if(loginName == null){ 
                        loginName ="Guest";
                %>
                
                Hi Guest
                  <a href="login.jsp">Sign In | </a>
                   <a href="register.jsp">Register</a>   
                <% } else {%>
                
               You are logged in as  <%=loginName%>
                 <a href="logout.jsp">Logout </a>
                 <% }%>
             
       
       
    </header>
    </body>
</html>
