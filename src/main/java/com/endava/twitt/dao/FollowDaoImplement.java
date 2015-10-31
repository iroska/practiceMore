package com.endava.twitt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.endava.twitt.models.Follow;

@Repository
public class FollowDaoImplement implements FollowDaoInterface {

	private static final Logger logger = LoggerFactory
			.getLogger(FollowDaoImplement.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {		
		this.sessionFactory = sessionFactory;		
	}

	@Override
	public void insertFollow(Follow follow) {
		try {
			this.sessionFactory.getCurrentSession().persist(follow);
		} catch (HibernateException e) {
			logger.error("Follow wasn't saved." + e);
		}
		logger.info("Follow saved successfully, Follow Details=" + follow);

	}

	@SuppressWarnings("unchecked")
	public List<Follow> getFollows() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Follow> followList = (List<Follow>) session.createQuery(
					"from Follow").list();
			for (Follow follow : followList) {
				logger.info("Listing Users from Follow List:" + follow);
			}
			return followList;
		} catch (HibernateException e) {
			logger.error("Couldn't list follows: " + e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Follow> getFollowByUser(String userEmail) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Follow> followList = (List<Follow>) session.createQuery(
					"from Follow where User_Follow='" + userEmail + "'").list();
			for (Follow follow : followList) {
				logger.info(userEmail + " Follow List:" + follow);
			}
			return followList;
		} catch (HibernateException e) {
			logger.error("Couldn't list user's follows: " + e);
		}
		return null;
	}

	@Override
	public void deleteUserFollow(Follow follow) {
		try {
			logger.info("Try to delete one user's follow");
			this.sessionFactory.getCurrentSession().delete(follow);
		} catch (HibernateException e) {
			logger.error("Couldn't delete one follow: " + follow + " " + e);
		}
		logger.info("Followed user deleted successfully, Follow Details="
				+ follow.getUserFollowed());
	}

	public void deleteAllUserFollow(String user_email) {
		try {
			logger.info("Try to delete ALL user's follow");
			Session session = this.sessionFactory.getCurrentSession();
			String stringQuery = "DELETE FROM Follow where User_Follow='"
					+ user_email + "'";
			Query query = session.createQuery(stringQuery);
			query.executeUpdate();
		} catch (HibernateException e) {
			logger.error("Couldn't delete all follow." + e);
		}
		logger.info("All followed users by user was deleted successfully.");
	}

}
