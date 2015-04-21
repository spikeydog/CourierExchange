/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.util.List;

/**
 * Interface that allows a <code>Messenger</code> to update its paired instance
 * of <code>Messenger</code>.
 * 
 * @author sedog
 */
public interface Sender {
    /**
     * Attaches the given <code>Receiver</code> to this <code>Sender</code>.
     * @param r <code>Receiver</code> to attach
     */
    public void registerReceiver(Receiver r);
    /**
     * Removes the given <code>Receiver</code> from this <code>Sender</code>.
     * @param r <code>Receiver</code> to remove
     */
    public void unregisterReceiver(Receiver r);
    /**
     * Obtains a <code>List</code> of all new messages.
     * @return <code>List&#60String&#62</code>
     */
    public List<String> sendMessages();
    /**
     * Sends a message from this <code>Sender</code> to the attached <code>
     * Receiver</code>.
     * @param message <String> to send as a message
     * @return
     */
    public void send(String message);
}
