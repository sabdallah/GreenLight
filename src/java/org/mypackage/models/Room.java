package org.mypackage.models;

import java.io.Serializable;

/**
 * A class that handles all of the numbers to be passed to and from the
 * database. An intermediate between the classroom JSP pages and the
 * ClassDatabaseHandler.
 *
 * @author Sam Abdallah
 * @version .8
 */
public final class Room implements Serializable {

    private int understandingStudents, totalStudents, roomNum;
    public final String colors[] = {"00FF40", "00FF00", "40FF00", "80FF00", "BFFF00", "FFFF00", "FFBF00", "FF0000", "FF0040", "FE2E64"};

    public Room(int room) {
        roomNum = room;
        understandingStudents = 0;
        totalStudents = 0;
        loadData();
    }

    /**
     * Gets the data from the ClassDatabaseHandler for use in calculations by
     * this class.
     */
    public void loadData() {
        int[] results = ClassDatabaseHandler.getRoomStats(roomNum);
        understandingStudents = results[0];
        totalStudents = results[1];
    }

    /**
     * Adds an understanding student to the database.
     */
    public void addGood() {
        ClassDatabaseHandler.changeUnderstand(roomNum, 1);
    }

    /**
     * Removes an understanding student to the database.
     */
    public void removeGood() {
        ClassDatabaseHandler.changeUnderstand(roomNum, -1);
    }

    /**
     * Adds a student to the total in the database.
     */
    public void addKid() {
        ClassDatabaseHandler.changeTotal(roomNum, 1);
    }

    /**
     * Subtracts a student from the total in the database.
     */
    public void removeKid() {
        ClassDatabaseHandler.changeTotal(roomNum, -1);
    }

    /**
     * Calculates and returns the percentage of students who are understanding.
     */
    public double getPercent() {
        if (totalStudents != 0) {
            return ((double) understandingStudents / (double) totalStudents) * 100;
        }
        return 0;
    }

    /**
     * Returns the room number.
     */
    public int getRoomNum() {
        return roomNum;
    }

    /**
     * Picks the correct hexadecimal color to use for the given percent of
     * students understanding.
     */
    public String getHex() {
        double n = getPercent();
        int pick = 9 - (((int) n / 10) - 1);
        if (pick >= 10) {
            pick = 9;
        }
        return "#" + colors[pick];

    }

    /* Deprecated code from the previous IO storage system. Everything is now in SQL databases.
     private void loadIO() {
     try {
     br = new BufferedReader(new FileReader(destination + roomNum + ".txt"));
     String s = br.readLine();
     goodStudents = Integer.parseInt(s.split(";")[0]);
     totalStudents = Integer.parseInt(s.split(";")[1]);
     br.close();
            
     } catch (IOException e) {
     saveFile();
     loadIO();
     return;
     }
     }

     public void saveFile() {
     boolean created = false;
     while (!created) {
     try {
     PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(destination + roomNum + ".txt")));
     out.println(goodStudents + ";" + totalStudents);
     out.close();                                  // close the output file
     created = true;
     } catch (IOException e) {
     File file = new File(destination
     + "" + roomNum + ".txt");
     }
     }
     }*/
}
