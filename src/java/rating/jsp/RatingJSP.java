/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rating.jsp;

/**
 *
 * @author Chiru
 */
public enum RatingJSP {
    
    SESSION_RATING("rating");
    
     public final String name;
    
    private RatingJSP (String name) {
        this.name = name;
    }
    
}
