package com.endava.twitt.dao;

import java.util.List;

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
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertUser(User user) {
		this.sessionFactory.getCurrentSession().persist(user);
		logger.info("Person saved successfully, Person Details=" + user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUser() {
		Session session = this.sessionFactory.getCurrentSession();
		List<User> userList = (List<User>)session.createQuery("from User").list();
		for (User user : userList) {
			logger.info("User List::" + user);
		}
		return userList;
	}

	@Override
	public void deleteUser(String userEmail) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.load(User.class, new String(userEmail));
		if (null != user) {
			session.delete(user);
		}
		logger.info("Person deleted successfully, person details=" + user);
	}

	@Override
	public User getUserByName(String name) {
		Session session = this.sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, new String (name));
		logger.info("Person loaded successfully, Person details=" + user);
		return user;
	}

	@Override
	public void updateUser(User user) {
		this.sessionFactory.getCurrentSession().update(user);
		logger.info("Person updated successfully, Person Details=" + user);
	}

}
