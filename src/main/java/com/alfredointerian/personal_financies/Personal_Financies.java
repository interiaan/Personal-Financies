/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.alfredointerian.personal_financies;

import com.alfredointerian.personal_financies.client.Auth;
import javax.swing.UIManager;

/**
 *
 * @author alfre
 */
public class Personal_Financies {

    public static void main(String[] args) {
        
        // <editor-fold defaultstate="collapsed" desc="Look and Feel">
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.err.println("Look and Feel couldn't be applied. " + e);
        }
        // </editor-fold>
        
        Auth auth = new Auth();
        auth.setVisible(true);
    }
}
