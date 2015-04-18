/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.servlet;

import common.bidding.Bid;
import common.bidding.BiddingServer;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.bidding.Server;
import common.delivery.DeliveryRequest;
import common.delivery.DeliveryRequestCE;
import common.util.code.bidding.ExitCode;
import delivery.jsp.ViewDeliveryRequestDetailsIO;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.List;

/**
 *
 * @author sedog
 */
public class BidLister extends HttpServlet {
    // The exit code used by this servlet to handle output messages
    private ExitCode code = ExitCode.FAILURE;

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
        // Do nothing
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
        System.out.println("bid editor called");
        String binding = "rmi://localhost:2222/" + Server.RMI_BINDING.name;
        List<Bid> bids = null;
        
        try {
            Parameters params = new Parameters(request);
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            BiddingServer server = (BiddingServer) Naming.lookup(binding);
            bids = server.listBids(params.deliveryRequest, null, null);
            
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
        if (null != bids) {
            for (Bid bid : bids) {
                System.out.println(bid.toString());
            }
        } else {
            System.out.println("bids null");
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

    private class Parameters {
        private DeliveryRequest deliveryRequest;
        
        public Parameters(HttpServletRequest request) {
            DeliveryRequest delivReq = (DeliveryRequest) request.getSession()
                    .getAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
            if (null != delivReq) {
                // Nothing to do here
            } else {   
                delivReq = new DeliveryRequestCE();
                delivReq.setDeliveryRequestID(23);
                this.deliveryRequest = delivReq;
                //code = ExitCode.REQ_NULL;
                //throw new RuntimeException("Delivery null");
            }
            
        }
    }
}
