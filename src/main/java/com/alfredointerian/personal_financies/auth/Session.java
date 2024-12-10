/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.auth;

import static com.alfredointerian.personal_financies.database.DatabaseManager.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Manages the session in current application execution
 * @author alfre
 */
public class Session {

    int account_id = -1;

    public Session(String email, String password) {
        this.account_id = verifyCredentials(email, password);
    }

    public int getAccount_id() {
        return account_id;
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

        return -1;
    }

}
