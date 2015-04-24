/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


/**
 *
 * @author pooja
 */
public class UserIO {
  
     
     public static User selectUser(String email) {
         
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps=null;
        String emailId ="";
        String password ="";
        String username="";
        
        String addOne="";
        String addTwo="";
        String city="";
        String state="";
        String userType="";
        int pin=0;
        String phone_no="";
        String country="";
        int user_id=0;
        User user = new User();
              String query = "SELECT user_id,username,password,user_type,street_address_1,street_address_2,city,state,country,zip_code,phone_no,email_address from USER where email_address=?";
        try
        {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
             if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + email);
                return null;
            }
             else {
                 user_id=resultSet.getInt("user_id");
                emailId = resultSet.getString("email_address");
                password = resultSet.getString("password");
                username = resultSet.getString("username");
               // phone_no=resultSet.getString("phone_no");
                addOne=resultSet.getString("street_address_1");
                addTwo=resultSet.getString("street_address_2");
                city=resultSet.getString("city");
                state=resultSet.getString("state");
                pin=resultSet.getInt("zip_code");
                country=resultSet.getString("country");
                userType=resultSet.getString("user_type");
                user = new User(user_id,emailId,password,username,  addOne,addTwo,city,state,pin,country,userType);
                System.out.println("Found user in user table: " + emailId);
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Could not execute SQL statement: " + query);
            System.out.println("SQL error: " + e);
            return null;
        }
         return user;
    }
     
     
     public User getLoginDet(String email) 
     {
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps=null;
        String emailId ="";
        String password ="";
        User user = new User();
              String query = "SELECT email_address,password from USER where email_address=?";
        try
        {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
             if (!resultSet.next()) {
                System.out.println("WARNING: Could not find user in USER table: " + email);
                return null;
            }
             else {
                emailId = resultSet.getString("email_address");
                password = resultSet.getString("password");
            //    user = new User(emailId,password);
                System.out.println("Found user in user table: " + emailId);
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR: Could not execute SQL statement: " + query);
            System.out.println("SQL error: " + e);
            return null;
        }
         return user;
     }
     public static boolean emailExists(String email) 
     {
//         
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        Connection conn = DbConnection.getConnection();
        PreparedStatement ps=null; 
        
         String query = "SELECT email_address FROM USER "
                + "WHERE email_address = ?";
          try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            resultSet = ps.executeQuery();
             if(resultSet.next())
             {
                 return true;
             }
             else
             {
                 return false;
             }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
     }
     public static void register(String email,String firstName,String addOne,String addTwo,String city,String state,String pin,String country,String password,String userType) {
            
       Connection conn = DbConnection.getConnection();
         PreparedStatement ps;
          try
         {
         ps = conn.prepareStatement("INSERT INTO USER(email_address,username,street_address_1,street_address_2,city,state,zip_code,country,password,user_type) VALUES (?,?,?,?,?,?,?,?,?,?)");
        // ps.setString(parameterIndex, null);
         ps.setString(1, email);
         ps.setString(2, firstName);
        
         ps.setString(3, addOne);
         ps.setString(4, addTwo);
         ps.setString(5, city);
         ps.setString(6, state);
         ps.setString(7, pin);
         ps.setString(8, country);
         ps.setString(9, password);
         ps.setString(10, userType);
         
         ps.executeUpdate();
         }
          catch(Exception e)
          {
                  System.out.println("ERROR: Could not add row to PRODUCT table: " + email);
         
          }
         
     }
    
     public static String hashPassword(String password) throws NoSuchAlgorithmException 
     {
         MessageDigest md=MessageDigest.getInstance("SHA-256");
         md.update(password.getBytes());
         byte[] mdArray = md.digest();
         StringBuilder sb = new StringBuilder(mdArray.length * 2);
         for(byte b:mdArray){
             int v = b & 0xff;
             if(v<16)
             {
                 sb.append('0');
                 
             }
             sb.append(Integer.toHexString(v));
             
         }
         return sb.toString();
     }
     
   
}
