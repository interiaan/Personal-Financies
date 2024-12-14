/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.auth;

import static com.alfredointerian.personal_financies.database.DatabaseManager.connect;
import com.alfredointerian.personal_financies.database.BankingAccount;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Manages the session in current application execution
 * @author alfre
 */
public class Session {

    public static final int UNAUTHENTICATED = -1;
    
    private static Session instance = null;
    
    // Global Session Memory

    int accountId = UNAUTHENTICATED;
    int bankingAccountSelected = UNAUTHENTICATED;
    
    private ArrayList<BankingAccount> bankingAccountList = new ArrayList<>();

    public ArrayList<BankingAccount> getBankingAccountList() {
        return bankingAccountList;
    }
    

    private Session(String email, String password) {
        this.accountId = verifyCredentials(email, password);
    }
    
    public boolean isActive () {
        return ((instance != null));
    }
    
    /**
     * Creates a session instance
     * @param email
     * @param password
     * @return Session Instance (Reusable)
     */
    public static Session getInstance (String email, String password) {
        if (instance == null) {
            instance = new Session(email, password);
            
            System.out.println("Iniciando sesión..");
            
            if (instance.getAccountId() == UNAUTHENTICATED) {
                instance = null;
                System.out.println("Credenciales incorrectas");
            }
        }
        
        return instance;
    }
    
    /**
     * Return session active, if there's no active session, a null value will be returned
     * @return 
     */
    public static Session getInstance () {
        return instance;
    }
    
    /**
     * Set local banking account list with database information
     * @param list
     */
    public void setBankingAccountList (ArrayList<BankingAccount> list) {
        instance.bankingAccountList = list;
    }
    
    /**
     * Establish close the session instance, erasing data
     */
    public void logout() {
        Session.instance = null;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getBankingAccountSelected() {
        return bankingAccountSelected;
    }
    
    public void setBankingAccountSelected (int bankingAccountId) {
        this.bankingAccountSelected = bankingAccountId;
    }
    
    /**
     * Gets Account Email this inner SessionID
     * @return String Account Email
     */
    public String fetchAccountEmail () {
        try (Connection conn = connect();
             PreparedStatement verify = conn.prepareStatement("SELECT account_email FROM accounts WHERE account_id = ?")) {
            verify.setInt(1, this.accountId);

            ResultSet account = verify.executeQuery();

            if (account.next()) {
                return account.getString("account_email");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return "[Email not found with AccountID " + this.accountId + "]";
    }

    /**
     * Connects to the database to authenticate the account.
     *
     * @param email    User's email address.
     * @param password User's password.
     * @return int Session status. Returns the account ID if credentials are correct, otherwise -1.
     */
    private int verifyCredentials(String email, String password) {
        try (Connection conn = connect();
             PreparedStatement verify = conn.prepareStatement("SELECT account_id FROM accounts WHERE account_email = ? AND account_password = ?")) {
            verify.setString(1, email);
            verify.setString(2, password);

            ResultSet account = verify.executeQuery();

            if (account.next()) {
                return account.getInt("account_id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return UNAUTHENTICATED;
    }

}
