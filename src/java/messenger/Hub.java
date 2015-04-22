/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is to be instantiated and placed into the session of each user
 * when logging into the system. A reference to each instance should be mapped
 * in the application context directory.
 * 
 * @author sedog
 */
public class Hub {
    Map<Integer,Messenger> messengers = new HashMap<Integer,Messenger>();
    
    /**
     * This method maps a new messenger into this <code>Hub</code> using the
     * unique username of the user with whom messages will be sent/received.
     * 
     * @param username      the username to act as the map key
     * @param messenger     <code>Messenger</code> to map to <code>username</code>
     */
    public void add(final int userID, final Messenger messenger) {
        this.messengers.put(userID, messenger);
    }
    
    public Messenger get(final Integer userID) {
        return this.messengers.get(userID);
    }
    
    /**
     * This method unmaps an existing messenger from this <code>Hub</code>
     * @param username  the key to unmap 
     */
    public void remove(final Integer userID) {
        this.messengers.remove(userID);
    }
    
    /**
     * This method breaks all connections between the <code>Messengers</code> in
     * this <code>Hub</code>. This should be called whenever a user logs out of
     * the system.
     */
    public void destroy() {
        Set<Integer> keys = messengers.keySet();
        for (Integer key : keys) {
            messengers.get(key).selfDestruct();
        }
    }
    
    /**
     * This method allows a user to send a message to another user by the unique
     * username of that user. This should be available in the session, and can
     * be set as a result of calling ViewDeliveryRequestDetails.
     * 
     * @param username  the unique username of the recipient
     * @param message   the message to send
     */
    public void sendMessage(final Integer userID, final String message) {
        this.messengers.get(userID).send(message);
    }
    
    /**
     * This method allows a user to obtain any unreceived messages sent to him
     * by the user with the given username.
     * @param username the unique username of the sender
     * @return <code>List&#60String&#62</code>
     */
    public List<String> receiveMessages(final Integer userID) {
        Messenger messenger = this.messengers.get(userID);
        return messenger.receiveMessages();
    }
}
