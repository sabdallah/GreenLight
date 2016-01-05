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
        int[] results = new int[2];
        try {

            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            String sql;
            sql = "SELECT total, understand FROM Root.Data WHERE room =?";

            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            results[1] = rs.getInt("total");
            results[0] = rs.getInt("understand");

            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return results;
    }

    static String getName(int room) {
        Connection conn = null;
        String result = "";
        try {

            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            String sql;
            sql = "SELECT name FROM Root.Data WHERE room =?";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            result = rs.getString("name");

            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return result;
    }

    /**
     * Changes the total amount of students in the given room by int i.
     */
    static void changeTotal(int room, int i) {
        Connection conn = null;
        try {
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            String sql;
            sql = "SELECT total FROM Root.Data WHERE room =?";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            int total = rs.getInt("total");
            total += i;

            sql = "UPDATE Root.data SET total =? WHERE room =?";

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, total);
            preparedStatement.setInt(2, room);
            preparedStatement.executeUpdate();

            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    /**
     * Changes the amount of understanding students in the given room by int i.
     */
    static void changeUnderstand(int room, int i) {
        Connection conn = null;
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... changeUnderstand");
            String sql;
            sql = "SELECT understand FROM Root.Data WHERE room =?";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);

            ResultSet rs = preparedStatement.executeQuery();

            rs.next();

            int understand = rs.getInt("understand");
            understand += i;

            sql = "UPDATE Root.data SET understand =? WHERE room =?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, understand);
            preparedStatement.setInt(2, room);
            preparedStatement.executeUpdate();

            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

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
        if (checkRoom(room)) {
            return;
        }
        try {

            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();
            String sql;
            sql = "INSERT INTO Root.Data (room,understand,total, name, password) VALUES(?,0,0,?,?)";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

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
        boolean result = false;
        try {

            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... checkRoom");
            String sql;
            sql = "SELECT room FROM Root.Data WHERE room =?";
            PreparedStatement preparedStatement
                    = conn.prepareStatement(sql);

            preparedStatement.setInt(1, room);
            ResultSet rs = preparedStatement.executeQuery();

            rs.next();
            try {
                if (rs.getInt("room") != 0) {
                    result = true;
                }
            } catch (SQLException e) {
                result = false;
            }

            rs.close();
            preparedStatement.close();
            conn.close();
        } catch (SQLException se) {

            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

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

    public static boolean checkPassword(int id, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        try {
            if (!checkRoom(id)) {
                return false;
            }
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Root.Data";
            ResultSet rs = stmt.executeQuery(sql);

            try {
                while (!result && rs.next()) {
                    if (rs.getInt("room") == id) {
                        if (rs.getString("password").equals(password)) {
                            result = true;
                        } else {
                            break;
                        }
                    }
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
