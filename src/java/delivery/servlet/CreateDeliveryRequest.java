/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivery.servlet;

import common.delivery.DeliveryRequest;
import common.delivery.DeliveryRequestCE;
import common.delivery.DeliveryServer;
import common.util.code.delivery.ExitCode;
import delivery.jsp.ViewDeliveryRequestDetailsIO;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Akshay
 */
public class CreateDeliveryRequest extends HttpServlet {

    
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
        doPost(request, response);
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
            System.out.println("create delivery called");
            String binding = common.util.RMI.URL.path + common.delivery.Server.RMI_BINDING.name;
            DeliveryServer deliveryServer = null;
            HttpSession session = request.getSession();
            ExitCode code = null;
            DeliveryRequest delivery = buildDelivery(request);
            RequestDispatcher dispatcher = request.getRequestDispatcher("createdeliveryhtml.html");
             
            try {
                deliveryServer = (DeliveryServer) Naming.lookup(binding);
            } catch (NotBoundException ex) {
                System.out.println("Delivery Server is not bound to " + binding);
            }
            
             try {
            code = deliveryServer.createDeliveryRequest(delivery);
            }
             catch (RemoteException ex) {
            System.out.println("RMI call failed");
            }

            if (ExitCode.SUCCESS == code) {
                response.getWriter().write("Delivery Request Created"); // DEBUG
            }
            else{
                response.getWriter().write("Delivery Request fail to create"); // DEBUG
            }
    }

    private DeliveryRequest buildDelivery(HttpServletRequest request) {
        String description = (String) request.getParameter("description");
        String pickUpAddress = (String) request.getParameter("pickUpAddress");
        String dropOffAddress = (String)request.getParameter("DropoffAddress");
        Float weight = (float) 0;
        try {
            weight = Float.valueOf(request.getParameter("weight"));
        } catch (NumberFormatException ex) {
            weight = (float) 1.0;
        }
        Long dropOffDateTime = (Long.valueOf(request.getParameter("dropOffDateTime")));
        Long pickUpDateTime = (Long.valueOf(request.getParameter("pickUpDateTime")));
        
     
        DeliveryRequest dr = new DeliveryRequestCE();
        dr.setCustomerID(1); // Hard-coded  :(
        dr.setDescription(description);
        dr.setPickUpAddress(pickUpAddress);
        dr.setDropOffAddress(dropOffAddress);
        dr.setPickUpTime(new Timestamp(pickUpDateTime));
        dr.setDropOffTime(new Timestamp(dropOffDateTime));   
        dr.setWeight(weight);
        return(dr);
    }
    
}