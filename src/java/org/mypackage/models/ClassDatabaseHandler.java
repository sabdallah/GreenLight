/*
 * A class that carries out all database related operations for the class pages.
 */
package org.mypackage.models;

import java.sql.*;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author Sam Abdallah
 */
public class ClassDatabaseHandler {

    //Database URL
    static final String DB_URL = "jdbc:derby://localhost:1527/data";

    @SuppressWarnings("empty-statement")
    /**
     * Returns an array of integer that includes the statistics of the room
     * (Understanding students and total students).
     */
    static int[] getRoomStats(int room) {
        Connection conn = null;
        Statement stmt = null;
        int[] results = new int[2];
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... getRoomStats");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT total, understand FROM Root.Data WHERE room =" + room + "";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            results[1] = rs.getInt("total");
            results[0] = rs.getInt("understand");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
        return results;
    }
        static String getName(int room) {
        Connection conn = null;
        Statement stmt = null;
        String result = "";
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... getRoomStats");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT name FROM Root.Data WHERE room =" + room + "";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            result = rs.getString("name");

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
        return result;
    }

    /**
     * Changes the total amount of students in the given room by int i.
     */
    static void changeTotal(int room, int i) {
        Connection conn = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to database... ");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... changeTotal");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT total FROM Root.Data WHERE room =" + room + "";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            int total = rs.getInt("total");
            total += i;

            sql = "UPDATE Root.data SET total = " + total + " WHERE room =" + room + "";
            stmt.execute(sql);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
    }

    /**
     * Changes the amount of understanding students in the given room by int i.
     */
    static void changeUnderstand(int room, int i) {
        Connection conn = null;
        Statement stmt = null;
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... changeUnderstand");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT understand FROM Root.Data WHERE room =" + room + "";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            int understand = rs.getInt("understand");
            understand += i;

            sql = "UPDATE Root.data SET understand = " + understand + " WHERE room =" + room + "";
            stmt.execute(sql);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
    }

    /**
     * Creates a new row in the database. This row is an empty room with an ID
     * of the given number, 0 understanding students and 0 total students. This
     * will not create a duplicate room, it checks if the room already exists
     * and returns without doing anything if it does.
     */
    public static void createRoom(int room, String name, String password) {
        Connection conn = null;
        Statement stmt = null;
        if (checkRoom(room)) {
            return;
        }
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... createRoom");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO Root.Data (room,understand,total, name, password) VALUES(" + room + ",0,0,"+name +","+ password + ")";
            stmt.execute(sql);

            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
    }
    /**
     * Checks if a room exists in the database.
     */
    public static boolean checkRoom(int room) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... checkRoom");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT room FROM Root.Data WHERE room =" + room + "";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();
            try {
                if (rs.getInt("room") != 0) {
                    result = true;
                }
            } catch (SQLException e) {
                result = false;
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Disconnecting!");
        return result;
    }

}
