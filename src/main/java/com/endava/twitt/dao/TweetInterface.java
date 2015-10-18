package com.endava.twitt.dao;

import java.util.List;

import com.endava.twitt.models.Tweets;

public interface TweetInterface {
	
	public void insertTweets(Tweets tweet);

	public List<Tweets> getTweets();
	
	public List<Tweets> getTweetsByUser(String userEmail);

}
