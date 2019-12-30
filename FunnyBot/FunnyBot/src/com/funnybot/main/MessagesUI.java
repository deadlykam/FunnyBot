/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funnybot.main;

import com.funnybot.filecontrollers.FileController;
import com.funnybot.twittercontrollers.TwitterMessageController;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kamran
 */
public class MessagesUI extends javax.swing.JFrame implements Runnable {

    private final int _tweetLimit = 280;
    private int _counter = 0;
    private boolean _isAlive;
    
    /**
     * Creates new form MessagesUI
     */
    public MessagesUI() {
        initComponents();
        
        // Centering the texts in the table
        DefaultTableCellRenderer centerRenderer = 
                new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableTweets.setDefaultRenderer(String.class, centerRenderer);
        TableTweets.setDefaultRenderer(Integer.class, centerRenderer);
        
        // Setting the start message
        txtStartTweet.setText(TwitterMessageController
                .GetInstance().GetStartMessage());
            
        // Setting the end message
        txtEndTweet.setText(TwitterMessageController
                .GetInstance().GetEndMessage());
        
        LoadMessagesToTable();
        
        _isAlive = true; // Keeping the thread alive
        
        // Starting the thread
        new Thread(this).start();
    }

    /**
     * This method checks if the tweet is within the tweet limit
     * 
     * @return True means within tweet limit, false otherwise,
     *         of type boolean
     */
    private boolean IsWithInLimit()
    {
        return TextAreaTweet.getText().length() <= _tweetLimit;
    }
    
    /**
     * This method updates the current tweet length counter.
     */
    private void UpdateTweetLengthCounter()
    {
        txtTweetLengthCounter.setText(TextAreaTweet.getText()
                                      .length() 
                                      + "/"
                                      + _tweetLimit);
        
        // Condition to check if the tweet length is more
        // than the tweet limit
        if(!IsWithInLimit())
        {
            // Checking if the text colour is not turn to red
            if(TextAreaTweet.getForeground() != Color.RED)
            {
                // Turning text colour to red
                TextAreaTweet.setForeground(Color.RED);
            }
        }
        else // Tweet length with in limit
        {
            // Chcking if the text colour is not turn to black
            if(TextAreaTweet.getForeground() != Color.BLACK)
            {
                // Turning text colour to black
                TextAreaTweet.setForeground(Color.BLACK);
            }
        }
        
        
    }
    
    /**
     * This method checks if the start,end and at least 1
     * tweet given.
     * 
     * @return True means all the necessary tweets are given,
     *         false otherwise, of type boolean
     */
    private boolean ValidateInfo()
    {
        // Condition to check if the validation failed
        if(txtStartTweet.getText().isEmpty() ||
           txtEndTweet.getText().isEmpty() ||
           !TwitterMessageController.GetInstance()
                   .HasTweetMessage())
        {
            return false; // Validation failed
        }
        
        return true; // Validation succeeded
    }
    
    /**
     * This method loads all the tweet messages into the table.
     */
    private void LoadMessagesToTable()
    {
        _counter = 0; // Resetting the counter
        
        // Loop to remove any row data if present
        while(GetTable().getRowCount() != 0)
        {
            // Removing row data
            GetTable().removeRow(0);
        }
        
        // Getting the message list
        List<String> data = TwitterMessageController.GetInstance()
                .GetData(false);

        // Loop for adding all the tweet messages
        // to the table
        for(int i = 0; i < data.size(); i++)
        {
            // Checking if message isn't end of file
            if(!data.get(i)
                    .equalsIgnoreCase(FileController.CommandDone)){
                // Adding the tweet message
                GetTable().addRow(new Object[]{_counter, data.get(i)});
                _counter++;
            }
        }
    }
    
    /**
     * This method returns the row addable table.
     * 
     * @return The addable table, of type DefaultTableModel
     */
    private DefaultTableModel GetTable(){
        return (DefaultTableModel)TableTweets.getModel();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        FileChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaTweet = new javax.swing.JTextArea();
        btnAddTweet = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableTweets = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtStartTweet = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtEndTweet = new javax.swing.JTextField();
        btnSet = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        txtTweetLengthCounter = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();

        FileChooser.setSelectedFile(new java.io.File("C:\\Program Files\\NetBeans 8.2\\.txt"));

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Tweets");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("resources/Icon.jpg")).getImage());

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Enter Tweet");

        TextAreaTweet.setColumns(20);
        TextAreaTweet.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        TextAreaTweet.setLineWrap(true);
        TextAreaTweet.setRows(5);
        jScrollPane1.setViewportView(TextAreaTweet);

        btnAddTweet.setText("Add Tweet");
        btnAddTweet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTweetActionPerformed(evt);
            }
        });

        TableTweets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Message"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TableTweets);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel2.setText("Start Tweet");

        txtStartTweet.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setText("End Tweet");

        txtEndTweet.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        btnSet.setText("Set");
        btnSet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        txtTweetLengthCounter.setEditable(false);
        txtTweetLengthCounter.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        txtTweetLengthCounter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTweetLengthCounter.setText("0/280");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTweetLengthCounter, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTweetLengthCounter)
        );

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddTweet)
                                .addGap(190, 190, 190)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnSet, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane2)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEndTweet)
                                        .addComponent(txtStartTweet)))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtStartTweet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEndTweet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddTweet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSet)
                    .addComponent(btnCancel))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddTweetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTweetActionPerformed
        // Condition to check if the tweet is within limit
        if(IsWithInLimit()){
            TwitterMessageController.GetInstance()
                    .AddMessage(TextAreaTweet.getText());

            // Adding the tweet message
            GetTable().addRow(new Object[]{_counter, TextAreaTweet.getText()});
            _counter++;
            TextAreaTweet.setText(""); // Removing all texts once added
        }
    }//GEN-LAST:event_btnAddTweetActionPerformed

    private void btnSetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetActionPerformed
        // Checking if the tweets given are valid
        if(ValidateInfo()){
            // Saving the start tweet
            TwitterMessageController.GetInstance()
                    .SetStartMessage(txtStartTweet.getText());

            // Saving the end tweet
            TwitterMessageController.GetInstance()
                    .SetEndMessage(txtEndTweet.getText());
            
            _isAlive = false; // Killing the thread
            
            this.dispose(); // Closing this menu
        }
    }//GEN-LAST:event_btnSetActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        _isAlive = false; // Killing the thread
        this.dispose(); // Closing this menu
    }//GEN-LAST:event_btnCancelActionPerformed

    @Override
    public void run() 
    {
        // Loop running the thread
        while(_isAlive)
        {
            UpdateTweetLengthCounter();
        }
    }
    
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
            java.util.logging.Logger.getLogger(MessagesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MessagesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MessagesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MessagesUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MessagesUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JTable TableTweets;
    private javax.swing.JTextArea TextAreaTweet;
    private javax.swing.JButton btnAddTweet;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnSet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtEndTweet;
    private javax.swing.JTextField txtStartTweet;
    private javax.swing.JTextField txtTweetLengthCounter;
    // End of variables declaration//GEN-END:variables

}
