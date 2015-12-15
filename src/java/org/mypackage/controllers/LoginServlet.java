/*
 * Servlet that checks the database to verify login info and passes the user to the dashboard if info is valid.
 * Also passes the username as an attribute of the HttpSession.
 */
package org.mypackage.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.mypackage.models.StringHolder;
import org.mypackage.models.UserDatabaseHandler;

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
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/failLogin.jsp");
        if (request.getParameter("username") != null) {
            username = (String) request.getParameter("username");
        }
        if (request.getParameter("password") != null) {
            password = (String) request.getParameter("password");
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
        ses.setAttribute("username", username);
        if (!UserDatabaseHandler.isTeacher(username)) {
            //student
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Green Light</title>");
            out.println("</head>");
            out.println("<body>");
            //creates button to add class
            out.println("<form action=\"NewClassServlet\">\n" +
                        "<input type=\"submit\" value=\"Add Class\" />\n" +
                        "</form>");
            out.println("<h1>Welcome " + username + "</h1>");
            //creates the table of classes
            createTable(out,request.getParameter("username"));
            out.println("</body>");
            out.println("</html>");
            
            return;
        } else {
            //teacher
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Green Light</title>");
            out.println("</head>");
            out.println("<body>");
            //creates button to add class
            out.println("<br>\n" +
"                <a href=\"NewClassPage.html\" class=\"btn btn-block btn-lg btn-info\">Create new class</a>  \n" +
"                </br>\n" + //TO DO: sitch this to add class
                        "<input type=\"submit\" value=\"Create New Class\" />\n" +
                        "</form>");
            out.println("<h1>Welcome " + username + "</h1>");
            //creates the table of classes
            createTable(out,request.getParameter("username"));
            out.println("</body>");
            out.println("</html>");
            return;

        }
    }
    
    /**
     * Creates the table of classes on the page
     * @param out 
     */
    private void createTable(PrintWriter out, String username)
    {
        out.println("<table border=\"5\">");
        //labels
        out.println("<tr>\n" +
                    "<td>Class</td>\n" +
                    "<td>Link</td>\n" +
                    "</tr>");
        
        String classes = UserDatabaseHandler.getClasses(username);
        if(classes == null)
        {
            out.println("<tr>\n" +
                    "<td>No classes yet!</td>\n" +
                    "<td>Click above to add your first class!</td>\n" +
                    "</tr>");
        }else{
            String[] classArray = classes.split(",");
            int index = 0;
            while(index+1 < classArray.length)
            {
                out.print("<tr>\n" +
                    "<td>"+ classArray[index]+"</td>\n" +
                    "<td>"+ classArray[index+1]+"</td>\n" +
                    "</tr>");
                index += 2;
            }
        }
        out.println("</table>");
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
