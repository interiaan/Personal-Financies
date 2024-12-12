/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alfre
 */
public class DatabaseManager {
    /**
     * Connects to database automatically
     * 
     * @return Connection 
     * @throws SQLException 
     */
    public static Connection connect () throws SQLException {
       return DriverManager.getConnection("jdbc:sqlite:personal_financies.db");
    }
}
