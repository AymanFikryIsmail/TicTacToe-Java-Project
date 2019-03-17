/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dataBaseManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ayman
 */
public class DataBaseManager {
     private static String userName = "root";
    private static String password = "ayman123AYMAN";
    private static String con = "jdbc:mysql://localhost/gamedb";
    private static Connection databaseConnection = null;
   
    /**
     * connect to database
     * @return
     */
    public static Connection getConnection() {
        try {
            databaseConnection = DriverManager.getConnection(con, userName, password);
            System.out.println(" Database connected");
        } catch (SQLException ex) {
           ex.printStackTrace();
            System.out.println("connection failure");
        }
        return databaseConnection;
    }
    
    public static void closeConnection(){
    
         try {
             databaseConnection.close();
         } catch (SQLException ex) {
             Logger.getLogger(DataBaseManager.class.getName()).log(Level.SEVERE, null, ex);
         }
    
    }

}
