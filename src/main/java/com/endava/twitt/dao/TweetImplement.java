package com.endava.twitt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;

@Repository
public class TweetImplement implements TweetInterface {

	private static final Logger logger = LoggerFactory
			.getLogger(TweetImplement.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insertTweets(Tweets tweet) {
		this.sessionFactory.getCurrentSession().persist(tweet);
		logger.info("Tweet saved successfully, Tweet Details=" + tweet);
	}

	@SuppressWarnings("unchecked")
	public List<Tweets> getTweets() {
		Session session = this.sessionFactory.getCurrentSession();		
		List<Tweets> tweetList = (List<Tweets>)session.createQuery("from Tweets").list();
		for (Tweets tweet : tweetList) {
			logger.info("All Users Tweet List:" + tweet);
		}
		return tweetList;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Tweets> getTweetsByUser(String userEmail) {
		Session session = this.sessionFactory.getCurrentSession();		
		List<Tweets> tweetList = (List<Tweets>)session.createQuery("from Tweets where Users_Email='"+userEmail+"'").list();
		for (Tweets tweet : tweetList) {
			logger.info(userEmail +" Tweet List:" + tweet);
		}
		return tweetList;		
	}    

}
