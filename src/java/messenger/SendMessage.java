/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;
import common.user.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author sedog
 */
public class SendMessage extends HttpServlet {
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userID = Integer.valueOf(request.getParameter("userid"));
        User user = (User) request.getSession().getAttribute("user");
        Integer selfID = user.getUserID();
        String msg = request.getParameter("message");
        Hub hub = (Hub) request.getSession().getAttribute("hub");
        Hub other = Directory.get(userID);
        
        if (null == hub.get(userID)) {
            Messenger mine = new Messenger();
            Messenger his = new Messenger();
            mine.registerReceiver(his);
            his.registerReceiver(mine);
            
            
            hub.add(userID,mine);
            
            other.add(selfID, his);
        }
        
        hub.sendMessage(userID,msg);
        List<String> messages = hub.receiveMessages(userID);
        
        request.getSession().setAttribute("messages", messages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("out.jsp");
        dispatcher.forward(request,response);
    }
}