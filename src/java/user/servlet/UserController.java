/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pooja
 */
@WebServlet(name = "userController", urlPatterns = {"/userController"})
public class UserController  extends HttpServlet{
         @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)throws ServletException, IOException
    {
            ServletContext sc = getServletContext();
           String url = "/index.jsp";
         String action = request.getParameter("action");
             
        String email;
        email = request.getParameter("email");
           HttpSession session = request.getSession();   
            if(action.equals("Login"))
            {
                  UserIO ui=new UserIO();
                 String Email=request.getParameter("email");
                 String pass=request.getParameter("j_password");
                 String compEmail=ui.selectUser(email).getEmail();
                 String compPass=ui.selectUser(email).getPwd();
                 Integer userId=ui.selectUser(email).getUserId();
                 String curier="1";
                 String cust="2";
                  if(Email!=null)
                 {
                 if((Email.equalsIgnoreCase((ui.selectUser(email).getEmail()))) && 
                         (pass.equalsIgnoreCase(ui.selectUser(email).getPwd())))
                 {
                     
                       session.setAttribute("Email", Email); 
                       session.setAttribute("userId", userId);
                       
                       //when logged in as Courier company
                       if(curier.equalsIgnoreCase(ui.selectUser(email).getUserType()))
                       {
                       request.getRequestDispatcher("courier.jsp").forward(request, response);
                       }
                       if(cust.equalsIgnoreCase(ui.selectUser(email).getUserType()))
                       {
                            request.getRequestDispatcher("customer.jsp").forward(request, response);
                       }
                           
                       
                 }
                  else {
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }
    }
             else if(action.equals("Register"))
              {
                   String message;
                  String Email=request.getParameter("email"); 
               
                  if (UserIO.emailExists(email)) {
                message = "This email address already exists. <br>"
                    + "Please enter another email address.";
                request.setAttribute("message", message);
                 url = "/register.jsp";
                 }
                  String fName=request.getParameter("firstName"); 
                 String lName=request.getParameter("lastName"); 
                 String uName=fName+lName;
                 String addOne= request.getParameter("addOne");
                 String addTwo= request.getParameter("addTwo");
                 String city=  request.getParameter("city");
                 String state=  request.getParameter("state");
                 String pin = request.getParameter("pin");
                 String country = request.getParameter("country");
                 String pwd = request.getParameter("pwd");
                 String userType=request.getParameter("userType");
                 UserIO.register(Email,uName,addOne,addTwo,city,state,pin,country,pwd,userType);
                 getServletContext().getRequestDispatcher("/thanks.jsp").forward(request,response);
            } 
}
}

