package com.funnybot.twittercontrollers;

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
        }
        else // Condition for showing error message
            System.out.println("Error: TwitterController - "
                    + "Failed to initialize twitter, "
                    + "credentials not given.");
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
            }
            catch(TwitterException e){
                System.out.println("Error: TwitterException - "
                        + "TwitterController - "
                        + e.getMessage());
            }
        }
        else // Condition for showing error message
            System.out.println("Error: TwitterController - "
                    + "Failed to initialize twitter, "
                    + "credentials not given.");
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
