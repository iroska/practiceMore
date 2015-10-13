package com.endava.twitt.services;

import java.util.List;

import com.endava.twitt.models.Tweets;

public interface TweetServiceInterface {
	
	public void insertTweets(Tweets tweet);

	public List<Tweets> getTweets();

}
