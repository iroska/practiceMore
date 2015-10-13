package com.endava.twitt.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.endava.twitt.models.Tweets;

@Repository
public class TweetImplement implements TweetInterface {

	private static final Logger logger = LoggerFactory
			.getLogger(TweetImplement.class);
	
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertTweets(Tweets tweet) {
		this.sessionFactory.getCurrentSession().persist(tweet);
		logger.info("Tweet saved successfully, Tweet Details=" + tweet);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tweets> getTweets() {
		Session session = this.sessionFactory.getCurrentSession();		
		List<Tweets> tweetList = session.createQuery("from tweets").list();
		for (Tweets tweet : tweetList) {
			logger.info("User List::" + tweet);
		}
		return tweetList;		
	}

}
