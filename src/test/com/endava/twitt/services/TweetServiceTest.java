package com.endava.twitt.services;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;
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
        user = userService.getUserByName("igor.balanici@gmail.com");
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
        assertTrue(tweetsList.size() > 0);
    }


}
