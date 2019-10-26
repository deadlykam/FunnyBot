package com.funnybot.main;

import java.util.List;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author ShitHasian
 */
public class FunnyBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Initializing the configuration builder
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        
        // Setting up the configuration builder
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("")
                .setOAuthConsumerSecret("")
                .setOAuthAccessToken("")
                .setOAuthAccessTokenSecret("");
        
        // Initializing the twitter factory
        TwitterFactory twitterFactory = 
                new TwitterFactory(configurationBuilder.build());
        
        // Getting the twitter instance
        twitter4j.Twitter twitter = twitterFactory.getInstance();
        
        try{
            // Getting all the status from timeline
            /*List<Status> status = twitter.getHomeTimeline();
            
            // Looping to show username and tweets
            for(Status st : status)
            {
                System.out.println(st.getUser().getName() + "---" 
                                   + st.getText());
            }*/
            
            // For tweeting in tweeter
            Status statusForTweet = twitter.updateStatus("Test tweet homie");
        }
        catch(TwitterException twitterException){
            System.out.println("Error: " + twitterException.getErrorMessage());
        }
    }
    
}
