package com.funnybot.twittercontrollers;

import com.funnybot.filecontrollers.FileController;
import com.funnybot.helpers.DataLogController;
import java.util.ArrayList;
import java.util.List;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterController {
    
    private static TwitterController _instance; // Singleton
    
    private String _consumerKey;
    private String _consumerKeySecret;
    private String _accessToken;
    private String _accessTokenSecret;
    
    private ConfigurationBuilder _configurationBuilder;
    private TwitterFactory _twitterFactory;
    private Twitter _twitter;
    
    private List<String> _dataConverter;
    
    /**
     * This constructor makes sure the class can not be an
     * instance and is only instantiated from with in.
     */
    private TwitterController()
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
    private TwitterController(String consumerKey, 
            String consumerKeySecret, String accessToken,
            String accessTokenSecret)
    {
        _consumerKey = consumerKey;
        _consumerKeySecret = consumerKeySecret;
        _accessToken = accessToken;
        _accessTokenSecret = accessTokenSecret;
        
        SetupTwitter(); // Initializing twitter
    }
    
    /**
     * This method initializes the twitter api.
     */
    public void SetupTwitter()
    {
        // Checking if all credentials given and
        // initializing twitter.
        if(IsCredentialsGiven())
        {
            _configurationBuilder = null; // Removing previous
                                          // builder
                                          
            _twitterFactory = null; // Removing previous twitter
                                    // factory
                                    
            _twitter = null; // Removing previous twitter
            
            // Initializing the configuration builder
            _configurationBuilder = new ConfigurationBuilder();
            
            // Setting up the configuration builder
            _configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(_consumerKey)
                .setOAuthConsumerSecret(_consumerKeySecret)
                .setOAuthAccessToken(_accessToken)
                .setOAuthAccessTokenSecret(_accessTokenSecret);
            
            // Initializing the twitter factory
            _twitterFactory = 
                    new TwitterFactory(
                            _configurationBuilder.build());
            
            // Getting the twitter instance
            _twitter = _twitterFactory.getInstance();
            
            DataLogController.GetInstance()
                    .LogSuccess("Success: TwitterController, "
                            + "SetupTwitter(), "
                            + "Twitter initialized with "
                            + "credentials successful");
        }
        else // Condition for showing error message
            DataLogController.GetInstance()
                    .LogFailed("Error: TwitterController, "
                            + "SetupTwitter(), "
                            + "Failed to initialize twitter, "
                            + "credentials not given");
    }
    
    /**
     * This method sets the credentials for the twitter.
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
    public void SetCredentials(String consumerKey,
            String consumerKeySecret, String accessToken,
            String accessTokenSecret)
    {
        _consumerKey = consumerKey;
        _consumerKeySecret = consumerKeySecret;
        _accessToken = accessToken;
        _accessTokenSecret = accessTokenSecret;
    }
    
    /**
     * This method sets the credentials for the twitter.
     * 
     * @param data The data from which the credentials to set,
     *             of type List<String>
     */
    public void SetCredentials(List<String> data)
    {
        _consumerKey = data.get(0);
        _consumerKeySecret = data.get(1);
        _accessToken = data.get(2);
        _accessTokenSecret = data.get(3);
        
        SetupTwitter(); // Setting up twitter
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
     * This method checks if the credentials are given.
     * 
     * @return True means all the credentials give, false
     *         means at least one credential not given,
     *         of type boolean.
     */
    public boolean IsCredentialsGiven()
    {
        return !_consumerKey.isEmpty() && 
               !_consumerKeySecret.isEmpty() &&
               !_accessToken.isEmpty() &&
               !_accessTokenSecret.isEmpty();
    }
    
    /**
     * This method sends out tweet to twitter.
     * 
     * @param tweet The message to tweet to twitter,
     *              of type String
     */
    public void SendTweet(String tweet)
    {
        // Checking if all the credentials are given
        // for sending out tweet
        if(IsCredentialsGiven())
        {
            try{
                _twitter.updateStatus(tweet); // Sending tweet
                
                DataLogController.GetInstance()
                        .LogSuccess("Success: "
                                + "TwitterController, "
                                + "SendTweet(String), "
                                + "Tweet sent successful, "
                                + "Message: "
                                + tweet);
                
            }
            catch(TwitterException e){
                DataLogController.GetInstance()
                        .LogFailed("Error: TwitterController, "
                        + "TwitterException, "
                        + "SendTweet(String), "
                        + "Tweet sent failed, "
                        + "On Tweet Message: " + tweet
                        + ", Message: " + e.getMessage());
            }
        }
        else // Condition for showing error message
            DataLogController.GetInstance()
                    .LogFailed("Error: TwitterController, "
                    + "SendTweet(String), "
                    + "Failed to initialize twitter, "
                    + "credentials not given.");
    }
    
    /**
     * This method converts all the essential data 
     * into String list.
     * 
     * @return The converted data, of type List<String>
     */
    public List<String> GetData()
    {
        // Initializing the converted data
        _dataConverter = new ArrayList<String>();
        
        // Adding all the data to _dataConverter
        _dataConverter.add(_consumerKey);
        _dataConverter.add(_consumerKeySecret);
        _dataConverter.add(_accessToken);
        _dataConverter.add(_accessTokenSecret);
        _dataConverter.add(FileController.CommandDone);
        
        return _dataConverter;
    }
    
    /**
     * This method initializes the singleton.
     */
    public static void Initialize()
    { 
        _instance = new TwitterController(); 
    }
    
    /**
     * This method initializes the TwitterController with the
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
        _instance = new TwitterController(consumerKey, 
                consumerKeySecret, accessToken, 
                accessTokenSecret);
    }
    
    /**
     * This method returns the singleton, if the singleton
     * is null then initializes the singleton.
     */
    public static TwitterController GetInstance()
    {
        // Condition to check if singleton is null
        // then initializing the singleton
        if(_instance == null) Initialize();
        
        return _instance;
    }
}
