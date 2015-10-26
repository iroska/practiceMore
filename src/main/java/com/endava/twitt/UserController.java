package com.endava.twitt;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.endava.twitt.models.Follow;
import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import com.endava.twitt.services.FollowServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
@Scope("session")
public class UserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	
	private FollowServiceInterface followService;
	
	
	public static Logger getLogger() {
		return logger;
	}

	@Autowired(required = true)
	@Qualifier(value = "followService")
	public void setFollowService(FollowServiceInterface followService) {
		this.followService = followService;
	}


	private UserServicesInterface userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(HttpSession session, Model model) {
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}
		List<User> list=userService.getUser();
		System.out.println("llist size in users= "+list.size());
		model.addAttribute("user", new User());
		model.addAttribute("userList", this.userService.getUser());
		
			
		return "users";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String addUser(@Valid @ModelAttribute("user") User user,
			BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "login";
		}

		if ((userService.getUserByName(user.getEmail()) == null)) {
			this.userService.insertUser(user);
		} else {
			model.addAttribute("userAlreadyExists",
					"<center>User already exists.<br/> Try another email address</center>");

			return "login";
		}

		model.addAttribute("successfulRegistration", "<center>Congratualtions.<br/> Succesful Registration!</center>");
		return "loginTest";
	}

	@RequestMapping("/delete")
	public String deleteUser(@ModelAttribute("email") String userEmail) {
		this.userService.deleteUser(userEmail);

		return "admin";
	}
	
	@RequestMapping("/editUserProfile")
	public String editUserProfile(@ModelAttribute("email") String userEmail) {
		
		//User user=userService.getUserByName(userEmail);
		//this.userService.updateUser(user);

		return "userProfileEdit";
	}
	
	@RequestMapping("/changeProfile")
	public String changeUserProfile(@Valid @ModelAttribute("user") User user,
			BindingResult result,HttpSession session, Model model) {
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}
		
		
		if (result.hasErrors()) {
			System.out.println("ERROR to change profile");
			return "userProfileEdit";
		}

		if ((userService.getUserByName(user.getEmail()) != null)) {			
			this.userService.updateUser(user);
			logger.info("User "+user.getEmail()+" updated his profile on:" +new Date());
		} else {
			model.addAttribute("userAlreadyExists",
					"<center>User already exists.<br/> Try another email address</center>");

			return "userProfileEdit";
		}
		
		session.setAttribute("loadedUser", user);

		model.addAttribute("successfulRegistration", "<center>Congratualtions.<br/> You changed your Profile Succesfully!</center>");
		return "userProfileEdit";
	}
	
	
	@RequestMapping("/follow")
	public String followUser(@RequestParam String user_email,
			@RequestParam String followedUser,HttpSession session, Model model) {
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}
		
		
		User user=userService.getUserByName(user_email);
		Set<Follow> listFollowOfUser=user.getFollowed();
		for(Follow foll:listFollowOfUser){
			if(foll.getFollowedUser().equals(followedUser)){
				System.out.println("followed exists"+foll.getFollowedUser());
				return "redirect:/users";
			}
		}
		
		Follow follow=new Follow();		
		follow.setFollowedUser(followedUser);
		follow.setUserFollowed(user);
		followService.insertFollow(follow);
		
		User user1 = userService.getUserByName(user_email);
		Set<Follow> allUsersFollow = user1.getFollowed();
		Integer listSizeFollow = allUsersFollow.size();
		System.out.println("ddasdasdasdas     "+listSizeFollow);
		for(Follow foll:listFollowOfUser){
			
				System.out.println("followed user"+foll.getFollowedUser());
				
			
		}
		
		return "redirect:/users";
	}
	
	@RequestMapping(value = "/deletefollowed", method = RequestMethod.GET)
	public String deletefollowed(Model model, HttpSession session,
			@RequestParam Integer idFollowToDelete,
			@RequestParam String followedUser, @RequestParam String userToDelete) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}
		
		User user1 = userService.getUserByName(userToDelete);
		Follow follow=new Follow();	
		follow.setId(idFollowToDelete);
		follow.setUserFollowed(user1);
		follow.setFollowedUser(followedUser);		
		this.followService.deleteUserFollow(follow);
		
		return "redirect:/followedusers";
	}
	
	@RequestMapping(value = "/followedusers", method = RequestMethod.GET)
	public String viewFollowees(HttpSession session, Model model) {
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}
		User user=userService.getUserByName((String)session.getAttribute("userID"));		
		Set<Follow> listFollowed=user.getFollowed();		
		System.out.println("list Size of followed users=========================== "+listFollowed.size());
		session.setAttribute("followedUsers", listFollowed);		
		
		return "followedusers";
	}	

}
