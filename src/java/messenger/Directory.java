/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import common.user.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sedog
 */
public class Directory {
    private static Map<String,Hub> directory = new HashMap<String, Hub>();
    private static Directory instance = null;
    
    private Directory() {}
    
    /**
     * Returns a reference to the singleton instance of this class
     * @return Directory
     */
    public static Directory getInstance() {
        if (null == instance) {
            return new Directory();
        } else {
            return instance;
        }
    }
    
    /**
     * Returns the <code>Hub</code> mapped to the given username.
     * @param username  the username of the user to get the Hub for
     * @return Hub
     */
    public static Hub get(String username) {
        return directory.get(username);
    }
    
    /**
     * Maps the given username to the given <code>Hub</code>.
     * @param username  the username of the user to map <code>hub</come> to
     * @param hub  the <cod>Hub</code> to be mapped to <code>username</code>
     */
    public static void add(String username, Hub hub) {
        directory.put(username, hub);
    }
    
    /**
     * Removes the directory entry for the given username. As a side-effect, the
     * mapped <code>Hub</code> will destroy all attached <code>Messengers</code>
     * 
     * @param username  the username of the user to unmap a Hub of
     */
    public static void remove(String username) {
        directory.get(username).destroy();
        directory.remove(username);
    }
}
