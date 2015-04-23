/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.util.List;

/**
 * Interface that allows a <code>Messenger</code> to receive messages from its
 * paired <code>Messenger</code>.
 *
 * @author sedog
 */
public interface Receiver {
    /**
     * Allows a <code>Talker</code> to update this <code>Receiver</code>.
     */
    public void update(Sender s);
    
    /**
     * Gets all received messages
     */
    public List<String> receiveMessages();
}
