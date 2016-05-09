/*
 * Servlet that checks the database to verify login info and passes the user to the dashboard if info is valid.
 * Also passes the username as an attribute of the HttpSession.
 */
package org.mypackage.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;
import org.mypackage.models.panelCreator;

/**
 *
 * @author Sam Abdallah
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = "";
        String password = "";
        HttpSession ses = request.getSession();
        response.setContentType("text/html");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failLogin.jsp");
        InternetAddress i = null;
     
        
        if (request.getParameter("username") != null) {
            username = ((String) request.getParameter("username")).toLowerCase();
        }
        else {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        try {
                i = new InternetAddress(username);
            } catch (AddressException ex) {
                //This will never happen
                System.out.println("An error has occured");
        }
        if (request.getParameter("password") != null) {
            password = (String) request.getParameter("password");
        }
        else {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        try {
            i.validate();
        } catch (AddressException e) {
            request.setAttribute("error", new StringHolder("Email must be valid"));
            dispatcher.forward(request, response);
            return;
        }
        ses.setAttribute("username", username);
        if (!UserDatabaseHandler.isUser(username)) {
            request.setAttribute("error", new StringHolder("There is no user with that email in our database."));
            dispatcher.forward(request, response);
            return;
        } else if (!UserDatabaseHandler.checkPassword(username, password)) {
            request.setAttribute("error", new StringHolder("Password incorrect."));
            dispatcher.forward(request, response);
            return;
        } //else if(!UserDatabaseHandler.isVerified(username)){
            //request.setAttribute("error", new StringHolder("User not verified, check your email."));
            //dispatcher.forward(request, response);
        //}
        PrintWriter out = response.getWriter();
        new panelCreator(out, username, UserDatabaseHandler.isTeacher(username));
    }
    

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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
