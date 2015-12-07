/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.Room;

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
     * Makes a new room if a room does not exist, then sends the user to the teacherResponse page.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Room room = new Room(0);

        HttpSession ses = request.getSession(true);
        if (ses.getAttribute("ClassNum") == null) {
            room = makeNewRoom();
            ses.setAttribute("ClassNum", room.getRoomNum());
        } else {
            room = new Room((int) ses.getAttribute("ClassNum"));
        }
        request.setAttribute(id, room);
        ses.setAttribute("roomNum", room.getRoomNum());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/teacherResponse.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Generates a random number for a room that does not already exist and then
     * creates that room in the database and as a Room class.
     */
    public Room makeNewRoom() {
        Random rand = new Random();
        int num = rand.nextInt(10000) + 1;
        while (ClassDatabaseHandler.checkRoom(num)) {
            num = rand.nextInt(10000) + 1;
        }
        ClassDatabaseHandler.createRoom(num, "Blank", "password");
        Room room = new Room(num);
        return room;
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
