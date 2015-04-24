/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metrics.servlet;


import common.rating.metrics.Metrics;
import common.rating.metrics.MetricsServer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.rating.metrics.Server;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.List;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author Chiru
 */
@WebServlet(name = "ViewMetrics", urlPatterns = {"/ViewMetrics"})
public class ViewMetrics extends HttpServlet {

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
         System.out.println("In Metrics Servlet");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Metrics.jsp");
        String binding = "rmi://127.0.0.1:2222/" + Server.RMI_BINDING.name;
        System.out.println("Printing the binding path"+binding);
         List<Metrics> metricsList = null;
          try {
           // InsertRating.Parameters params = new InsertRating.Parameters(request);
           // System.out.println("From Parameters,Rating ID is"+params.rating.getRatingID());
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            MetricsServer server = (MetricsServer) Naming.lookup(binding);            
            metricsList = server.getMetricsForAllCouriers();            
            for(Metrics metrics : metricsList)            {
                System.out.println(metrics);
            }            
        } catch (RuntimeException ex) {
            System.out.println("Runtime Exceptoin occured "+ex);
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
          
          if (null == metricsList) {
            System.out.println("No bids have been placed on this Delivery Request");
        } else {
            request.getSession().setAttribute("metricsList",metricsList);
            dispatcher.forward(request, response);
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
