package com.endava.twitt.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="follow")
public class Follow {

	@Id
	@GeneratedValue
	@Column(name = "id_follow")
	@OrderBy("id_follow DESC")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "User_Follow")
	private User userFollowed;

	public User getUserFollowed() {
		return userFollowed;
	}

	public void setUserFollowed(User userFollowed) {
		this.userFollowed = userFollowed;
	}

	@Column(name = "followedUser")
	private String followedUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	
	public String getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(String followedUser) {
		this.followedUser = followedUser;
	}
	
	
	
	
}
