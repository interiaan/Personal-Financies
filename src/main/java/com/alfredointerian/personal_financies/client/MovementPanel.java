/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.alfredointerian.personal_financies.client;

/**
 *
 * @author alfre
 */
public class MovementPanel extends javax.swing.JPanel {

    /**
     * Creates new form MovementPanel
     * @param concept Movement concept to show
     * @param date Movement date to show
     * @param time Movement time to show
     * @param amount Movement amount to show
     */
    public MovementPanel(String concept, String date, String time, String amount) {
        initComponents();
        
        ConceptLabel.setText(concept);
        DateLabel.setText(date + " " + time);
        AmountLabel.setText("$ " + amount);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ConceptLabel = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        AmountLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();

        setForeground(new java.awt.Color(255, 255, 255));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ConceptLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ConceptLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ConceptLabel.setText("<Concept>");
        add(ConceptLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        DateLabel.setFont(new java.awt.Font("Microsoft JhengHei UI", 0, 12)); // NOI18N
        DateLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        DateLabel.setText("<Date & Time>");
        add(DateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        AmountLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        AmountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        AmountLabel.setText("<Amount>");
        add(AmountLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 200, -1));
        add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 5, 350, -1));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 65, 350, -1));

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 0, 12)); // NOI18N
        jLabel1.setText("Amount");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        System.out.println("Generar recibo del movimiento");
    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AmountLabel;
    private javax.swing.JLabel ConceptLabel;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
