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
import java.util.ArrayList;

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
    
    public int getAccountId () {
        return this.bankingAccountId;
    }
    
    public void setAccountId (int accountId) {
        this.bankingAccountId = accountId;
    }
    
    /**
     * Add the stored banking account data to database
     */
    public void addBankingAccountToDatabase () {
        try (Connection conn = connect(); PreparedStatement add = conn.prepareStatement("INSERT INTO banking_accounts (bk_acc_name, bk_acc_owner) VALUES (?, ?)")) {
            add.setString(1, this.bankingAccountName);
            add.setInt(2, this.bankingAccountOwner);
            add.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public void deleteBankingAccountInDatabase () {
        try (Connection conn = connect(); PreparedStatement delete = conn.prepareStatement("DELETE FROM banking_accounts WHERE bk_acc_id = ?")) {
            delete.setInt(1, this.bankingAccountId);
            
            delete.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public ArrayList<Movement> fetchLatestMovements () {
        try (Connection conn = connect();
                PreparedStatement latestMovementsStmt = conn.prepareStatement("SELECT * FROM movements WHERE bk_acc_id = ? ORDER BY movement_date, movement_time DESC")) {
            latestMovementsStmt.setInt(1, this.bankingAccountId);
            ResultSet latestMovements = latestMovementsStmt.executeQuery();
            
            ArrayList<Movement> movements = new ArrayList<>();
            while (latestMovements.next()) {                
                movements.add(new Movement(latestMovements.getString("movement_concept"), latestMovements.getString("movement_description"), latestMovements.getDouble("movement_amount"), latestMovements.getString("movement_date"), latestMovements.getString("movement_time"), latestMovements.getInt("bk_acc_id")));
            }
            
            return movements;

        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return new ArrayList<>();
    }
    
    /**
     * Gets related banking account movements
     * @return Double Total 
     */
    public double fetchTotalBalance () {
        try (Connection conn = connect();
            PreparedStatement totalBalanceStmt = conn.prepareStatement("SELECT SUM(movement_amount) as total FROM movements WHERE bk_acc_id = ?;")) {
            totalBalanceStmt.setInt(1, this.getAccountId());

            ResultSet totalBalance = totalBalanceStmt.executeQuery();

            if (totalBalance.next()) {
                return totalBalance.getDouble("total");
            }        
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return 0;
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
    
    /**
     * Get all banking accounts of the user account
     * @param accountId Account ID related with the banking accounts
     * @return ArrayList List with banking accounts
     */
    public static ArrayList<BankingAccount> fetchBankingAccounts (int accountId) {
        ArrayList<BankingAccount> bankingAccounts = new ArrayList<>();
        
        try (Connection conn = connect();
             PreparedStatement verify = conn.prepareStatement("SELECT * FROM banking_accounts WHERE bk_acc_owner = ?")) {
            verify.setInt(1, accountId);
            ResultSet account = verify.executeQuery();

            while (account.next()) {
                bankingAccounts.add(new BankingAccount(account.getString("bk_acc_name"), account.getInt("bk_acc_owner"))); // Adds banking account to list
                bankingAccounts.getLast()
                        .setAccountId(account.getInt("bk_acc_id")); // Sets the last addtion adding the account id
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return bankingAccounts;
    }

    public String getBankingAccountName() {
        return this.bankingAccountName;
    }
}
