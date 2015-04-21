/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author sedog
 */
public class Messenger implements Sender, Receiver {
    private static final String SELF_DESTRUCT = "#!SelfDestruct";
    private Set<Receiver> receivers = new HashSet<Receiver>();
    private List<String> messagesToSend = new LinkedList<String>();
    private List<String> messagesReceived = new LinkedList<String>();
    
    @Override
    public void registerReceiver(Receiver r) {
        this.receivers.add(r);
    }
    @Override
    public void unregisterReceiver(Receiver r) {
        this.receivers.remove(r);
    }
    @Override
    public void update(Sender s) {
        System.out.println("DEBUG: messages from: " + s);
        List<String> messages = s.sendMessages();
        System.out.println("DEBUG: messages" + messages);
        for (String m : messages) {
            if (SELF_DESTRUCT.equals(m)) {
                this.receivers = null;
            }
            this.messagesReceived.add(m);
            System.out.println("received after update:" + messagesReceived);
        }
    }
    @Override
    public List<String> sendMessages() {
        List<String> newMessages = this.messagesToSend;
        
        if (null == this.receivers) {
            newMessages.add(SELF_DESTRUCT);
        }
        
        this.messagesToSend = new LinkedList<String>();
        
        return newMessages;
    }
    @Override
    public List<String> receiveMessages() {
        System.out.println("recevieMessages called");
        System.out.println("current messages received: " + this.messagesReceived);
        List<String> messages = this.messagesReceived;
        this.messagesReceived = new LinkedList<String>();
        
        return messages;
    }
    @Override
    public void send(String message) {
        this.messagesToSend.add(message);
        for (Receiver r : receivers) {
            r.update(this);
        }
    }
    public void selfDestruct() {
        for (Receiver r : this.receivers) {
            r.update(this);
        }
        this.receivers = null;
    }
}
