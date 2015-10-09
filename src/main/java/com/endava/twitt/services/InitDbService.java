package com.endava.twitt.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.endava.twitt.dao.FollowersRepository;
import com.endava.twitt.dao.ItemRepository;
import com.endava.twitt.dao.RoleRepository;
import com.endava.twitt.dao.UserRepository;
import com.endava.twitt.models.Followers;
import com.endava.twitt.models.Item;
import com.endava.twitt.models.Role;
import com.endava.twitt.models.User;

//@Transactional
//@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowersRepository followersRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	
	public void init(){
		Role roleUser= new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);
		
		Role roleAdmin= new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);
		
		User userAdmin=new User();
		userAdmin.setFirstName("admin");
		List <Role> roles=new ArrayList<Role>();
		roles.add(roleAdmin);
		roles.add(roleUser);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		
		Followers followers=new Followers();
		followers.setName("Team 01");
		followers.setUrl("http://www.google.com");
		followers.setUser(userAdmin);
		followersRepository.save(followers);
		
		Item item1=new Item();
		item1.setFolow(followers);
		item1.setTitle("first");
		item1.setLink("http://www.endava.com");
		item1.setPublishedDate(new Date());
		
		Item item2=new Item();
		item2.setFolow(followers);
		item2.setTitle("second");
		item2.setLink("http://www.twitter.com");
		item2.setPublishedDate(new Date());
		
		
		
		
		
		
	}
	
}
