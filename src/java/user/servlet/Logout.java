/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;

import common.user.Server;
import common.user.UserCommonServer;
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
/**
 *
 * @author pooja
 */
public class Logout  extends HttpServlet {
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
    String binding = "rmi://localhost:2222/" + Server.RMI_BINDING.name;
     RequestDispatcher dispatcher = request.getRequestDispatcher(".jsp");
       try {
            UserCommonServer server = (UserCommonServer) Naming.lookup(binding);
           
            
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
       
         dispatcher.forward(request, response);
}
}
