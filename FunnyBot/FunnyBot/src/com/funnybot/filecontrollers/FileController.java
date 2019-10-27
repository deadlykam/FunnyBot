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
    
    private List<String> _data;
    
    /**
     * This class can not be an instant.
     */
    private FileController(){}
    
    
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
    
    /**
     * This method loads the data from the given path.
     * 
     * @param path The path to the file location, of type String
     */
    public void LoadData(String path)
    {
        try
        {
            _data = null; // Removing and clearing previous
                              // data
            
            _data = new ArrayList<>();
            
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
                _data.add(data); // Storing the data
                                     // temporarily
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error: FileNotFoundException - " + 
                    "FileController - " + e.getMessage());
        }
        catch(IOException e)
        {
            System.out.println("Error: IOException - " + 
                    "FileController - " + e.getMessage());
        }
    }
    
    /**
     * This method returns the indexth data.
     * 
     * @return The indexth data, of type String
     */
    public String GetData(int index){ return _data.get(index); }
    
    /**
     * This method checks if there are any data available.
     * 
     * @return True means data is available, false otherwise,
     *         of tyep boolean
     */
    public boolean IsDataTemp(){ return _data != null; }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new FileController(); 
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
