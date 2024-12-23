/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.alfredointerian.personal_financies.client;

import java.time.LocalDate;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author alfre
 */
public class DateChooser extends javax.swing.JPanel {

    /**
     * Creates new form DateChooser
     */
    public DateChooser() {
        initComponents();
        setDayField(1);
        setYearField();

        setCurrentDate();
    }

    /**
     * Modifies Model List in DayField
     *
     * @param month Number of Month
     */
    private void setDayField(int month) {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        // Get the selected year from YearField
        String selectedYearString = (String) YearField.getSelectedItem();
        int selectedYear = selectedYearString != null ? Integer.parseInt(selectedYearString) : Calendar.getInstance().get(Calendar.YEAR);

        // Determine if the selected year is a leap year
        boolean isLeapYear = (selectedYear % 4 == 0 && selectedYear % 100 != 0) || (selectedYear % 400 == 0);

        // Get the number of days in the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, selectedYear);
        calendar.set(Calendar.MONTH, month - 1);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        // Adjust for leap years in February
        if (month == 2 && isLeapYear) {
            daysInMonth = 29;
        }

        // Add days to the model
        for (int day = 1; day <= daysInMonth; day++) {
            model.addElement(String.valueOf(day));
        }

        // Set the model to the DayField
        DayField.setModel(model);
    }

    /**
     * * Generates years between 1980 and current year
     */
    private void setYearField() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int year = currentYear; year >= 1980; year--) {
            model.addElement(String.valueOf(year));
        }

        YearField.setModel(model);
    }

    /**
     * Selects the current date in the ComboBoxes
     */
    private void setCurrentDate() {
        LocalDate currentDate = LocalDate.now();

        YearField.setSelectedItem(String.valueOf(currentDate.getYear()));
        MonthField.setSelectedIndex(currentDate.getMonthValue() - 1);
        setDayField(currentDate.getMonthValue()); // Update DayField based on the month
        DayField.setSelectedItem(String.valueOf(currentDate.getDayOfMonth()));
    }

    /*
    * Get date selected in format yyyy-MM-dd
     */
    protected String getDate() {
        String year = String.valueOf(YearField.getSelectedItem());

        String month = String.valueOf(MonthField.getSelectedIndex() + 1);
        if (month.length() == 1) {
            month = "0" + month;
        }

        String day = String.valueOf(DayField.getSelectedItem());
        if (day.length() == 1) {
            day = "0" + day;
        }

        return year + "-" + month + "-" + day;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DayField = new javax.swing.JComboBox<>();
        MonthField = new javax.swing.JComboBox<>();
        YearField = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        MonthField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        MonthField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MonthFieldItemStateChanged(evt);
            }
        });

        YearField.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                YearFieldItemStateChanged(evt);
            }
        });

        jLabel1.setText("Day");

        jLabel2.setText("Month");

        jLabel3.setText("Year");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(MonthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(YearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DayField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MonthField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YearField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MonthFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MonthFieldItemStateChanged
        setDayField(MonthField.getSelectedIndex() + 1);
    }//GEN-LAST:event_MonthFieldItemStateChanged

    private void YearFieldItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_YearFieldItemStateChanged
        setDayField(MonthField.getSelectedIndex() + 1);
    }//GEN-LAST:event_YearFieldItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DayField;
    private javax.swing.JComboBox<String> MonthField;
    private javax.swing.JComboBox<String> YearField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
