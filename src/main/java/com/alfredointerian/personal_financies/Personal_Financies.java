/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.alfredointerian.personal_financies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.alfredointerian.personal_financies.auth.Session;

/**
 *
 * @author alfre
 */


public class Personal_Financies {
    

    public static void main(String[] args) {
        
        Scanner read = new Scanner(System.in);
        
        System.out.println("""
                           ||| Personal Financies - v0.0.0 |||
                           by Alfredo Interián
                           """);
        
        System.out.println("- Log In");
        System.out.print("Email: ");
        String email = read.next();
        System.out.print("Password: ");
        String password = read.next();
        
        Session user = new Session(email, password);
        
        if (user.getAccount_id() == -1) {
            System.out.println("User couldn't be authenticated");
        } else {
            System.out.println("Welcome " + user.getAccount_id());
        }  
    }
}
