package com.endava.twitt.services;

import com.endava.twitt.dao.TweetInterface;
import com.endava.twitt.models.Tweets;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TweetServiceImplement implements TweetServiceInterface {

    private TweetInterface tweetDao;

    public void setTweetDao(TweetInterface tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Transactional
    public void insertTweets(Tweets tweet) {
        tweetDao.insertTweets(tweet);
    }

    @Transactional
    public List<Tweets> getTweets() {
        return tweetDao.getTweets();
    }

    @Transactional
    public List<Tweets> getTweetsByUser(String userEmail) {
        return tweetDao.getTweetsByUser(userEmail);
    }

    @Transactional
    public void updateTweet(Tweets tweet) {
        tweetDao.updateTweet(tweet);

    }

}
