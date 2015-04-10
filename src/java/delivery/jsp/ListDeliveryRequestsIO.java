/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package delivery.jsp;

/**
 * Contains the names for any request input parameters or session attributes for
 the ListDeliveryRequestsIO use case.
 * 
 * @author sedog
 */
public enum ListDeliveryRequestsIO {
    // Request input parameter for the delivery request status to list
    PARA_STATUS("status"),
    // Session attribute populated with the returned list
    SESSION_DELIVERY_REQUEST_LIST("deliveries");
    
    public final String name;
    
    private ListDeliveryRequestsIO (String name) {
        this.name = name;
    }
}
