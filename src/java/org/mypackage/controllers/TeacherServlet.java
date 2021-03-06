/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.Room;
import org.mypackage.models.UserDatabaseHandler;

/**
 *
 * @author 1670676
 */
@WebServlet(name = "TeacherServlet", urlPatterns = {"/TeacherServlet"})
public class TeacherServlet extends HttpServlet {

    private final String id = "teacherBean";  // The ID of the Person object
    private final String destination = "C:\\Users\\1670676\\Dropbox\\NetBeansProjects\\WebApplication1\\rooms\\";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * Makes a new room if a room does not exist, then sends the user to the
     * teacherResponse page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Room room = null;
        HttpSession ses = request.getSession(false);
        if(ses == null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
            dispatcher.forward(request, response);
        }

        String user = (String) ses.getAttribute("username");
        String classes = UserDatabaseHandler.getClasses(user);
        if (!classes.contains((String) request.getParameter("id"))) {
            return;
        }

        try {
            room = makeNewRoom(request.getParameter("id"));
            ses.setAttribute("roomNum", request.getParameter("id"));
        } catch (IOException e) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failIndex.html");
            dispatcher.forward(request, response);
            return;
        }

        ses.setAttribute("ClassNum", room.getRoomNum());

        request.setAttribute(id, room);
        room.loadData();
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherResponse.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Creates a new room object if the room that was requested exists.
     */
    public Room makeNewRoom(String roomNum) throws IOException {
        if (ClassDatabaseHandler.checkRoom(Integer.parseInt(roomNum))) {
            Room room = new Room(Integer.parseInt(roomNum));
            return room;
        }

        throw new IOException("Room doesn't exist");

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
