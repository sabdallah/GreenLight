/*
 * A servlet that allows teachers to create new classes and writes them into the database.
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
import javax.servlet.http.HttpSession;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.Encryption;
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;
import org.mypackage.models.panelCreator;

/**
 *
 * @author Sam Abdallah
 */
public class CreateClassServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Ensures that all of the information provided is correct, sends errors back tot he JSP if it isn't, and then creates the class.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failNewClassPage.jsp");
        String pass1, pass2, name;
        InternetAddress i = null;
        HttpSession ses = request.getSession();
        String username = (String)ses.getAttribute("username");

        //Set the local variables as long as the fields aren't empty.
        if (request.getParameter("password") != null && request.getParameter("password2") != null && request.getParameter("classname") != null) {
            pass1 = (String) request.getParameter("password");
            pass2 = (String) request.getParameter("password2");
            name = (String) request.getParameter("classname");
         
        }
        //If any of the fields are null, create a new error holder telling the user to fill out the rest of the information and send it back to the JSP page.
        else {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        //Makes sure the name isn't just  a blank string. If it is, indicate to the user that it can't be.
        if (name.equals("")) {
            request.setAttribute("error", new StringHolder("Please fill out all of the required information"));
            dispatcher.forward(request, response);
            return;
        }
        //Makes sure the password is at least 6 characters long. If it isn't, tell the user.
        if (pass1.length() <= 6) {
            request.setAttribute("error", new StringHolder("Password must be at least 6 characters long"));
            dispatcher.forward(request, response);
            return;
        }
        //Makes sure the confirm password is the same as the actual password. If it isn't, tell the use.
        if (!pass1.equals(pass2)) {
            request.setAttribute("error", new StringHolder("Passwords must match"));
            dispatcher.forward(request, response);
            return;
        } 
        //If the user trying to create a class isn't a teacher, and is attempting to use this servlet, deny them access.
        if(!UserDatabaseHandler.isTeacher((String) ses.getAttribute("username")))
        {
            request.setAttribute("error", new StringHolder("You're not a teacher... Nice try bud."));
            dispatcher.forward(request, response);
            return;
        }
        //If all is well, proceed to creating the class.
        addClass(name, pass1, username);
        PrintWriter out = response.getWriter();
        new panelCreator(out, username, UserDatabaseHandler.isTeacher(username));

    }
     
     public void addClass(String name, String password, String username){
        Random rand = new Random();
        int num = rand.nextInt(100000) + 1;
        while (ClassDatabaseHandler.checkRoom(num)) {
            num = rand.nextInt(100000) + 1;
        }
         ClassDatabaseHandler.createRoom(num, name, password);
         UserDatabaseHandler.addRoom(num, username);
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
