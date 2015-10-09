package com.endava.twitt.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Item {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String title;
	private String description;
	private Date publishedDate;
	
	@ManyToOne
	@JoinColumn(name="follow_id")
	private Followers folow;
	
	public Followers getFolow() {
		return folow;
	}
	public void setFolow(Followers folow) {
		this.folow = folow;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
		this.publishedDate = publishedDate;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	private String link;

}
