/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    
    /**
     * Get all banking accounts of the user account
     * @param accountId Account ID related with the banking accounts
     * @return ArrayList List with banking accounts
     */
    public static ArrayList<BankingAccount> getBankingAccounts (int accountId) {
        ArrayList<BankingAccount> bankingAccounts = new ArrayList<>();
        
        try (Connection conn = connect();
             PreparedStatement verify = conn.prepareStatement("SELECT * FROM banking_accounts WHERE bk_acc_owner = ?")) {
            verify.setInt(1, accountId);
            ResultSet account = verify.executeQuery();

            while (account.next()) {
                bankingAccounts.add(new BankingAccount(account.getString("bk_acc_name"), account.getInt("bk_acc_owner"))); // Adds banking account to list
                bankingAccounts.getLast().setAccountId(account.getInt("bk_acc_id")); // Sets the last addtion adding the account id
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return bankingAccounts;
    }
}
