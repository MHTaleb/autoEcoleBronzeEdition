/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taleb
 */
public class ConnectionProvider {
    
    private static final String PASSWORD = "123456";
    private static final String USER = "root";
    private static final String JDBC_MYSQL = "jdbc:mysql://";
    private static final String HOST = "localhost:3306/";
    private static final String DATABASE_NAME = "autoecolebronze?";
    private static final String CHARACTER_ENCODING_ARABIC = "characterEncoding=UTF-8";

    private static Connection connection = null;
    private static ConnectionProvider instance = null;

    private ConnectionProvider() {
        try {
            ConnectionProvider.connection = DriverManager.getConnection(JDBC_MYSQL+HOST+DATABASE_NAME+CHARACTER_ENCODING_ARABIC, USER, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection getConnection(){
        
        if (ConnectionProvider.instance == null) {
            ConnectionProvider.instance = new ConnectionProvider();
        }
        return connection;
                
        
    }
    
}
