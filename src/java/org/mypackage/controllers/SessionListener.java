/*
 * Carries out the required processes to end a user's session if they log out or time out.
 */
package org.mypackage.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.mypackage.models.Room;

/**
 *
 * @author Sam Abdallah
 */
public class SessionListener implements HttpSessionListener {

    private final String destination = "C:\\Users\\1670676\\Dropbox\\NetBeansProjects\\WebApplication1\\rooms\\";

    @Override
    /**
     * Prints to the console when a session is created.
     */
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created");
    }

    @Override
    /**
     * If the user is a student, removes them from the room.
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession ses = se.getSession();
        if (ses.getAttribute("understand") != null) {
            Room room = new Room(Integer.parseInt((String) ses.getAttribute("roomNum")));
            room.removeKid();
            if ((boolean) ses.getAttribute("understand") == true) {
                room.removeGood();
            }
            ses.setAttribute("understand",null); 
            room.loadData();
        }
    }

}
