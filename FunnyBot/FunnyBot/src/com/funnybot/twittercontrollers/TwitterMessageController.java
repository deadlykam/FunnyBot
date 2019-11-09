package com.funnybot.twittercontrollers;

import com.funnybot.filecontrollers.FileController;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwitterMessageController {
    
    private static TwitterMessageController _instance; // Singleton
    
    private List<TweetInfo> _messages;
    private List<TweetInfo> _usedMessages;
    private List<String> _dataConverter;
    private String _startMessage;
    private String _endMessage;
    private Random _random;
    
    /**
     * This constructor makes sure the class can not be an
     * instance and is only instantiated from with in.
     */
    private TwitterMessageController()
    {
        _messages = new ArrayList<TweetInfo>();
        _usedMessages = new ArrayList<TweetInfo>();
        _startMessage = "";
        _endMessage = "";
        _random = new Random();
    }
    
    /**
     * This method loads all the messages.
     * 
     * @param messages The messages to load, of type List<String>
     */
    public void SetMessages(List<String> messages)
    {
        _messages = null; // Helping garbage collector
        _messages = new ArrayList<TweetInfo>(); // Instantiating a
                                                // new collector
        
        // Loop for adding all the messages except
        // start and end message
        for(int i = 0; i < messages.size() - 2; i++)
        {
            _messages.add(new TweetInfo(messages.get(i), i));
        }
        
        // Setting the start message
        _startMessage = messages.get(messages.size() - 2);
        
        // Setting the end message
        _endMessage = messages.get(messages.size() - 1);
    }
    
    /**
     * This method gets a list of all messages.
     * 
     * @param isStartEnd The flag to decide if to contain the start and end
     *                   message, True means to contain start and end message,
     *                   false otherwise, of type boolean
     * 
     * @return All messages, of type List<String>
     */
    public List<String> GetMessages(boolean isStartEnd)
    {
        ResetMessages(); // Resetting messages
        
        // Initializing the converted data
        _dataConverter = new ArrayList<String>();
        
        // Loop for adding all the message data
        for(int i = 0; i < _messages.size(); i++)
        {
            // Adding the message data
            _dataConverter.add(_messages.get(i).GetMessage());
        }
        
        // Condition to add start and end messages
        if(isStartEnd)
        {
            _dataConverter.add(_startMessage); // Adding start message
            _dataConverter.add(_endMessage);   // Adding end message
        }
        
        // Adding end of line command
        _dataConverter.add(FileController.CommandDone);
        
        return _dataConverter; // Returning all the messages
    }
    
    /**
     * This method adds a message to the list.
     * 
     * @param message The message to add, of type String
     */
    public void AddMessage(String message)
    {
        _messages.add(new TweetInfo(message, _messages.size()));
    }
    
    /**
     * This method resets all the sent messages.
     */
    public void ResetMessages()
    {
        // Loop for checking if any messages are in used
        // and adding them back to being unused
        while(_usedMessages.size() != 0)
        {
            // Adding the used message back to messages
            _messages.add(_usedMessages.get(0));
            _usedMessages.remove(0); // Removing the used message
        }
    }
    
    /**
     * This method checks if there are any tweet messages left.
     * 
     * @return True means at least 1 tweet message left, false otherwise,
     *         of type boolean
     */
    public boolean HasTweetMessage()
    {
        return _messages.size() != 0 ? true : false;
    }
    
    /**
     * This method gives the index of a new tweet to use.
     * 
     * @return The index of a new tweet to use, if there are
     *         no tweets left then the return value will be -1,
     *         of type int
     */
    public int GetTweetIndex()
    {
        return _messages.size() == 0 ? 
               -1 : _random.nextInt(_messages.size());
    }
    
    /**
     * This method gets a tweet message.
     * 
     * @param index The index of the tweet message to get, of type int
     * 
     * @return The tweet message, of type String
     */
    public String GetTweet(int index)
    {
        // Temporarily storing the tweet to send
        String tempTweet = _messages.get(index).GetMessage();
        // Adding the sent tweet to the list of sent tweets
        _usedMessages.add(_messages.get(index));
        // Removing the tweet from the available list
        _messages.remove(index);
        return tempTweet; // Returning the tweet message
    }
    
    /**
     * This method sets the start message.
     * 
     * @param message The start message to set, of type String
     */
    public void SetStartMessage(String message) { _startMessage = message; }
    
    /**
     * This method gets the start message.
     * 
     * @return The start message, of type String
     */
    public String GetStartMessage() { return _startMessage; }
    
    /**
     * This method sets the end message.
     * 
     * @param message The end message to set, of type String
     */
    public void SetEndMessage(String message) { _endMessage = message; }
    
    /**
     * This method gets the end message.
     * 
     * @return The end message, of type String
     */
    public String GetEndMessage() { return _endMessage; }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new TwitterMessageController();
    }
    
    /**
     * This method returns the singleton, if the singleton
     * is null then initializes the singleton.
     * 
     * @return The TwitterMessageController instance, of type
     *         TwitterMessageController
     */
    public static TwitterMessageController GetInstance()
    {
        // Condition to check if singleton is null
        // then initializing the singleton
        if(_instance == null) Initialize();
        
        return _instance;
    }
    
    /**
     * This class contains the tweet message information.
     */
    class TweetInfo
    {
        private String _message;
        private int _index;
        
        /**
         * This constructor creates an instance of the
         * TweetInfo.
         * 
         * @param message The message of the tweet, type of String
         * 
         * @param index The index of the message, of type int
         */
        public TweetInfo(String message, int index)
        {
            _message = message;
            _index = index;
        }
        
        /**
         * This method returns the message of the tweet.
         * 
         * @return The message of the tweet, of type String
         */
        public String GetMessage(){ return _message; }
        
        /**
         * This method returns the index of the tweet.
         * 
         * @return The index of the tweet, of type int
         */
        public int GetIndex(){ return _index; }
    }
}