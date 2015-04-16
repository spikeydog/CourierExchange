/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.servlet;

import bidding.jsp.EditBidIO;
import common.bidding.Bid;
import common.bidding.BidCE;
import common.bidding.BiddingServer;
import common.bidding.Server;
import common.util.code.bidding.ExitCode;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sedog
 */
public class BidEditor extends HttpServlet {
    private ExitCode code = ExitCode.FAILURE;
    
    
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
        System.out.println("bid editor called");
        String binding = "rmi://localhost:2222/" + Server.RMI_BINDING.name;
        try {
            Parameters params = new Parameters(request);
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            Bid bid = new BidCE();
            bid.setDropOffTime(new Timestamp(System.currentTimeMillis()));
            bid.setPickUpTime(params.pickUpTime);
            bid.setFee(params.fee);
            bid.setCourierID(3);
            bid.setDeliveryRequestID(22);
            BiddingServer server = (BiddingServer) Naming.lookup(binding);
            code = server.placeBid(bid);
            
            response.getWriter().write(code.toString());
        } catch (RuntimeException ex) {
            
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
    
    /**
     * Handles some basic validation of HttpRequest parameters and casts 
     * them into their proper data types.
     */
    private class Parameters {
        private Timestamp dropOffTime;
        private Timestamp pickUpTime;
        private float fee;
        
        private Parameters(final HttpServletRequest request) {
            String dropOffTime = (String) request.getAttribute(EditBidIO
                    .PARA_DROP_OFF_TIME.name);
            String pickUpTime = (String) request.getAttribute(EditBidIO
                    .PARA_PICKUP_TIME.name);
            String fee = (String) request.getAttribute(EditBidIO
                    .PARA_FEE.name);
            if (null != dropOffTime) {
                try {
                    this.dropOffTime = Timestamp.valueOf(dropOffTime);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.DROP_OFF_TIME_CAST_ERR;
                    throw new RuntimeException("Invalid");
                }
            }
            if (null != pickUpTime) {
                try {
                    this.pickUpTime = Timestamp.valueOf(pickUpTime);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.PICKUP_TIME_CAST_ERR;
                    throw new RuntimeException("Invalid");
                }
            }
            if (null != fee) {
                try {
                    this.fee = Float.valueOf(dropOffTime);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.FEE_CAST_ERR;
                    throw new RuntimeException("Invalid");
                }
            }
        }
    }
}
