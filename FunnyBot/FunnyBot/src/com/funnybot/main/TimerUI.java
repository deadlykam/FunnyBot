/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funnybot.main;

import com.funnybot.twittercontrollers.TimerCycleController;

/**
 *
 * @author Kamran
 */
public class TimerUI extends javax.swing.JFrame {

    /**
     * Creates new form TimerUI
     */
    public TimerUI() {
        initComponents();
        
        // Setting the second text
        TxtSeconds.setText("" + TimerCycleController.GetInstance()
                .GetSeconds());
        
        // Setting the minute text
        TxtMinutes.setText("" + TimerCycleController.GetInstance()
                .GetMinutes());
        
        // Setting the hour text
        TxtHours.setText("" + TimerCycleController.GetInstance()
                .GetHours());
    }

    /**
     * This method checks if the given string value
     * is numerical.
     * 
     * @return True means the string is numerical, false
     *         otherwise, of type boolean
     */
    private boolean IsNumerical(String value)
    {
        // Loop for going through all the characters
        // in a string to check if they are numerical
        // or not
        for(Character c : value.toCharArray())
        {
            // Condition to check is the character
            // is not numerical
            if(!Character.isDigit(c))
            {
                return false; // Character is not numerical
            }
        }
        
        return true; // Character is numerical
    }
    
    /**
     * This method validates the given time values.
     * 
     * @return True means the given time values are correct,
     *         false otherwise, of type boolean
     */
    private boolean ValidateInfo()
    {
        // Condition to check if the strings are empty
        if(TxtSeconds.getText().isEmpty() ||
           TxtMinutes.getText().isEmpty() ||
           TxtHours.getText().isEmpty())
        {
            return false; // validation failed
        }
        
        // Condition to check if the strings are
        // numerical
        if(!IsNumerical(TxtSeconds.getText()) ||
           !IsNumerical(TxtMinutes.getText()) ||
           !IsNumerical(TxtHours.getText()))
        {
            return false; // Validation failed
        }
        
        return true; // Validation succeeded
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        TxtSeconds = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        TxtMinutes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        TxtHours = new javax.swing.JTextField();
        btnSetTimer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Set Timer");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("resources/Icon.jpg")).getImage());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Seconds");

        TxtSeconds.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Minutes");

        TxtMinutes.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("Hours");

        TxtHours.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnSetTimer.setText("Set Timer");
        btnSetTimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetTimerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TxtHours, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                            .addComponent(TxtMinutes)
                            .addComponent(TxtSeconds)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnSetTimer)))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TxtSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TxtMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TxtHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSetTimer)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetTimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetTimerActionPerformed
        // Checking if the given time is valid
        if(ValidateInfo()){
            TimerCycleController.GetInstance()
                    .SetTimer(
                            Integer.parseInt(TxtSeconds.getText()), 
                            Integer.parseInt(TxtMinutes.getText()), 
                            Integer.parseInt(TxtHours.getText()));
            
            this.dispose(); // Closing this menu
        }
    }//GEN-LAST:event_btnSetTimerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimerUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TimerUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TxtHours;
    private javax.swing.JTextField TxtMinutes;
    private javax.swing.JTextField TxtSeconds;
    private javax.swing.JButton btnSetTimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
