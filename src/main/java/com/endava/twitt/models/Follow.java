package com.endava.twitt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	//@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	//@ManyToOne(fetch = FetchType.EAGER)
	//@JoinColumn(name = "User_Follow")
	@Column (name = "User_Follow")	
	private String userFollowed;

	public String getUserFollowed() {
		return userFollowed;
	}

	public void setUserFollowed(String userFollowed) {
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
