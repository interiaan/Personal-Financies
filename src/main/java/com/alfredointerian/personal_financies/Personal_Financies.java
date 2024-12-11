/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.alfredointerian.personal_financies;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static com.alfredointerian.personal_financies.database.DatabaseManager.connect;
import java.sql.ResultSet;

import com.alfredointerian.personal_financies.client.Auth;

/**
 *
 * @author alfre
 */
public class Personal_Financies {

    public static void main(String[] args) {
        Auth auth = new Auth();
        auth.setVisible(true);
    }
    
    private static void viewBankingAccounts () {
        try (Connection conn = connect(); PreparedStatement bankingAccounts = conn.prepareStatement("SELECT * FROM banking_accounts")) {
            ResultSet rs = bankingAccounts.executeQuery();
            
            while (rs.next()) {
                System.out.println( rs.getString("bk_acc_name"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
