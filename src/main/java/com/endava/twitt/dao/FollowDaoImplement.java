package com.endava.twitt.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.endava.twitt.models.Follow;

@Repository
public class FollowDaoImplement implements FollowDaoInterface  {
	
	private static final Logger logger = LoggerFactory
			.getLogger(FollowDaoImplement.class);
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		logger.info("Try to connect to database FollowDaoImplement class.");
		this.sessionFactory = sessionFactory;
		
		if(this.sessionFactory==null){
			logger.info("Cnnection to database failed. FollowDaoImplement class.");
		}else{
			logger.info("Succesfull connection to database done in FollowDaoImplement class.");
		}
	}

	@Override
	public void insertFollow(Follow follow) {
		this.sessionFactory.getCurrentSession().persist(follow);
		
	}

	@SuppressWarnings("unchecked")
	public Set<Follow> getFollows() {
		Session session = this.sessionFactory.getCurrentSession();		
		Set<Follow> followList = (Set<Follow>)session.createQuery("from Follow").list();
		for (Follow follow : followList) {
			logger.info("All Users Follow List:" + follow);
		}
		return followList;
	}

	@SuppressWarnings("unchecked")
	public Set<Follow> getFollowByUser(String userEmail) {
		Session session = this.sessionFactory.getCurrentSession();		
		Set<Follow> followList = (Set<Follow>)session.createQuery("from Follow where Users_Email='"+userEmail+"'").list();
		for (Follow follow : followList) {
			logger.info(userEmail +" Follow List:" + follow);
		}
		return followList;
	}

	@Override
	public void deleteUserFollow(Follow follow) {
		 this.sessionFactory.getCurrentSession().delete(follow);
	        logger.info("Followed user deleted successfully, Follow Details=" + follow.getUserFollowed());	 
		
	}
	
	

}