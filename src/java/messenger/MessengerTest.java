/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;
import common.user.User;
import common.user.UserCE;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author sedog
 */
public class MessengerTest extends HttpServlet {
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
        Integer uname = Integer.valueOf(request.getParameter("userid"));
        ServletContext context = request.getSession().getServletContext();
        User user = new UserCE();
        user.setUserID(uname);
        
        if (null == context.getAttribute("directory")) {
            context.setAttribute("directory", Directory.getInstance());
        }
        Directory.add(uname,new Hub());
        request.getSession().setAttribute("hub",Directory.get(uname));
        request.getSession().setAttribute("user", user);
        RequestDispatcher dispatcher = request.getRequestDispatcher("out.jsp");
        dispatcher.forward(request,response);
    }
}
