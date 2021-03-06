/*
 * A servlet that validates the user's registration information and sends them the confirmation email.
 */
package org.mypackage.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mypackage.models.Encryption;
import org.mypackage.models.StringHolder;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.naming.InitialContext;
import org.mypackage.models.UserDatabaseHandler;

/**
 *
 * @author Sam Abdallah
 */
public class RegisterServlet extends HttpServlet {

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
        String pass1, pass2, user;
        InternetAddress i = null;

        if (request.getParameter("password") != null && request.getParameter("password2") != null && request.getParameter("username") != null) {
            pass1 = (String) request.getParameter("password");
            pass2 = (String) request.getParameter("password2");
            user = (String) request.getParameter("username");
            try {
                i = new InternetAddress(user);
            } catch (AddressException ex) {
                //This will never happen
                System.out.println("An error has occured");
            }
        } else {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        if (user.equals("")) {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }

        if (pass1.length() < 6) {
            request.setAttribute("error", new StringHolder("Password must be at least 6 characters long"));
            dispatcher.forward(request, response);
            return;
        }
        if (!pass1.equals(pass2)) {
            request.setAttribute("error", new StringHolder("Passwords must match"));
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
        
        addUser(user.toLowerCase(), pass1);
        //sendEmail(user);
        dispatcher = getServletContext().getRequestDispatcher("/index.html");
        dispatcher.forward(request, response);

    }
     public void sendEmail(String user) throws Exception {
        InitialContext ctx = new InitialContext();
        Session session =
            (Session) ctx.lookup("mail/GreenLight");
        Message msg = new MimeMessage(session);
        msg.setSubject("Greenlight Confirmation");
        msg.setRecipient(RecipientType.TO,
                         new InternetAddress(
                         user,
                         user));
        msg.setFrom(new InternetAddress(
                    "lfhsgreenlight@gmail.com",
                    "GreenLight Admin"));

        msg.setText("Here is your confirmation code: http://localhost:8080/WebApplication1/ConfirmServlet?code=" + Encryption.encryptEmail(user));
        Transport.send(msg);
    }
     
     public void addUser(String email, String password){
         UserDatabaseHandler.addUser(email, password);
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
