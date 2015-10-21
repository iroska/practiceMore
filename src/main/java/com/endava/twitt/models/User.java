package com.endava.twitt.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

	public User() {

	}
	
	@Size(min = 2, max = 254, message = " tow to 254")
	@Column(name = "FirstName")
	private String firstName;

	@Size(min = 2, max = 254)
	@Column(name = "LastName")
	private String lastName;

	@Column(name = "Role", insertable = false)
	private String role;

	@OneToMany(targetEntity = Tweets.class, mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("publishedDate DESC")
	private List<Tweets> tweet;

	@Id
	@Size(min = 4, max = 254)
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name = "Email")
	private String email;
	@NotEmpty
	@Size(min = 8, max = 25)
	@Column(name = "Password")
	private String password;

	public User(Integer firstRow, Integer rowCount) {

	}

	/*----------------------------------------------------*/

	public List<Tweets> getTweet() {
		return tweet;
	}	

	public void setTweet(List<Tweets> tweet) {
		this.tweet = tweet;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
