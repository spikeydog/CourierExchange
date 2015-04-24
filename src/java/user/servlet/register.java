/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;

import common.user.Server;
import common.user.User;
import common.user.UserCE;
import common.user.UserCommonServer;
import common.user.UserType;
import javax.servlet.http.HttpServlet;
import common.util.code.user.ExitCode;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pooja
 */
public class register  extends HttpServlet{
    
    
      private ExitCode code = ExitCode.FAILURE;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Hello---====>>>");
      
          String binding = "rmi://localhost:2222/" + Server.RMI_BINDING.name;
//    String binding=  "rmi://localhost:2222/UserCommonServer";
//   // String binding = common.util.RMI.URL.path + common.user.Server.RMI_BINDING.name;
     RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
    System.out.println("USERNAME IS---"+request.getParameter("username"));
    String UserName=request.getParameter("username");
    String Email=request.getParameter("email");
    String PhoneNo=request.getParameter("phoneNo");
    String streetAddress1=request.getParameter("streetAddress1");
    String streetAddress2=request.getParameter("streetAddress2");
    String city=request.getParameter("city");
    String state=request.getParameter("state");;
    String zip=request.getParameter("zipCode");
    String country=request.getParameter("country");
    String password=request.getParameter("password");
    String userType=request.getParameter("userType");
    User usr1 = new UserCE();
    usr1.setUsername(UserName);
    usr1.setEmail(Email);
    usr1.setPhoneNo(PhoneNo);
    usr1.setStreetAddress1(streetAddress1);
    usr1.setStreetAddress2(streetAddress2);
    usr1.setCity(city);
    usr1.setState(state);
    usr1.setZipCode(zip);
    usr1.setPassword(password);
    if(userType.equalsIgnoreCase("COURIER"))
    {
    usr1.setUserType(UserType.COURIER);
    }
    else
    {
        usr1.setUserType(UserType.CUSTOMER);
    }
       try {
            UserCommonServer server = (UserCommonServer) Naming.lookup(binding);
           server.registerUser(usr1);
            
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
       
         dispatcher.forward(request, response);
}
    private User createRegisterRequest(Parameters params)
    {
     

        User usr = new UserCE();
        System.out.println("--->params.UserName"+params.UserName);
        usr.setUsername(params.UserName);
        usr.setPassword(params.password);
        
        usr.setUserType(params.userType);
        usr.setStreetAddress1(params.streetAddress1);
        usr.setStreetAddress2(params.streetAddress2);
        usr.setCity(params.city);
        usr.setState(params.state);
        usr.setEmail(params.Email);
        usr.setZipCode(params.zip);
        System.out.println("--"+usr.getUsername());
        return usr;
 
    }
      private class Parameters {
           
          private String UserName;
          private String Email;
          private String PhoneNo;
          private String streetAddress1;
          private String streetAddress2;
          private String city;
          private String state;
          private String zip;
          private String country;
          private String password;
          private UserType userType;
          private Parameters(final HttpServletRequest request)
           {
               String UserName = (String)request.getParameter("username");
               String Password=(String)request.getAttribute("password");
               String userType=(String)request.getAttribute("userType");
               String Email =(String)request.getAttribute("email");
               String PhoneNo =(String)request.getAttribute("phoneNo");
               String streetAddress1 = (String)request.getAttribute("streetAddress1");
               String streetAddress2 = (String)request.getAttribute("streetAddress2");
               String city =(String)request.getAttribute("city");
               String state=(String)request.getAttribute("state");
               Integer zip =(Integer)request.getAttribute("zipCode");
               String country=(String)request.getAttribute("country");
               System.out.println("userName is--"+UserName); 
           }
         
    }
      private class Attributes
      {
          private User user;
          private Attributes(HttpSession session)
          {
              this.user=(User)session.getAttribute("user");
               if (null == user) {
                code = ExitCode.FAILURE;
                throw new RuntimeException("null courier");
            } 
          }
          
      }
}
