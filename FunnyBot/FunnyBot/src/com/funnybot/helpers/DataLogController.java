package com.funnybot.helpers;

import com.funnybot.filecontrollers.FileController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataLogController {
    
    private static DataLogController _instance;
    
    private final String _logSuccessFileName 
            = "\\Success.txt";
    
    private final String _logFailFileName 
            = "\\Fail.txt";
    
    private DateTimeFormatter _dateTimeFormatter;
    private LocalDateTime _localDateTime;
    private String _dateFormat = "dd/MM/yyyy HH:mm:ss";
    private String _logPath;
    private boolean _isDebugMode;
    private int _successCallCounter;
    private int _failedCallCounter;
    
    /**
     * This constructor makes sure the class can not be an
     * instance and is only instantiated from with in.
     */
    private DataLogController()
    {
        _dateTimeFormatter = DateTimeFormatter.ofPattern(_dateFormat);
        _localDateTime = LocalDateTime.now();
        _logPath = System.getProperty("user.home");
        _isDebugMode = false;
        
        _successCallCounter = 0;
        _failedCallCounter = 0;
    }
    
    /**
     * This method returns the current local time with format.
     * 
     * @return The current local time with format example
     *         " - 27/10/2019 16:03:03", of type String
     */
    private String GetDateTime()
    {
        _localDateTime = null; // Helping garbage
        _localDateTime = LocalDateTime.now(); // Getting a new
                                              // instance with
                                              // updated time
        
        return " - " + _dateTimeFormatter.format(_localDateTime)
                .toString();
    }
    
    /**
     * This method store logs into a success text.
     * 
     * @param log The success log to store, of type String
     */
    public void LogSuccess(String log)
    {
        _successCallCounter++; // Incrementing the success counter
        
        FileController.GetInstance()
                .SaveFileAppend(
                    _logPath + _logSuccessFileName,
                    log + GetDateTime());
        
        // Checking if debug mode is on
        if(_isDebugMode)
            System.out.println(log + GetDateTime());
    }
    
    /**
     * This method store logs into a failed text.
     * 
     * @param log The failed log to store, of type String
     */
    public void LogFailed(String log)
    {
        _failedCallCounter++; // Incrementing the failed counter
        
        FileController.GetInstance()
                .SaveFileAppend(
                    _logPath + _logFailFileName,
                    log + GetDateTime());
        
        // Checking if debug mode is on
        if(_isDebugMode)
            System.out.println(log + GetDateTime());
    }
    
    /**
     * This method sets the path of the data logs.
     * 
     * @param path The new path of the data log,
     *             of type String
     */
    public void SetPath(String path){ _logPath = path; }
    
    /**
     * This method sets the debug mode of the
     * DataLogController.
     */
    public void SetDebugEnabled(boolean isEnabled)
    {
        _isDebugMode = isEnabled;
    }
    
    /**
     * This method gets the success call counter.
     * 
     * @return The success call counter, of type int
     */
    public int GetSuccessCounter(){ return _successCallCounter; }
    
    /**
     * This method gets the fail call counter.
     * 
     * @return The fail call counter, of type int
     */
    public int GetFailCounter(){ return _failedCallCounter; }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new DataLogController(); 
    }
    
    /**
     * This method returns the singleton, if the singleton
     * is null then initializes the singleton.
     */
    public static DataLogController GetInstance()
    {
        // Condition to check if singleton is null
        // then initializing the singleton
        if(_instance == null) Initialize();
        
        return _instance;
    }
}
