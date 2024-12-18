/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alfredointerian.personal_financies.database;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author alfre
 */
public class Movement {
   
    private int movementId;
    private String movementConcept;
    private String movementDescription;
    private double movementAmount;
    private String movementDate;
    private String movementTime;
    private int bk_acc_id;

    public Movement(String movementConcept, String movementDescription, double movementAmount, String movementDate, String movementTime, int bk_acc_id) {
        this.movementConcept = movementConcept;
        this.movementDescription = movementDescription;
        this.movementAmount = movementAmount;
        this.movementDate = movementDate;
        this.movementTime = movementTime;
        this.bk_acc_id = bk_acc_id;
    }

    public String getMovementConcept() {
        return movementConcept;
    }

    public double getMovementAmount() {
        return movementAmount;
    }

    public String getMovementDate() {
        return movementDate;
    }

    public String getMovementTime() {
        return movementTime;
    }
    
    
    
    public void setMovementId (int movementId) {
        this.movementId = movementId;
    }
    
    //region Database Actions
    
    public void addMovementToDatabase () {
        try (Connection conn = DatabaseManager.connect(); 
                PreparedStatement addMovementStmt = conn.prepareStatement("INSERT INTO movements (movement_concept, movement_description, movement_amount, movement_date, movement_time, bk_acc_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            addMovementStmt.setString(1, this.movementConcept);
            addMovementStmt.setString(2, this.movementDescription);
            addMovementStmt.setDouble(3, this.movementAmount);
            addMovementStmt.setString(4, this.movementDate);
            addMovementStmt.setString(5, this.movementTime);
            addMovementStmt.setInt(6, this.bk_acc_id);
            
            addMovementStmt.execute();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    //endregion
}
