package com.endava.twitt.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name = "user_follow")
	private String userFollowed;

	@Column(name = "followed_user")
	private String followedUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getUserFollowed() {
		return userFollowed;
	}

	public void setUserFollowed(String userFollowed) {
		this.userFollowed = userFollowed;
	}

	public String getFollowedUser() {
		return followedUser;
	}

	public void setFollowedUser(String followedUser) {
		this.followedUser = followedUser;
	}
	
	
	
	
}
