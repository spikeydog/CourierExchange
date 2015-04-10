/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.jsp;

/**
 * Stores the names of session attributes set for the ListBids use case; includes
 * the SortBids use case.
 * 
 * @author sedog
 */
public enum ListBidsIO {
    // Session attribute populated with the returned list
    SESSION_BID_LIST("bids");
    
    public final String name;
    
    private ListBidsIO (String name) {
        this.name = name;
    }
}
