/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bidding.jsp;

/**
 * Contains the names for any request input parameters or session attributes for
 * the PlaceBid and UpdateBid use cases, as the same information is used.
 * 
 * @author sedog
 */
public enum EditBidIO {
    // Request input parameter for the bid drop off time
    PARA_DROP_OFF_TIME("drop_off_time"),
    // Request input parameter for the bid pick up time
    PARA_PICKUP_TIME("pickup_time"),
    // Request input parameter for the bid fee
    PARA_FEE("fee");
    
    public final String name;
    
    private EditBidIO (String name) {
        this.name = name;
    }
}
