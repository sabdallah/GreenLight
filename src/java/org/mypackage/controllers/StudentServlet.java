/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
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
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;

/**
 *
 * @author 1670676
 */
@WebServlet(name = "StudentServlet", urlPatterns = {"/StudentServlet"})
public class StudentServlet extends HttpServlet {

    private final String id = "studentBean";  // The ID of the Person object
    private final String destination = "C:\\Users\\1670676\\Dropbox\\NetBeansProjects\\WebApplication1\\rooms\\";

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

        Room room = null;
        HttpSession ses = request.getSession(false);
        if (ses == null) {
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
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/NoMoreClass.html");
            dispatcher.forward(request, response);
            return;
        }
        ses.setAttribute("ClassNum", room.getRoomNum());
        if (ses.getAttribute("understand") == null) {
            ses.setAttribute("understand", true);
            room.addKid();
            room.addGood();
        }

        if (ses.getAttribute("question") == null) {
            ses.setAttribute("question", "");
        }

        boolean understand = (boolean) ses.getAttribute("understand");

        if (request.getParameter("formVal") != null && request.getParameter("formVal").equals("confused")) {
            ses.setAttribute("understand", false);
        } else if (request.getParameter("formVal") != null && request.getParameter("formVal").equals("understand")) {
            ses.setAttribute("understand", true);
        }

        if ((boolean) ses.getAttribute("understand") == true && understand != true) {
            room.addGood();
        } else if ((boolean) ses.getAttribute("understand") == false && understand == true) {
            room.removeGood();
        }
        if (request.getParameter("question") != null && !request.getParameter("question").equals(ses.getAttribute("question"))) {
            
                ClassDatabaseHandler.addQuestion((String) request.getParameter("question"), (String) ses.getAttribute("username"), (int) ses.getAttribute("ClassNum"));
                ses.setAttribute("question", request.getParameter("question"));
            }
            StringHolder qHolder = new StringHolder("\"" + (String) ses.getAttribute("question") + "\"");
            request.setAttribute("qHolder", qHolder);
            request.setAttribute(id, room);
            room.loadData();
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/studentResponse.jsp");
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
