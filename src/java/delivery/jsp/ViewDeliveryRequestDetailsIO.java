/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivery.jsp;

/**
 * Contains the names of JSP input parameters and output session attributes for
 * the ViewDeliveryRequestDetails use case.
 * 
 * @author sedog
 */
public enum ViewDeliveryRequestDetailsIO {
    // Request input parameter for the index of the selected delivery request
    PARA_DELIVERY_REQUEST_LIST_INDEX("index"),
    // Session attribute populated with the details of the selected request
    SESSION_DELIVERY("delivery"),
    // Session attribute populated when a courier selects a request with a bid
    // already placed
    SESSION_BID("bid");
    
    public final String name;
    
    private ViewDeliveryRequestDetailsIO (String name) {
        this.name = name;
    }
}
