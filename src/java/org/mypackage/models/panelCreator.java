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

        if (!teacher) {
            //student

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Green Light</title>        "
                    + "<!-- Loading Bootstrap -->\n"
                    + "        <link href=\"dist/css/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "        <!-- Loading Flat UI -->\n"
                    + "        <link href=\"dist/css/flat-ui.css\" rel=\"stylesheet\">\n"
                    + "        <link href=\"docs/assets/css/demo.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "        <link rel=\"shortcut icon\" href=\"img/favicon.ico\">"
            );
            out.println("</head>"
                    + " <style>\n"
                    + "            html {\n"
                    + "                position: relative;\n"
                    + "                min-height: 100%;\n"
                    + "            }\n"
                    + "            body {\n"
                    + "                /* Margin bottom by footer height */\n"
                    + "                margin-bottom: 50px;\n"
                    + "            }\n"
                    + "            .footer {\n"
                    + "                position: absolute;\n"
                    + "                bottom: 0;\n"
                    + "                width: 100%;\n"
                    + "                /* Set the fixed height of the footer here */\n"
                    + "                height: 50px;\n"
                    + "                background-color: #2C3E50;\n"
                    + "            }\n"
                    + "            .animate {\n"
                    + "                position: relative; \n"
                    + "                top: -30px;\n"
                    + "                width: 100%;\n"
                    + "                margin-top: 0px;\n"
                    + "                border-top: 0px;\n"
                    + "                padding-top: 0px;\n"
                    + "                height: 300px;\n"
                    + "                -webkit-animation: animate_bg 20s;\n"
                    + "                animation: animate_bg 20s;\n"
                    + "                -webkit-animation-iteration-count: infinite;\n"
                    + "                animation-iteration-count: infinite;\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "\n"
                    + "            @keyframes animate_bg {\n"
                    + "                0%   {background:red;}\n"
                    + "                25%  {background:yellow;}\n"
                    + "                50% {background:green;}\n"
                    + "                75%  {background:yellow;}\n"
                    + "                100%   {background:red;}\n"
                    + "            }\n"
                    + "\n"
                    + "            @-webkit-keyframes animate_bg {\n"
                    + "                0%   {background:red;}\n"
                    + "                25%  {background:yellow;}\n"
                    + "                50% {background:green;}\n"
                    + "                75%  {background:yellow;}\n"
                    + "                100%   {background:red;}\n"
                    + "            }\n"
                    + "        </style>");
            out.println("<body>       "
                    + "<div  class=\"animate\">\n"
                    + "            <br>\n"
                    + "            <br>\n"
                    + "            <center><h3><a href=\"/index.html\">Green Light<a></h3></center>\n"
                    + "        </div>\n"
                    + "        <br><center>");

            out.println("<h4>Welcome " + username + "</h4>");
            //creates the table of classes
            createTableStudent(out, username);
            out.println("</center><br>\n"
                    + "<a href=\"joinclass.html\" class=\"btn btn-block btn-lg btn-info\">Join New Class</a>  \n"
                    + "</br>\n");
            out.println("</body>");
            out.println("</html>");

            return;
        } else {
            //teacher
            out.println("<html>");
            out.println("</head>"
                    + " <style>\n"
                    + "            html {\n"
                    + "                position: relative;\n"
                    + "                min-height: 100%;\n"
                    + "            }\n"
                    + "            body {\n"
                    + "                /* Margin bottom by footer height */\n"
                    + "                margin-bottom: 50px;\n"
                    + "            }\n"
                    + "            .footer {\n"
                    + "                position: absolute;\n"
                    + "                bottom: 0;\n"
                    + "                width: 100%;\n"
                    + "                /* Set the fixed height of the footer here */\n"
                    + "                height: 50px;\n"
                    + "                background-color: #2C3E50;\n"
                    + "            }\n"
                    + "            .animate {\n"
                    + "                position: relative; \n"
                    + "                top: -30px;\n"
                    + "                width: 100%;\n"
                    + "                margin-top: 0px;\n"
                    + "                border-top: 0px;\n"
                    + "                padding-top: 0px;\n"
                    + "                height: 300px;\n"
                    + "                -webkit-animation: animate_bg 20s;\n"
                    + "                animation: animate_bg 20s;\n"
                    + "                -webkit-animation-iteration-count: infinite;\n"
                    + "                animation-iteration-count: infinite;\n"
                    + "                text-align: center;\n"
                    + "            }\n"
                    + "\n"
                    + "            @keyframes animate_bg {\n"
                    + "                0%   {background:red;}\n"
                    + "                25%  {background:yellow;}\n"
                    + "                50% {background:green;}\n"
                    + "                75%  {background:yellow;}\n"
                    + "                100%   {background:red;}\n"
                    + "            }\n"
                    + "\n"
                    + "            @-webkit-keyframes animate_bg {\n"
                    + "                0%   {background:red;}\n"
                    + "                25%  {background:yellow;}\n"
                    + "                50% {background:green;}\n"
                    + "                75%  {background:yellow;}\n"
                    + "                100%   {background:red;}\n"
                    + "            }\n"
                    + "        </style>");
            out.println("<title>Green Light</title>        "
                    + "<!-- Loading Bootstrap -->\n"
                    + "        <link href=\"dist/css/vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "        <!-- Loading Flat UI -->\n"
                    + "        <link href=\"dist/css/flat-ui.css\" rel=\"stylesheet\">\n"
                    + "        <link href=\"docs/assets/css/demo.css\" rel=\"stylesheet\">\n"
                    + "\n"
                    + "        <link rel=\"shortcut icon\" href=\"img/favicon.ico\">"
            );
            out.println("</head>");
            out.println("<body>       "
                    + "<div  class=\"animate\">\n"
                    + "            <br>\n"
                    + "            <br>\n"
                    + "            <center><h3><a href=\"/index.html\">Green Light<a></h3></center>\n"
                    + "        </div>\n"
                    + "        <br><center>");
            //creates button to add class

            out.println("<h4>Welcome " + username + "</h4>");
            //creates the table of classes
            createTableTeacher(out, username);
            out.println("</center></body>");
            out.println("<br>\n"
                    + "<a href=\"NewClassPage.html\" class=\"btn btn-block btn-lg btn-info\">Create new class</a>  \n"
                    + "</br>\n");
            out.println("</html>");
            return;

        }
    }

    private void createTableStudent(PrintWriter out, String username) {
        out.println("<table class=\"table-striped\" border=\"5\">");
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
                        + "<td><a href = \"DeleteClassServlet?id=" + classArray[index] + "\">Leave<a></td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }

    private void createTableTeacher(PrintWriter out, String username) {
        out.println("<table class=\"table-striped\" border=\"5\">");
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
                        + "<td><a href = \"DeleteClassServlet?id=" + classArray[index] + "\">Delete<a></td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }
}
