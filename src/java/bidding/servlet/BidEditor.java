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
import common.delivery.DeliveryRequest;
import common.user.User;
import common.util.code.bidding.ExitCode;
import delivery.jsp.ViewDeliveryRequestDetailsIO;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        // URL to the bound RMI server
        String binding = common.util.RMI.URL.path + common.bidding.Server
                .RMI_BINDING.name;
        // The bidding server to handle our request 
        BiddingServer server = null;
        // Request dispatcher to forward the request after processing
        RequestDispatcher dispatcher = request.getRequestDispatcher(
                "postedDeliveryRequestDetailsPage.jsp");
        // Encapsulated input parameters
        Parameters params = new Parameters(request);
        // Encapsulated session attributes
        Attributes attr = new Attributes(request.getSession());
        // The bid that will be inserted or updated
        Bid bid = createBid(params, attr);
        // The bid after being inserted into the database (fully populated)
        Bid newBid = null;
        
        System.out.println("DEBUG: bid editor called");
        
        try {
            server = (BiddingServer) Naming.lookup(binding);
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
            ex.printStackTrace();
        }
        // Insert if no session Bid; update otherwise.
        if (null == attr.bid) {
            code = server.placeBid(bid);
        } else {
            code = server.updateBid(bid);
        }
        
        System.out.println("DEBUG: BidEditor code: " + code.toString());
        newBid = server.getBidByCourierIDDeliveryID(attr.courier, attr.delivery);
        System.out.println(newBid==null? "newbid is null :( ": "");
        request.getSession().setAttribute(ViewDeliveryRequestDetailsIO
                .SESSION_BID.name, newBid);

        dispatcher.forward(request, response);
    }
    
    private Bid createBid(Parameters params, Attributes attr) {
        Bid bid = new BidCE();
        bid.setDropOffTime(params.dropOffTime);
        bid.setPickUpTime(params.pickUpTime);
        bid.setFee(params.fee);
        bid.setCourierID(attr.courier.getUserID());
        bid.setDeliveryRequestID(attr.delivery.getDeliveryRequestID());
        bid.setBidID((null == attr.bid)? BidCE.DEFAULT_BID_ID : attr.bid.getBidID());
        
        return bid;
    }
    
    private class Attributes {
        private User courier;
        private DeliveryRequest delivery;
        private Bid bid;
        
        private Attributes(HttpSession session) {
            this.courier = (User) session.getAttribute("user");
            this.bid = (Bid) session.getAttribute(ViewDeliveryRequestDetailsIO
                    .SESSION_BID.name);
            this.delivery = (DeliveryRequest) session.getAttribute(
                    ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
            
            if (null == courier) {
                code = ExitCode.FAILURE;
                throw new RuntimeException("null courier");
            } else if (null == delivery) {
                code = ExitCode.REQ_NULL;
            }
        }
    }
    
    /**
     * Handles some basic validation of HttpRequest parameters and casts 
     * them into their proper data types. Sets appropriate error codes if any 
     * of the given parameters are null, cannot be cast to the correct type, or
     * are otherwise logically invalid.
     * 
     * @param request   the <code>HttpRequest</code> containing parameters
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
            System.out.println("DEBUG: " + pickUpTime);
            String fee = (String) request.getAttribute(EditBidIO
                    .PARA_FEE.name);
            if (null != dropOffTime) {
                try {
                    this.dropOffTime = Timestamp.valueOf(dropOffTime);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.DROP_OFF_TIME_ERR;
                    throw new RuntimeException("Invalid");
                }
            } else {
                code = ExitCode.DROP_OFF_TIME_ERR;
            }
            if (null != pickUpTime) {
                try {
                    this.pickUpTime = Timestamp.valueOf(pickUpTime);
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.PICKUP_TIME_ERR;
                    throw new RuntimeException("Invalid");
                }
            } else {
                code = ExitCode.PICKUP_TIME_ERR;
            }
            if (null != fee) {
                try {
                    this.fee = Float.valueOf(dropOffTime);
                    if (0 >= this.fee) {
                        code = ExitCode.FEE_ERR;
                        throw new RuntimeException("Invalid");
                    }
                } catch (IllegalArgumentException ex) {
                    ex.printStackTrace();
                    code = ExitCode.FEE_ERR;
                    throw new RuntimeException("Invalid");
                }
            } else {
                code = ExitCode.FEE_ERR;
            }
        }
    }
}
