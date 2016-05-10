/*
 * Creates and populates the table where questions are held on the teacher interface.
 */
package org.mypackage.models;

import java.io.PrintWriter;

/**
 *
 * @author Sam Abdallah
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
                        + "<td>" + "<input id=\"Delete\" type=\"button\" value=\"Delete\" onclick=\"deleteQuestion(" + index + ");\" />" + "</td>\n"
                        + "<td>" + "<input id=\"Report\" type=\"button\" value=\"Report\" onclick=\"report(" + index + ");\" />" + "</td>\n"
                        + "</tr>");
                index++;
            }
        }
        out.println("</table>");
    }
    
}
