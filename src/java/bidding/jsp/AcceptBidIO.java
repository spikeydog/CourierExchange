/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.jsp;

/**
 * Stores the names of the JSP input parameters for the AcceptBidIO use case.
 * @author sedog
 */
public enum AcceptBidIO {
    // Request input parameter as the bids list index for the accepted bid
    PARA_BID_LIST_INDEX("index");
    
    public final String name;
    
    private AcceptBidIO (String name) {
        this.name = name;
    }
}
