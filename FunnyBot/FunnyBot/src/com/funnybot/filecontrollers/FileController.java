package com.funnybot.filecontrollers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileController {
    
    private static FileController _instance; // Singleton
    
    public static String CommandDone = "-done-";
    
    private String _consumerKey;
    private String _consumerKeySecret;
    private String _accessToken;
    private String _accessTokenSecret;
    
    private List<String> _dataTemp;
    
    /**
     * This class can not be an instant.
     */
    private FileController()
    {
        _consumerKey = "";
        _consumerKeySecret = "";
        _accessToken = "";
        _accessTokenSecret = "";
    }
    
    /**
     * This constructor can not be an instant, only
     * initiating from with in with the credentials
     * 
     * @param consumerKey The consumer key for the twitter,
     *                    of type String
     * 
     * @param consumerKeySecret The consumer key secret for
     *                          the twitter of type String
     * 
     * @param accessToken The access token for the twitter,
     *                    of type String
     * 
     * @param accessTokenSecret The access token secret for
     *                          the twitter, of type String
     */
    private FileController(String consumerKey, 
            String consumerKeySecret, String accessToken,
            String accessTokenSecret)
    {
        _consumerKey = consumerKey;
        _consumerKeySecret = consumerKeySecret;
        _accessToken = accessToken;
        _accessTokenSecret = accessTokenSecret;
    }
    
    /**
     * This method saves the data by over writing the old
     * data on the given path.
     * 
     * @param path The path of the save location including
     *             the file name and extension, of type
     *             String
     * 
     * @param data The data to be store, of type String
     */
    public void SaveFileOverWrite(String path, String data)
    {
        try
        {
            // Opening a writer
            PrintWriter writer = 
                    new PrintWriter(path, "UTF-8");
            
            writer.print(data); // Storing the data
            writer.close(); // Closing the writer
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: FileNotFound - " +
                    "FileController - " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("Error: IO - FileController"
                    + " " + e.getMessage());
        }
    }
    
    /**
     * This method saves the data by appending the old
     * data on the given path.
     * 
     * @param path The path of the save location including
     *             the file name and extension, of type
     *             String
     * 
     * @param data The data to be store, of type String
     */
    public void SaveFileAppend(String path, String data)
    {
        try
        {
            // Opening a writer
            PrintWriter writer = 
                new PrintWriter(
                new BufferedWriter(
                new FileWriter(
                    path, true
                )));
            
            writer.print(data); // Storing the data
            writer.close(); // Closing the writer
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: FileNotFound - " +
                    "FileController - " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("Error: IO - FileController"
                    + " " + e.getMessage());
        }
    }
    
    public void LoadData(String path)
    {
        try
        {
            _dataTemp = null; // Removing and clearing previous
                              // data
            
            _dataTemp = new ArrayList<>();
            
            // Loading the file
            File file = new File(path);
            
            // Setting the file to read
            BufferedReader bufferReader = 
                    new BufferedReader(new FileReader(file));
            
            String data = ""; // For storing the read data
            
            // Loop for getting all the data from file
            while(!(data = bufferReader.readLine())
                    .equals(CommandDone))
            {
                _dataTemp.add(data); // Storing the data
                                     // temporarily
            }
            
            _consumerKey = _dataTemp.get(0); // Storing consumer 
                                             // key
                                             
            _consumerKeySecret = _dataTemp.get(1); // Storing
                                                   // consumer
                                                   // secret key
                                                   
            _accessToken = _dataTemp.get(2); // Storing access token
                                             // key
            
            _accessTokenSecret = _dataTemp.get(3); // Storing access
                                                   // token secret
                                                   // key
                                                   
            _dataTemp = null; // Removing and clearing data
        }
        catch(FileNotFoundException e)
        {}
        catch(IOException e)
        {}
        
        
    }
    
    /**
     * This method returns the value of the consumer key.
     * 
     * @return The consumer key value, of type String
     */
    public String GetConsumerKey(){ return _consumerKey; }
    
    /**
     * This method returns the value of the consumer key secret.
     * 
     * @return The consumer key secret value, of type String
     */
    public String GetConsumerKeySecret()
    { return _consumerKeySecret; }
    
    /**
     * This method returns the value of the access token.
     * 
     * @return The access token value, of type String
     */
    public String GetAccessToken(){ return _accessToken; }
    
    /**
     * This method returns the value of the access token secret.
     * 
     * @return The access token secret value, of type String
     */
    public String GetAccessTokenSecret()
    { return _accessTokenSecret; }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new FileController(); 
    }
    
    /**
     * This method initializes the FileController with the
     * credentials.
     * 
     * @param consumerKey The consumer key for the twitter,
     *                    of type String
     * 
     * @param consumerKeySecret The consumer key secret for
     *                          the twitter of type String
     * 
     * @param accessToken The access token for the twitter,
     *                    of type String
     * 
     * @param accessTokenSecret The access token secret for
     *                          the twitter, of type String
     */
    public static void Initialize(String consumerKey, 
            String consumerKeySecret, String accessToken,
            String accessTokenSecret)
    {
        _instance = new FileController(consumerKey, 
                consumerKeySecret, accessToken, 
                accessTokenSecret);
    }
    
    /**
     * This method returns the singleton, if the singleton
     * is null then initializes the singleton.
     */
    public static FileController GetInstance()
    {
        // Condition to check if singleton is null
        // then initializing the singleton
        if(_instance == null) Initialize();
        
        return _instance;
    }
}
