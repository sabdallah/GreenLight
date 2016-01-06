/*
 * A servlet that passes the Percent Understanding to the AJAX in the teacher and student JSP pages.
 */
package org.mypackage.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.ClassDatabaseHandler;
import org.mypackage.models.Room;

/**
 *
 * @author Sam Abdallah
 */
public class PercentServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String destination = "C:\\Users\\1670676\\Dropbox\\NetBeansProjects\\WebApplication1\\rooms\\";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        }
    
    public Room makeNewRoom(String roomNum) throws IOException{
        if(ClassDatabaseHandler.checkRoom(Integer.parseInt(roomNum))){
            Room room = new Room(Integer.parseInt(roomNum));
        return room;}
        
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

        HttpSession ses = request.getSession();
        Room room = makeNewRoom((String) ("" + ses.getAttribute("ClassNum")));
        response.getWriter().write("" + Math.round((float)room.getPercent()));
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
