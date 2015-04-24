/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.servlet;

import common.rating.Rating;
import common.rating.RatingCE;
import common.rating.*;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chiru
 */
@WebServlet(name = "InsertRating", urlPatterns = {"/InsertRating"})
public class InsertRating extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Do nothing
        doPost(request,response);
    }
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("In Ratings Servlet");
        String binding = "rmi://127.0.0.1:2222/" + Server.RMI_BINDING.name;
        System.out.println("Printing the binding path"+binding);
        
        //String binding = common.util.RMI.URL.path+Server.RMI_BINDING.name;
        Rating rating2 = null;
        
        try {
            Parameters params = new Parameters(request);
            System.out.println("From Parameters,Rating ID is"+params.rating.getRatingID());
            // Hard-coding some stuff to test; this is really ugly.
            // This also does not work yet. Only produces failure.
            RatingServer server = (RatingServer) Naming.lookup(binding);
           //System.out.println( server.insertRating(params.rating) );
            
        } catch (RuntimeException ex) {
            
        } catch (NotBoundException ex) {
            System.out.println("RMI Server does not appear to be running");
        }
    } 
     

private class Parameters {
        private Rating rating;        
        public Parameters(HttpServletRequest request) {
            this.rating = new RatingCE();
            rating.setRatingID(60);
            rating.setDeliveryRequestID(1000);
            rating.setCustomerDeliveryPersonRating(10);
            rating.setCustomerOverallRating(1);
            rating.setCustomerProfesionalismRating(1);
                    //(Rating) request.getSession()            .getAttribute(insertRating);
            if (null != rating) {
                // Nothing to do here
            } else {   
                Rating rate= new RatingCE();
                rate.setRatingID(20);
                this.rating = rate;
                //code = ExitCode.REQ_NULL;
                //throw new RuntimeException("Delivery null");
            }
            
        }
    }// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">


 
}
