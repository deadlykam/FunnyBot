/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funnybot.main;

import com.funnybot.filecontrollers.FileController;
import com.funnybot.helpers.DataLogController;
import com.funnybot.twittercontrollers.TimerCycleController;
import com.funnybot.twittercontrollers.TwitterController;
import com.funnybot.twittercontrollers.TwitterMessageController;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class FunnyBotGUI extends javax.swing.JFrame implements Runnable{

    private boolean _isStart;
    private boolean _isCredential;
    private boolean _isTimer;
    private boolean _isTweets;
    private Thread _appThread;
    private int _counter;
    private String _tempTweet;
    private ImageIcon _iconRed;
    private ImageIcon _iconGreen;
    
    /**
     * Creates new form FunnyBotGUI
     */
    public FunnyBotGUI() {
        initComponents();
        
        _isStart = false;
        
        FileController.Initialize(); // Initializing the file 
                                     // controller
                                     
        TwitterController.Initialize(); // Initializing the
                                        // twitter controller
                                        
        DataLogController.GetInstance() // Initializing the
                .SetDebugEnabled(true); // data log controller
        
        TimerCycleController.Initialize(); // Initializing the
                                           // timer cycle
                                           // controller
                                           
        TwitterMessageController.Initialize(); // Initializing the
                                               // twitter messages
                                           
        _appThread = new Thread(this);
        _appThread.start();
        
        _counter = 0; // Setting the counter default value
        _tempTweet = ""; // Setting the default tweet message
        
        // Centering the texts in the table
        DefaultTableCellRenderer centerRenderer = 
                new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        TableInfo.setDefaultRenderer(String.class, centerRenderer);
        TableInfo.setDefaultRenderer(Integer.class, centerRenderer);
        
        // Loading the green light icon
        _iconGreen = new ImageIcon(getClass()
                .getResource("resources/Lights_Green_50x.gif"));
        
        // Loading the red light icon
        _iconRed = new ImageIcon(getClass()
                .getResource("resources/Lights_Red_50x.gif"));
        
        // Setting the red light icons
        imgCredentials.setIcon(_iconRed);
        imgTweets.setIcon(_iconRed);
        imgTimer.setIcon(_iconRed);
        imgStart.setIcon(_iconRed);
    }

    /**
     * This method returns the row addable table.
     * 
     * @return The addable table, of type DefaultTableModel
     */
    private DefaultTableModel GetTable(){
        return (DefaultTableModel)TableInfo.getModel();
    }
    
    /**
     * This method adds sent tweet to the table.
     * 
     * @param tweet The tweet message to add, of type String
     */
    private void AddTweetToTable(String tweet)
    {
        GetTable().addRow(new Object[]{
            _counter, tweet,
            DataLogController.GetInstance()
                .GetDateTime()});
        
        _counter++;
    }
    
    /**
     * This method returns the status of the bot.
     * 
     * @return The status of the bot, of type String
     */
    private String GetBotStatus()
    {
        return _isStart ? "Status: On" : "Status: Off";
    }
    
    /**
     * This method updates the bottom bar.
     */
    private void UpdateBottomBar()
    {
        // Updating the bottom bar
        LblCounter.setText(
                GetBotStatus() + " - " +
                TimerCycleController.GetInstance().toString() 
                + " - " +
                "Success: " + 
                DataLogController.GetInstance()
                        .GetSuccessCounter() + 
                " Fail: " + 
                DataLogController.GetInstance()
                        .GetFailCounter());
    }
    
    /**
     * This method checks if all the criteria has been met to
     * start the bot.
     * 
     * @return True means all criteria has been met bot is allowed to
     *         start, false otherwise, of type boolean
     */
    private boolean IsStartBot()
    {
        return IsOtherFeatures() && _isStart;
    }
    
    /**
     * This method checks if all the other features have been set up.
     * 
     * @return True means all other features has been set up, false
     *         otherwise, of type boolean
     */
    private boolean IsOtherFeatures()
    {
        return _isCredential && _isTweets && _isTimer;
    }
    
    /**
     * This method checks the status of all the major features and sets
     * the light accordingly.
     */
    private void CheckStatus()
    {
        // Condition to check if credentials are given
        if(TwitterController.GetInstance()
                .IsCredentialsGiven())
        {
            // Condition to set up credential light
            if(!_isCredential)
            {
                // Setting credential light to green
                imgCredentials.setIcon(_iconGreen);
                _isCredential = true; // Credential given and set
            }
        }
        else // Credentials not given
        {
            // Condition to set up credential light
            if(_isCredential)
            {
                // Setting credential light to red
                imgCredentials.setIcon(_iconRed);
                _isCredential = false; // Credential not given and set
            }
        }
        
        // Condition to check if tweets has been loaded
        if(TwitterMessageController.GetInstance()
                .HasTweetMessage())
        {
            // Condition to set up tweets light
            if(!_isTweets)
            {
                // Setting tweets light to green
                imgTweets.setIcon(_iconGreen);
                _isTweets = true; // Tweets given and set
            }
        }
        else // Tweets not loaded
        {
            // Condition to set up tweets light
            if(_isTweets)
            {
                // Setting tweets light to red
                imgTweets.setIcon(_iconRed);
                _isTweets = false; // Tweets given and set
            }
        }
        
        // Condition to check if timer has been set
        if(TimerCycleController.GetInstance().IsTimerSet())
        {
            // Condition to set up timer light
            if(!_isTimer)
            {
                // Setting timer light to green
                imgTimer.setIcon(_iconGreen);
                _isTimer = true; // Timer given and set
            }
        }
        else // Timer not set
        {
            // Condition to set up timer light
            if(_isTimer)
            {
                // Setting timer light to red
                imgTimer.setIcon(_iconRed);
                _isTimer = false; // Timer not given and set
            }
        }
    }
    
    @Override
    public void run() {
        while(true) // Continuous thread
        {
            // Condition for starting the bot
            if(IsStartBot())
            {
                try
                {
                    Thread.sleep(TimerCycleController
                            .GetInstance()
                            .GetTimer()); // Thread sleeping

                    // Condition for sending messages
                    if(DataLogController.GetInstance().IsSamdDay())
                    {
                        // Condition to check if messages available
                        if(TwitterMessageController.GetInstance()
                                .HasTweetMessage())
                        {
                            // Checking if twitter credentials given
                            if(TwitterController.GetInstance()
                                    .IsCredentialsGiven()){
                                // Storing the temp tweet here
                                _tempTweet = TwitterMessageController
                                        .GetInstance()
                                        .GetTweet(TwitterMessageController
                                                .GetInstance()
                                                .GetTweetIndex());

                                // Sending tweet
                                TwitterController.GetInstance()
                                        .SendTweet(_tempTweet);

                                // Adding tweet message to table
                                AddTweetToTable(_tempTweet);
                            }
                            else // Twitter Credentials not given
                            {
                                // Adding error message to table
                                AddTweetToTable("Tweeter credentails "
                                        + "not given.");
                            }
                        }
                        else // No more message available
                        {
                            // Adding error message to table
                            AddTweetToTable("Waiting for tweet "
                                    + "message reset.");
                            
                            DataLogController.GetInstance()
                            .LogFailed("FunnyBotGUI, "
                                     + "run(), "
                                     + "NoMoreMessages, "
                                     + "Message: Tried to send tweet but no "
                                     + "more tweet messages available "
                                     + "for the day. Wating for message"
                                     + " reset.");
                        }
                    }
                    else // Condition for resetting the messages
                    {
                        DataLogController.GetInstance()
                                .SetCurrentDate();
                        
                        // Resetting the messages
                        TwitterMessageController.GetInstance()
                                .ResetMessages();
                    }
                    
                    // Updating the bottom bar
                    UpdateBottomBar();
                }
                catch(InterruptedException e)
                {
                    DataLogController.GetInstance()
                            .LogFailed("FunnyBotGUI, "
                                     + "run(), "
                                     + "InterruptedException, "
                                     + "Message: " + e.getMessage());
                }
            }
            else
            {
                // Updating the bottom bar
                UpdateBottomBar();
            }
            
            CheckStatus(); // Updating the status of the lights
        }
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
        jPanel1 = new javax.swing.JPanel();
        LblCounter = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableInfo = new javax.swing.JTable();
        imgCredentials = new javax.swing.JLabel();
        imgTweets = new javax.swing.JLabel();
        imgTimer = new javax.swing.JLabel();
        imgStart = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNew = new javax.swing.JMenuItem();
        MenuOpen = new javax.swing.JMenuItem();
        MenuOpenLog = new javax.swing.JMenuItem();
        MenuSave = new javax.swing.JMenuItem();
        MenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuCredentials = new javax.swing.JMenuItem();
        MenuTweets = new javax.swing.JMenuItem();
        MenuLogPath = new javax.swing.JMenuItem();
        btnMenuSetTimer = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        btnStart = new javax.swing.JMenuItem();
        btnStop = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        LblCounter.setFont(new java.awt.Font("Dialog", 2, 12)); // NOI18N
        LblCounter.setForeground(new java.awt.Color(0, 0, 0));
        LblCounter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        LblCounter.setText("Status: Off - Success: 0 Fail: 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblCounter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LblCounter, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        TableInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Message", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableInfo);

        imgCredentials.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imgCredentials.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/funnybot/main/resources/Lights_Red_50x.gif"))); // NOI18N
        imgCredentials.setText("Credentials");

        imgTweets.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imgTweets.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/funnybot/main/resources/Lights_Red_50x.gif"))); // NOI18N
        imgTweets.setText("Tweets");

        imgTimer.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imgTimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/funnybot/main/resources/Lights_Red_50x.gif"))); // NOI18N
        imgTimer.setText("Timer");

        imgStart.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imgStart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/funnybot/main/resources/Lights_Red_50x.gif"))); // NOI18N
        imgStart.setText("Start");

        jMenu1.setText("File");

        MenuNew.setText("New");
        jMenu1.add(MenuNew);

        MenuOpen.setText("Open");
        MenuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(MenuOpen);

        MenuOpenLog.setText("Open Log");
        MenuOpenLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOpenLogActionPerformed(evt);
            }
        });
        jMenu1.add(MenuOpenLog);

        MenuSave.setText("Save");
        jMenu1.add(MenuSave);

        MenuExit.setText("Exit");
        jMenu1.add(MenuExit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        MenuCredentials.setText("Credentials");
        MenuCredentials.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCredentialsActionPerformed(evt);
            }
        });
        jMenu2.add(MenuCredentials);

        MenuTweets.setText("Tweets");
        MenuTweets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuTweetsActionPerformed(evt);
            }
        });
        jMenu2.add(MenuTweets);

        MenuLogPath.setText("Log Path");
        MenuLogPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuLogPathActionPerformed(evt);
            }
        });
        jMenu2.add(MenuLogPath);

        btnMenuSetTimer.setText("Set Timer");
        btnMenuSetTimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuSetTimerActionPerformed(evt);
            }
        });
        jMenu2.add(btnMenuSetTimer);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Bot");

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        jMenu3.add(btnStart);

        btnStop.setText("Stop");
        btnStop.setEnabled(false);
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });
        jMenu3.add(btnStop);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(imgCredentials)
                        .addGap(54, 54, 54)
                        .addComponent(imgTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(imgTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(imgStart, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgStart, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgTweets, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgCredentials, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpenActionPerformed
        // Setting the mode of the file chooser to files only
        FileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
        int status = FileChooser.showOpenDialog(this); // Getting status of
                                                       // the file chooser
                                                       
        // Condition to check if file chooser selected a file
        if(status == JFileChooser.APPROVE_OPTION)
        {
            FileController.GetInstance().LoadData(FileChooser.getSelectedFile()
                .toPath().toString());

            // Checking if data has been loaded
            if(FileController.GetInstance().IsDataTemp()){
                
                // Setting up twitter credentials
                TwitterController.GetInstance().SetCredentials(
                        FileController.GetInstance().GetData(0), 
                        FileController.GetInstance().GetData(1), 
                        FileController.GetInstance().GetData(2),
                        FileController.GetInstance().GetData(3));
                
                // Initializing twitter with new credentials
                TwitterController.GetInstance().SetupTwitter();
            }
        }
    }//GEN-LAST:event_MenuOpenActionPerformed

    private void MenuCredentialsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCredentialsActionPerformed
        new CredentialUI().setVisible(true);
    }//GEN-LAST:event_MenuCredentialsActionPerformed

    private void MenuLogPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuLogPathActionPerformed
        // Setting the mode of the file chooser to directories only
        FileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        int status = FileChooser.showOpenDialog(this); // Getting status of
                                                       // the file chooser

        // Condition to check if file chooser selected a file
        if(status == JFileChooser.APPROVE_OPTION)
        {
            // Setting the path of the data logger
            DataLogController.GetInstance().SetPath(
                    FileChooser.getSelectedFile()
                    .toPath().toString());
        }
    }//GEN-LAST:event_MenuLogPathActionPerformed

    private void btnMenuSetTimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuSetTimerActionPerformed
        new TimerUI().setVisible(true);
    }//GEN-LAST:event_btnMenuSetTimerActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if(IsOtherFeatures()){
            _isStart = true; // Starting the bot
            btnStart.setEnabled(false);
            btnStop.setEnabled(true);

            imgStart.setIcon(_iconGreen); // Setting the light to green
            
            DataLogController.GetInstance()
                    .LogSuccess("Success: FunnyBotGUI, "
                            + "btnStartActionPerformed(ActionEvent), "
                            + "Successfully started the bot.");

            // Updating the bottom bar
            UpdateBottomBar();

            // Setting the current date
            DataLogController.GetInstance().SetCurrentDate();

            // Sending the starting tweet
            TwitterController.GetInstance()
                    .SendTweet(TwitterMessageController.GetInstance()
                            .GetStartMessage());

            // Adding tweet message to table
            AddTweetToTable(TwitterMessageController.GetInstance()
                            .GetStartMessage());
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        _isStart = false; // Stopping the bot
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);
        
        imgStart.setIcon(_iconRed); // Setting the light to red
        
        // Condition for stopping the thread
        if(!_appThread.isInterrupted()) _appThread.interrupt();
        
        // Sending the ending tweet
        TwitterController.GetInstance()
                .SendTweet(TwitterMessageController.GetInstance()
                        .GetEndMessage());
        
        // Adding tweet message to table
        AddTweetToTable(TwitterMessageController.GetInstance()
                        .GetEndMessage());
        
        DataLogController.GetInstance()
                .LogSuccess("Success: FunnyBotGUI, "
                        + "btnStopActionPerformed(ActionEvent), "
                        + "Successfully stopped the bot.");
    }//GEN-LAST:event_btnStopActionPerformed

    private void MenuTweetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuTweetsActionPerformed
        new MessagesUI().setVisible(true);
    }//GEN-LAST:event_MenuTweetsActionPerformed

    private void MenuOpenLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOpenLogActionPerformed
        DataLogController.GetInstance().OpenLogPath();
    }//GEN-LAST:event_MenuOpenLogActionPerformed

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
            java.util.logging.Logger.getLogger(FunnyBotGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FunnyBotGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FunnyBotGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FunnyBotGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FunnyBotGUI app = new FunnyBotGUI();
                app.setVisible(true);
                
                /*Thread appThread = new Thread(app);
                appThread.start();*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser FileChooser;
    private javax.swing.JLabel LblCounter;
    private javax.swing.JMenuItem MenuCredentials;
    private javax.swing.JMenuItem MenuExit;
    private javax.swing.JMenuItem MenuLogPath;
    private javax.swing.JMenuItem MenuNew;
    private javax.swing.JMenuItem MenuOpen;
    private javax.swing.JMenuItem MenuOpenLog;
    private javax.swing.JMenuItem MenuSave;
    private javax.swing.JMenuItem MenuTweets;
    private javax.swing.JTable TableInfo;
    private javax.swing.JMenuItem btnMenuSetTimer;
    private javax.swing.JMenuItem btnStart;
    private javax.swing.JMenuItem btnStop;
    private javax.swing.JLabel imgCredentials;
    private javax.swing.JLabel imgStart;
    private javax.swing.JLabel imgTimer;
    private javax.swing.JLabel imgTweets;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
