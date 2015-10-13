package com.endava.twitt.services;

import java.util.List;

import com.endava.twitt.models.User;

public interface UserServicesInterface {
	
	public void insertUser(User user);

	public List<User> getUser();

	public void deleteUser(String userEmail);
	
	public User getUserByName(String name);

	public void updateUser(User user);

}
