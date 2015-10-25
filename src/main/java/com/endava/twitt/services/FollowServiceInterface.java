package com.endava.twitt.services;

import java.util.List;
import java.util.Set;

import com.endava.twitt.models.Follow;

public interface FollowServiceInterface {

	public void insertFollow(Follow follow);

	public Set<Follow> getFollows();

	public Set<Follow> getFollowByUser(String userEmail);

	public void deleteUserFollow(Follow follow);
}
