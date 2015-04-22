/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.servlet;

import bidding.jsp.ListBidsIO;
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
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author sedog
 */
public class BidLister extends HttpServlet {
    // The exit code used by this servlet to handle output messages
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
        System.out.println("bid lister called");
        String binding = "rmi://localhost:2222/" + Server.RMI_BINDING.name;
        List<Bid> bids = new LinkedList<Bid>();
        RequestDispatcher dispatcher = request.getRequestDispatcher("BidsListPage.jsp");
        DeliveryRequest delivery = (DeliveryRequest) request.getSession()
                .getAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
        
        try {
            BiddingServer server = (BiddingServer) Naming.lookup(binding);
            bids = server.listBids(delivery, null, null);
            
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
        if (null == bids) {
            System.out.println("No bids have been placed on this Delivery Request");
        } else {
            request.getSession().setAttribute(ListBidsIO.SESSION_BID_LIST.name, bids);
            dispatcher.forward(request, response);
        }
    }
}
