package com.endava.twitt.services;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by ibalanici on 20/10/2015.
 */
public class TweetServiceTest {
    private ClassPathXmlApplicationContext applicationContext;
    private TweetServiceInterface tweetService;
    private UserServicesInterface userService;
    private Tweets tweet;
    private User user;

    @Before
    public void setup() {
        applicationContext = new ClassPathXmlApplicationContext("/spring/appServlet/servlet-context-test.xml");       
    }

    @Test
    public void insertTweetsTest() {
        tweetService = (TweetServiceInterface) applicationContext.getBean("tweetService");
        assertNotNull(tweetService);
        userService = (UserServicesInterface) applicationContext.getBean("userService");
        assertNotNull(userService);
        user = userService.getUserByName("testInsertTweet@testTweet.com");
        tweet = (Tweets) applicationContext.getBean("testTweet");
        tweet.setUser(user);

        tweetService.insertTweets(tweet);
        assertTrue(tweet.getId() > 0);
    }

    @Test
    public void getAllTweetsTest() {
        tweetService = (TweetServiceInterface) applicationContext.getBean("tweetService");
        assertNotNull(tweetService);
        List<Tweets> tweetsList = tweetService.getTweets();
        assertNotNull(tweetsList);
        assertTrue(tweetsList.size() >= 0);
    }    
  
    @Test
    public void updateTweetTest() {
    	tweetService = (TweetServiceInterface) applicationContext.getBean("tweetService");
        assertNotNull(tweetService);
        userService = (UserServicesInterface) applicationContext.getBean("userService");
        assertNotNull(userService);
        user = userService.getUserByName("testInsertTweet@testTweet.com");
        
        String oldTweet = null;
        List <Tweets> lisetOfUserTweets=( List <Tweets>)user.getTweet();		 
	        for (Tweets tweet:lisetOfUserTweets){
	        	if(tweet.getId()==465){
	        		oldTweet=tweet.getDescription();	
	        	}
	        }	
                
        String updatedTweet="new descriptiont  3";
		Tweets tweets = new Tweets();		
		tweets.setId(465);
		tweets.setUser(user);
		tweets.setDescription(updatedTweet);
		tweets.setPublishedDate(new Date());
		tweetService.updateTweet(tweets);
		
		
		/*List <Tweets> lisetOfUserTweets1=( List <Tweets>)user.getTweet();	*/
		boolean testUapdate=true;
	        for (Tweets tweet:lisetOfUserTweets){
	        	if(tweet.getId()==465){
	        		testUapdate=updatedTweet.equals(tweet.getDescription());
	        		System.out.println(testUapdate);
	        		      		
	        	}
	        }		
	        assertTrue(testUapdate==false); 	      
    }
    
    @Test
    public void deleteUserTweetTest() {
    	tweetService = (TweetServiceInterface) applicationContext.getBean("tweetService");
        assertNotNull(tweetService);
        userService = (UserServicesInterface) applicationContext.getBean("userService");
        assertNotNull(userService);
        user = userService.getUserByName("testInsertTweet@testTweet.com");
        
		Tweets tweets = new Tweets();		
		tweets.setId(467);
		tweetService.deleteUser(tweets);
		
		List <Tweets> lisetOfUserTweets=( List <Tweets>)user.getTweet();
        boolean test=true;
        for (Tweets tweet:lisetOfUserTweets){
        	if(tweet.getId()==467){
        		test=false;        		
        	}
        }		
		assertTrue(test==false);		
    }
}
