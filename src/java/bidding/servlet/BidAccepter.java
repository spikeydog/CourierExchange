/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.servlet;

import bidding.jsp.AcceptBidIO;
import bidding.jsp.ListBidsIO;
import common.bidding.Bid;
import common.bidding.BidCE;
import common.bidding.BiddingServer;
import common.delivery.DeliveryRequest;
import common.delivery.DeliveryRequestCE;
import common.util.code.bidding.ExitCode;
import delivery.jsp.ViewDeliveryRequestDetailsIO;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import messenger.Directory;
import messenger.Hub;
import messenger.Messenger;

/**
 *
 * @author sedog
 */
public class BidAccepter extends HttpServlet {

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
        System.out.println("bid accepter called");
        String bidBinding = common.util.RMI.URL.path + common.bidding.Server.RMI_BINDING.name;
        BiddingServer bidServer = null;
        //UserServer userServer = null;
        List<Bid> bids = null;
        Bid bid = null;
        DeliveryRequest delivery = null;
        HttpSession session = request.getSession();
        int index;
        ExitCode code = null;
        RequestDispatcher dispatcher = request.getRequestDispatcher("inProgressDeliveryRequestDetailsPage.jsp");
        
        try {
            bidServer = (BiddingServer) Naming.lookup(bidBinding);
        } catch (NotBoundException ex) {
            System.out.println("A server is not bound");
        }
        
        //createFakeData(session); // DEBUG
        index = Integer.valueOf(request.getParameter(AcceptBidIO.PARA_BID_LIST_INDEX.name));
        bids = (List<Bid>) session.getAttribute(ListBidsIO.SESSION_BID_LIST.name);
        bid = bids.get(index);
        delivery = (DeliveryRequest) session.getAttribute(
                ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name);
        
        System.out.println(null == bid? "DEBUG: null session bid" : bid);
        System.out.println(null == delivery? "DEBUG: null session delivery" : delivery);
        
        try {
            code = bidServer.acceptBid(delivery, bid);
        } catch (RemoteException ex) {
            System.out.println("RMI call failed");
        }
        
        // If successful, create and bind messenger objects to the users
        if (ExitCode.SUCCESS == code) {
            ServletContext context = request.getSession().getServletContext();
            bindMessengers(bid, delivery, request.getSession());
            response.getWriter().write(code.toString()); // DEBUG
        } else {
            response.getWriter().write(code.toString()); // DEBUG
        }
    }
    
    private void bindMessengers(Bid bid, DeliveryRequest delivery, 
            HttpSession session) {
        ServletContext context = session.getServletContext();
        Directory directory = (Directory) context.getAttribute("directory");
        Integer courierID = bid.getCourierID();
        Integer customerID = delivery.getCustomerID();
        Hub courierHub = directory.get(courierID);
        Hub customerHub = directory.get(customerID);
        Messenger courierMsg = new Messenger();
        Messenger customerMsg = new Messenger();
        
        courierMsg.registerReceiver(customerMsg);
        customerMsg.registerReceiver(courierMsg);
        courierHub.add(courierID, courierMsg);
        customerHub.add(customerID, customerMsg);
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
    
    private void createFakeData(HttpSession session) {
        DeliveryRequest dr = new DeliveryRequestCE();
        dr.setDeliveryRequestID(22);
        Bid bid = new BidCE();
        bid.setBidID(3);
        List<Bid> list = new LinkedList<Bid>();
        list.add(bid);
        session.setAttribute(ListBidsIO.SESSION_BID_LIST.name, list);
        session.setAttribute(ViewDeliveryRequestDetailsIO.SESSION_DELIVERY.name, dr);
    }
}
