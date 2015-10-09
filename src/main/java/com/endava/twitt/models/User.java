package com.endava.twitt.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "Id_Users")
	@GeneratedValue
	private int id;
	
	@Size(min = 2, max = 254, message=" tow to 254")
	@Column(name = "FirstName")
	private String firstName;
	
	@Size(min = 2, max = 254)
	@Column(name = "LastName")
	private String lastName;
	
	//@OneToMany(mappedBy="user")
	//private List<Followers> folow;
	
	/*public List<Followers> getFolow() {
		return folow;
	}

	public void setFolow(List<Followers> folow) {
		this.folow = folow;
	}*/

	//@ManyToMany
	//private List<Role> roles;

	@Size(min = 4, max = 254)
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	@Column(name = "Email")
	
	private String email;
	@NotEmpty
	@Size(min = 8, max = 25)
	@Column(name = "Password")
	private String password;
	@Size(min = 0, max = 254)
	@Column(name = "UserAvatar")
	private String avatar;
	
	
	/*public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}*/

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
