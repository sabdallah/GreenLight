/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.Encryption;
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;

/**
 *
 * @author Sam Abdallah
 */
public class CreateClassServlet extends HttpServlet {

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
            throws ServletException, IOException, Exception {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failRegister.jsp");
        String pass1, pass2, name;
        InternetAddress i = null;

        if (request.getParameter("password") != null && request.getParameter("password2") != null && request.getParameter("classname") != null) {
            pass1 = (String) request.getParameter("password");
            pass2 = (String) request.getParameter("password2");
            name = (String) request.getParameter("name");
         
        } else {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        if (name.equals("")) {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }

        if (pass1.length() <= 6) {
            request.setAttribute("error", new StringHolder("Password must be at least 6 characters long"));
            dispatcher.forward(request, response);
            return;
        }
        if (!pass1.equals(pass2)) {
            request.setAttribute("error", new StringHolder("Passwords must match"));
            dispatcher.forward(request, response);
            return;
        } 
        addClass(name, pass1);
        dispatcher = getServletContext().getRequestDispatcher("/sendConfirmation.jsp");
        dispatcher.forward(request, response);

    }
     
     public void addClass(String name, String password){
        Random rand = new Random();
        int num = rand.nextInt(100000) + 1;
        while (ClassDatabaseHandler.checkRoom(num)) {
            num = rand.nextInt(100000) + 1;
        }
         
         ClassDatabaseHandler.createRoom(num, name, password);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
