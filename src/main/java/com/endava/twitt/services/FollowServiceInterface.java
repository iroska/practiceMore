package com.endava.twitt.services;

import java.util.List;
import java.util.Set;

import com.endava.twitt.models.Follow;

public interface FollowServiceInterface {

	public void insertFollow(Follow follow);

	public List<Follow> getFollows();

	public List<Follow> getFollowByUser(String userEmail);

	public void deleteUserFollow(Follow follow);
}
