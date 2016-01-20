/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.models;

import java.io.PrintWriter;

/**
 *
 * @author sam
 */
public class QuestionTableCreator {
    
     public QuestionTableCreator(PrintWriter out, int room) {
        out.println("<table class=\"table table-striped\">");
        //labels
        out.println("<tr>\n"
                + "<td>Questions</td>\n"
                + "<td>Dismiss</td>\n"
                + "<td>Report</td>\n"
                + "</tr>");

        String[] questions = ClassDatabaseHandler.getQuestions(room);
        if (questions == null) {
            out.println("<tr>\n"
                    + "<td>No questions yet!</td>\n"
                    + "<td></td>\n"
                    + "</tr>");
        } else {
            int index = 0;
            while (index < questions.length) {
                String question = questions[index].split(ClassDatabaseHandler.getCode(room))[0];
                String uid = questions[index].split(ClassDatabaseHandler.getCode(room))[1];
                out.print("<tr>\n"
                        + "<td>" + question +"</td>\n"
                        + "<td>" + "<input id=\"Delete\" type=\"button\" value=\"<img src=\"http://files.softicons.com/download/web-icons/minicons-numbers-icons-by-icojam/png/16x16/red/065.png\">\" onclick=\"deleteQuestion(" + index + ");\" />" + "</td>\n"
                        + "<td>" + "<input id=\"Delete\" type=\"button\" value=\"<img src=\"http://findicons.com/files/icons/2564/max_mini_icon/16/flag_red.png\">\" onclick=\"deleteQuestion(" + index + ");\" />" + "</td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }
    
}
