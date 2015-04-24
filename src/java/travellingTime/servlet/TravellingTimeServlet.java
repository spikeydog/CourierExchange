/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package travellingTime.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.rating.travellingTime.Server;
import common.rating.travellingTime.TravellingTimeServer;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import javax.servlet.RequestDispatcher;
import common.rating.travellingTime.TravellingTime;

/**
 *
 * @author Chiru
 */
@WebServlet(name = "TravellingTimeServlet", urlPatterns = {"/TravellingTime"})
public class TravellingTimeServlet extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ViewMetrics</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewMetrics at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
         System.out.println("In Travelling Time Servlet");
        String binding = "rmi://127.0.0.1:2222/" + Server.RMI_BINDING.name;
        System.out.println("Printing the binding path"+binding);
        
          try {
           // InsertRating.Parameters params = new InsertRating.Parameters(request);
           // System.out.println("From Parameters,Rating ID is"+params.rating.getRatingID());
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            RequestDispatcher dispatcher = request.getRequestDispatcher("TravellingTime2.jsp");
            TravellingTimeServer server = (TravellingTimeServer) Naming.lookup(binding);
            System.out.println("Before getting attributes from JSP");
            int courierID = Integer.valueOf((String)request.getParameter("courierID"));  
            System.out.println("The courier ID is "+courierID);
            String fromAdress = (String)request.getParameter("fromAddress");            
            String toAddress = (String)request.getParameter("toAddress");
            
            TravellingTime travellingTime = (TravellingTime) server.getTravellingTimes(courierID,fromAdress,toAddress);
            
           // TravellingTime travellingTime = (TravellingTime) server.getTravellingTimes(1,"111-12345","222-23456");
            System.out.println(travellingTime);
            request.getSession().setAttribute("travellingTime",travellingTime);
            dispatcher.forward(request, response);
            
        } catch (RuntimeException ex) {
            System.out.println("Runtime Exceptoin occured "+ex);
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
