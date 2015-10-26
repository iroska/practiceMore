package com.endava.twitt.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endava.twitt.dao.FollowDaoInterface;
import com.endava.twitt.models.Follow;

@Service
public class FollowServiceImplement implements FollowServiceInterface {
	
	private FollowDaoInterface followDaoInterface;

	public void setFollowDaoInterface(FollowDaoInterface followDaoInterface) {
		this.followDaoInterface = followDaoInterface;
	}

	@Override
	@Transactional
	public void insertFollow(Follow follow) {
		followDaoInterface.insertFollow(follow);		
	}

	@Override
	@Transactional
	public List<Follow> getFollows() {
		
		return followDaoInterface.getFollows();
	}

	@Override
	@Transactional
	public List<Follow> getFollowByUser(String userEmail) {		
		return followDaoInterface.getFollowByUser(userEmail);
	}

	@Override
	@Transactional
	public void deleteUserFollow(Follow follow) {
		followDaoInterface.deleteUserFollow(follow);
	}
	
	

}
