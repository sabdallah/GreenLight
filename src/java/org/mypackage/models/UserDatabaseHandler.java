/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import static org.mypackage.models.ClassDatabaseHandler.checkRoom;

/**
 *
 * @author 1670676
 */
public class UserDatabaseHandler {

    //Database URL
    static final String DB_URL = "jdbc:derby://localhost:1527/data";
    private static Object ClassDatabseHandler;

    @SuppressWarnings("empty-statement")

    /**
     * Gets a string list of classes that the student is in.
     */
    public static String getClasses(String email) {
        Connection conn = null;
        Statement stmt = null;
        String result = "";
        email = email.substring(0,email.indexOf("@")) + " " + email.substring(email.indexOf("@")+1);
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... getClasses");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT classes FROM Root.accounts WHERE CAST(email AS VARCHAR(128)) ='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            result = rs.getString("classes");

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
    
    public static void addRoom(int id, String email){
        email = email.substring(0,email.indexOf("@")) + " " + email.substring(email.indexOf("@")+1);
        Connection conn = null;
        Statement stmt = null;
        String result = null;
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... isVerified");
            stmt = conn.createStatement();
            
            String sql;
            
            sql = "SELECT classes FROM Root.accounts WHERE CAST(email AS VARCHAR(128)) ='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            result = rs.getString("CLASSES");
            if(result == null)
                result = "";
            else
                result = result + ",";
            
            sql = "UPDATE Root.accounts SET classes = '" + result + id +"' WHERE CAST(email AS VARCHAR(128)) ='" + email + "'";
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
    
    public static void removeRoom(int id, String email){
        email = email.substring(0,email.indexOf("@")) + " " + email.substring(email.indexOf("@")+1);
        Connection conn = null;
        Statement stmt = null;
        String result = null;
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... isVerified");
            stmt = conn.createStatement();
            
            String sql;
            
            sql = "SELECT classes FROM Root.accounts WHERE CAST(email AS VARCHAR(128)) ='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            result = rs.getString("CLASSES");
            if(result == null)
                result = "NULL";
            else if(result.contains(""+id)){
                String[] array = result.split(",");
                String newResult = "";
                for(String x:array){
                    if(x != ""+id){
                        if(newResult.length()>2)
                            newResult = newResult + ",";
                        newResult = newResult + id;
                    } 
                }
                if(newResult.length() != 0)
                    newResult = "'" + newResult + "'";
                else
                    newResult = "NULL";
                
                result = newResult;
            }
            
            sql = "UPDATE Root.accounts SET classes = " + result +" WHERE CAST(email AS VARCHAR(128)) ='" + email + "'";
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

    public static boolean isVerified(String email) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        email = email.substring(0,email.indexOf("@")) + " " + email.substring(email.indexOf("@")+1);
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... isVerified");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT verified FROM Root.accounts WHERE email ='" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);

            rs.next();

            result = rs.getBoolean("verified");

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
     * Verifies an account
     */
    public static void verify(String email) {
        Connection conn = null;
        Statement stmt = null;
        email = email.substring(0,email.indexOf("@")) + " " + email.substring(email.indexOf("@")+1);
        try {
            System.out.println("Connecting to database... ");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... verify");
            stmt = conn.createStatement();
            String sql;

            sql = "UPDATE Root.accounts SET verified = true WHERE email ='" + email + "'";
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

    public static void addUser(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        if (isUser(username)) {
            return;
        }
        username = username.substring(0,username.indexOf("@")) + " " + username.substring(username.indexOf("@")+1);
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... addUser");
            stmt = conn.createStatement();
            String sql;
            sql = "INSERT INTO Root.accounts (email, password, verified, teacher) VALUES('" + username + "','" + password + "',false, false)";
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

    public static boolean isUser(String username) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        username = username.substring(0,username.indexOf("@")) + " " + username.substring(username.indexOf("@")+1);
        try {

            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... isUser");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT email FROM Root.accounts";
            ResultSet rs = stmt.executeQuery(sql);

            try {
                while(!result && rs.next())
                {System.out.println(rs.getString("email") + ";" + username);
                if (rs.getString("email").equals(username)) {
                    result = true;
            
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

    public static boolean checkPassword(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        try {
            if(!isUser(username))
                return false;
            username = username.substring(0,username.indexOf("@")) + " " + username.substring(username.indexOf("@")+1);
            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... checkPassword");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Root.accounts";
            ResultSet rs = stmt.executeQuery(sql);

            try {
                while(!result && rs.next())
                {System.out.println(rs.getString("email") + ";" + username);
                if (rs.getString("email").equals(username)) {
                    if(rs.getString("password").equals(password))
                        result = true;
                    else
                        break;
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
    
    public static boolean isTeacher(String username) {
        Connection conn = null;
        Statement stmt = null;
        boolean result = false;
        
        try {
            if(!isUser(username))
                return false;
            username = username.substring(0,username.indexOf("@")) + " " + username.substring(username.indexOf("@")+1);
            System.out.println("Connecting to database...");
            conn = ((DataSource) new InitialContext().lookup("jdbc/ConfusOMeter")).getConnection();

            System.out.println("Creating statement... checkPassword");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM Root.accounts";
            ResultSet rs = stmt.executeQuery(sql);

            try {
                while(!result && rs.next())
                {
                if (rs.getString("email").equals(username)) {
                    if(rs.getBoolean("teacher") == true)
                        result = true;
                    else
                        break;
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
