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
import common.delivery.DeliveryRequestCE;
import common.user.User;
import common.user.UserCE;
import common.util.code.bidding.ExitCode;
import static delivery.jsp.ViewDeliveryRequestDetailsIO.SESSION_BID;
import static delivery.jsp.ViewDeliveryRequestDetailsIO.SESSION_DELIVERY;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.sql.Timestamp;
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
        System.out.println("bid editor called");
        String binding = common.util.RMI.URL.path + common.bidding.Server.RMI_BINDING.name;
        System.out.println("DEBUG:binding: " + binding);
        try {
            //createTestData(request.getSession());
            
            Parameters params = new Parameters(request);
            DeliveryRequest delivery = (DeliveryRequest) request.getSession()
                    .getAttribute(SESSION_DELIVERY.name);
            User user = (User) request.getSession().getAttribute("user");
            Bid oldBid = (Bid) request.getSession().getAttribute(SESSION_BID.name);
            
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            Bid bid = new BidCE();
            bid.setDropOffTime(params.dropOffTime);
            bid.setPickUpTime(params.pickUpTime);
            bid.setFee(params.fee);
            bid.setBidID(oldBid.getBidID());
        
            BiddingServer server = (BiddingServer) Naming.lookup("rmi://localhost:2222/biddingServer");
            
            if (null == request.getSession()
                    .getAttribute(SESSION_BID.name)) {
                code = server.placeBid(bid);
            } else {
                code = server.updateBid(bid);
            }

            response.getWriter().write(code.toString());
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
            ex.printStackTrace();
        }
        
        
    }
    
    private void createTestData(HttpSession session) {
        User user = new UserCE();
        user.setUserID(23);
        user.setUserType(common.user.UserType.COURIER);
        session.setAttribute("user", user);
        DeliveryRequest delivery = new DeliveryRequestCE();
        delivery.setDeliveryRequestID(23);
        session.setAttribute(SESSION_DELIVERY.name, delivery);
        Bid bid = new BidCE();
        bid.setBidID(13);
        session.setAttribute(SESSION_BID.name, bid);
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
