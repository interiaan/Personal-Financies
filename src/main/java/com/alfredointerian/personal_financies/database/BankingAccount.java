/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.database;

import static com.alfredointerian.personal_financies.database.DatabaseManager.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author alfre
 */
public class BankingAccount {
    private int bankingAccountId;
    private String bankingAccountName;
    private int bankingAccountOwner;

    public BankingAccount(String bankingAccountName, int bankingAccountOwner) {
       this.bankingAccountName = bankingAccountName;
       this.bankingAccountOwner = bankingAccountOwner;
    }
    
    public void setAccountId (int accountId) {
        this.bankingAccountId = accountId;
    }
    
    /**
     * Add the stored banking account data to database
     */
    public void addBankingAccountToDatabase () {
        try (Connection conn = DatabaseManager.connect(); PreparedStatement add = conn.prepareStatement("INSERT INTO banking_accounts (bk_acc_name, bk_acc_owner) VALUES (?, ?)")) {
            add.setString(1, this.bankingAccountName);
            add.setInt(2, this.bankingAccountOwner);
            add.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public String fetchBankingAccountName () {
        try (Connection conn = connect();
             PreparedStatement verify = conn.prepareStatement("SELECT bk_acc_name FROM banking_accounts WHERE bk_acc_id = ?")) {
            verify.setInt(1, this.bankingAccountId);

            ResultSet account = verify.executeQuery();

            if (account.next()) {
                return account.getString("bk_acc_name");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return "[Banking account Name not found]";
    }
}