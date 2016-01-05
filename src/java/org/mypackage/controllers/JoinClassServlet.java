/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;
import org.mypackage.models.panelCreator;

/**
 *
 * @author sam
 */
public class JoinClassServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String id1 = "";
        int id2 = 0;
        String password = "";
        HttpSession ses = request.getSession();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failJoinClassPage.jsp");
        if (request.getParameter("classID") != null) {
            id1 = (String) request.getParameter("classID");
            id2 = Integer.parseInt(id1);
        }
        if (request.getParameter("password") != null) {
            password = (String) request.getParameter("password");
        }
        if (!ClassDatabaseHandler.checkRoom(id2)) {
            request.setAttribute("error", new StringHolder("There is no class with that ID in our database."));
            dispatcher.forward(request, response);
            return;
        } else if (!ClassDatabaseHandler.checkPassword(id2, password)) {
            request.setAttribute("error", new StringHolder("Password incorrect."));
            dispatcher.forward(request, response);
            return;
        }
        String username = (String) ses.getAttribute("username");
        UserDatabaseHandler.addRoom(id2, username);
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
