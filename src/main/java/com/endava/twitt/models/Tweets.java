package com.endava.twitt.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tweets")
public class Tweets {

	/*public Tweets() {

	}

	public Tweets(int id,User user,String description, Date publishedDate){
		this.id=id;
		this.user=user;
		this.description=description;
		this.publishedDate=new Date();
	}*/

	@Id
	@GeneratedValue
	@Column(name = "Id_Tweets")
	private int id;

	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.REFRESH)
	@JoinColumn(name = "Users_Email")
	private User user;

	@Size(min = 0, max = 140)
	@Column(name = "description")
	private String description;

	@Column(name = "publishedDate", unique=false, insertable = true)
	private Date publishedDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = new Date();
	}

}
