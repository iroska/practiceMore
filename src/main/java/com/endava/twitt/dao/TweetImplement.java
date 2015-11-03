package com.endava.twitt.dao;

import java.util.List;

import org.hibernate.HibernateException;
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

	public void insertTweets(Tweets tweet) {
		try {
			this.sessionFactory.getCurrentSession().persist(tweet);
		} catch (HibernateException e) {
			logger.error("Tweet wasn't saved." + e);
		}
		logger.info("Tweet saved successfully, Tweet Details=" + tweet);
	}

	@SuppressWarnings("unchecked")
	public List<Tweets> getTweets() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Tweets> tweetList = (List<Tweets>) session.createQuery(
					"from Tweets").list();
			for (Tweets tweet : tweetList) {
				logger.info("Users Tweets Listing:" + tweet);
			}
			return tweetList;
		} catch (HibernateException e) {
			logger.error("Couldn't list tweets: " + e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Tweets> getTweetsByUser(String userEmail) {
		/*try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Tweets> tweetList = (List<Tweets>) session.createQuery(
					"from Tweets where Users_Email='" + userEmail + "'").list();
			for (Tweets tweet : tweetList) {
				logger.debug(userEmail + " Tweet List:" + tweet);
			}
			return tweetList;
		} catch (HibernateException e) {
			logger.error("Couldn't list user's tweets: " + e);
		}*/
		return null;
	}

	public void updateTweet(Tweets tweet) {
		try {
			this.sessionFactory.getCurrentSession().update(tweet);
		} catch (HibernateException e) {
			logger.error("Couldn't update tweets: " +tweet+" "+ e);
		}
		logger.debug("Tweet updated successfully, Tweet Details="
				+ tweet.getDescription());
	}

	@Override
	public void deleteUser(Tweets tweet) {
		try{
			
			if(null!=tweet){
			logger.error("Try to delete tweet: " +tweet);
			this.sessionFactory.getCurrentSession().delete(tweet);
			logger.debug("Tweet deleted successfully, Tweet Details="
					+ tweet.getDescription());
		}		
		}catch (HibernateException e) {
			logger.error("Couldn't delete tweet: " +tweet+" "+ e);
		}
		
	}

}
