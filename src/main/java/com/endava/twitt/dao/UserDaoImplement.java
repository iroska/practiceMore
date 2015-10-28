package com.endava.twitt.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.endava.twitt.models.User;

@Repository
public class UserDaoImplement implements UserDaoInterface {

	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoImplement.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		// logger.info("Try to connect to database.");
		this.sessionFactory = sessionFactory;

		/*
		 * if (this.sessionFactory.openSession() == null) {
		 * logger.info("Cnnection to database failed."); } else {
		 * logger.info("Succesfull connection to database done."); }
		 */
	}

	public void insertUser(User user) {
		try {
			this.sessionFactory.getCurrentSession().persist(user);
		} catch (HibernateException e) {
			logger.error("Person wasn't saved." + e);
		}
		logger.debug("Person saved successfully, Person Details="
				+ user.getEmail());
	}

	@SuppressWarnings("unchecked")
	public List<User> getUser() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from User");
			List<User> userList = (List<User>) query.list();
			for (User user : userList) {
				logger.debug("User List:" + user.getEmail());
			}
			return userList;
		} catch (HibernateException e) {
			logger.error("Couldn't list users: " + e);
		}

		return null;

	}

	public void deleteUser(String userEmail) {

		try {
			User user = getUserByName(userEmail);
			if (null != user) {
				sessionFactory.getCurrentSession().delete(user);
			}
			logger.debug("Person deleted successfully, person details="
					+ user.getEmail());
		} catch (HibernateException e) {
			logger.error("Couldn't deleted user." + e);
		}

	}

	public User getUserByName(String name) {

		try {
			Session session = this.sessionFactory.getCurrentSession();
			User user = (User) session.get(User.class, new String(name));
			logger.debug("Person loaded successfully, Person details="
					+ user.getEmail());
			return user;
		} catch (HibernateException e) {
			logger.error("Person wasn't loaded with provided name =" + name);
			return null;
		}
	}

	public void updateUser(User user) {
		try{
		this.sessionFactory.getCurrentSession().update(user);
		logger.debug("Person updated successfully, Person Details="
				+ user.getEmail());
		}catch (HibernateException e) {
			logger.error("Couldn't update user." + e);
		}		
	}

	public User loginUser(String userEmail, String password) {
		
		try{
		User user = this.getUserByName(userEmail);
		if ((user != null) && user.getPassword().equals(password)) {
			logger.debug("Person's credentials was verified successfully, Person details="
					+ user.getEmail());
			return user;
		}
		}catch (HibernateException e){
			logger.error("Login credentials validation filed with person's email=" + userEmail);
		}
		
		
		return null;
	}

}
