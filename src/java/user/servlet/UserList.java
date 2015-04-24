/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;
import java.io.Serializable;

import java.util.ArrayList;

/**
 *
 * @author pooja
 */
public class UserList implements Serializable {
     private ArrayList<User> users;
  
    public UserList()
            
    {
        users = new ArrayList<User>();
    }
     public ArrayList<User> getUsers()
    {
        return users;
    }
    
}
