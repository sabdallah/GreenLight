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
    
     private void createTable(PrintWriter out, int room) {
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
                        + "<td></td>\n"
                        + "<td></td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }
    
}
