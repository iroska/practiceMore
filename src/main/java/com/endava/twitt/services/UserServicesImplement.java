package com.endava.twitt.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.twitt.dao.UserDaoInterface;
import com.endava.twitt.models.User;

@Service
public class UserServicesImplement implements UserServicesInterface {
	
	private UserDaoInterface userDao;
	
	public void setUserDao(UserDaoInterface userDao) {
		this.userDao = userDao;
	}
	
	@Override
	@Transactional
	public void insertUser(User user) {
		userDao.insertUser(user);
	}	

	@Override
	@Transactional
	public List<User> getUser() {
		return userDao.getUser();
		
	}

	@Override
	@Transactional
	public void deleteUser(String userEmail) {
		userDao.deleteUser(userEmail);

	}
	
	@Override
    @Transactional
    public User getUserByName(String name) {
        return this.userDao.getUserByName(name);
    }

	@Override
	@Transactional
	public void updateUser(User user) {
		userDao.updateUser(user);
	}
}
