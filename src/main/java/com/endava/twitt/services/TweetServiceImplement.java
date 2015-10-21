package com.endava.twitt.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.twitt.dao.TweetInterface;
import com.endava.twitt.models.Tweets;

@Service
public class TweetServiceImplement implements TweetServiceInterface {
	
	private TweetInterface tweetDao;

	public void setTweetDao(TweetInterface tweetDao) {
		this.tweetDao = tweetDao;
	}

	@Override
	@Transactional
	public void insertTweets(Tweets tweet) {
		tweetDao.insertTweets(tweet);
	}

	@Override
	@Transactional
	public List<Tweets> getTweets() {		
		return tweetDao.getTweets();
	}

	@Override
	@Transactional
	public List<Tweets> getTweetsByUser(String userEmail) {
		return tweetDao.getTweetsByUser(userEmail);
	}

	@Override
	@Transactional
	public void updateTweet(Tweets tweet) {
		tweetDao.updateTweet(tweet);
		
	}

	@Override
	@Transactional
	public void deleteUser(Tweets tweet) {
		tweetDao.deleteUser(tweet);		
	}	

}
