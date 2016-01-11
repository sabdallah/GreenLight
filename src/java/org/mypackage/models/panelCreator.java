/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.models;

import java.io.PrintWriter;

/**
 *
 * @author 1620032
 */
public class panelCreator {

    public panelCreator(PrintWriter out, String username, boolean teacher) {
        out.println("<html>");
        out.println("<head>");
        out.println("  <!-- Loading Bootstrap -->\n" +
"        <link href=\"dist/css/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
"\n" +
"        <!-- Loading Flat UI -->\n" +
"        <link href=\"dist/css/flat-ui.css\" rel=\"stylesheet\">\n" +
"        <link href=\"docs/assets/css/demo.css\" rel=\"stylesheet\">\n" +
"\n" +
"        <link rel=\"shortcut icon\" href=\"img/favicon.ico\">");
        out.println("<title>Green Light</title>");
        out.println("</head>");
        out.println("<body>");

        if (!teacher) {
            //creates button to add class
            out.println("<br>\n"
                    + "<a href=\"joinclass.html\" class=\"btn btn-block btn-lg btn-info\">Join Class</a>  \n"
                    + "</br>\n");
            out.println("<h1>Welcome " + username + "</h1>");
            //creates the table of classes
            createTableStudent(out, username);
        } else {
            //creates button to add class
            out.println("<br>\n"
                    + "<a href=\"NewClassPage.html\" class=\"btn btn-block btn-lg btn-info\">Create new class</a>  \n"
                    + "</br>\n");
            out.println("<h1>Welcome " + username + "</h1>");
            //creates the table of classes
            createTableTeacher(out, username);

        }
                    out.println("</body>");
            out.println("</html>");
    }

    private void createTableStudent(PrintWriter out, String username) {
        out.println("<table border=\"5\">");
        //labels
        out.println("<tr>\n"
                + "<td>Class</td>\n"
                + "<td>Link</td>\n"
                + "</tr>");

        String classes = UserDatabaseHandler.getClasses(username);
        if (classes == null) {
            out.println("<tr>\n"
                    + "<td>No classes yet!</td>\n"
                    + "<td>Click above to add your first class!</td>\n"
                    + "</tr>");
        } else {
            String[] classArray = classes.split(",");
            int index = 0;
            while (index < classArray.length) {
                out.print("<tr>\n"
                        + "<td>" + ClassDatabaseHandler.getName(Integer.parseInt(classArray[index])) + "</td>\n"
                        + "<td><a href = \"StudentServlet?id=" + classArray[index] + "\">Join<a></td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }

    private void createTableTeacher(PrintWriter out, String username) {
        out.println("<table border=\"5\">");
        //labels
        out.println("<tr>\n"
                + "<td>Class</td>\n"
                + "<td>Link</td>\n"
                + "<td>Class ID</td>\n"
                + "</tr>");

        String classes = UserDatabaseHandler.getClasses(username);
        if (classes == null) {
            out.println("<tr>\n"
                    + "<td>No classes yet!</td>\n"
                    + "<td>Click above to create your first class!</td>\n"
                    + "</tr>");
        } else {
            String[] classArray = classes.split(",");
            int index = 0;
            while (index < classArray.length) {
                out.print("<tr>\n"
                        + "<td>" + ClassDatabaseHandler.getName(Integer.parseInt(classArray[index])) + "</td>\n"
                        + "<td><a href = \"TeacherServlet?id=" + classArray[index] + "\">Join<a></td>\n"
                        + "<td>" + classArray[index] + "</td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }
}
