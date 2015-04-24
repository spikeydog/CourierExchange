/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pooja
 */
public class DbConnection {
    
    private static final String dbURL = "jdbc:mysql://192.168.56.10:3306/";
    private static final String schemaName = "courier";
    private static final String dbUsername = "team4";
    private static final String dbPassword = "qwe123";
    private static Connection conn;
 
    public DbConnection()
    {
        try
        {
              Class.forName("com.mysql.jdbc.Driver");
              //conn= DriverManager.getConnection(dbURL + schemaName, dbUsername, dbPassword);
              conn= DriverManager.getConnection("jdbc:mysql://192.168.56.10:3306/courier", 
                    "team4","qwe123");
        }
        catch(Exception e)
        {
             System.out.println("ERROR: Could not create DB connection");
        }
    }
      public static Statement getNewStatement() {
        Statement statement;
        try {
            if (conn == null) {
                new DbConnection();
            }
            /* Creating a statement object that we can use for running
             * SQL statements commands against the database.*/
            statement = conn.createStatement();
        } catch (Exception e) {
            System.out.println("ERROR: Could not create SQL statement object: " + e);
            statement = null;
        }
        return statement;
    }
        public static Connection getConnection() {
        if (conn == null) {
            new DbConnection();
        }
        return conn;
    }
          public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
}
