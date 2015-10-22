package com.endava.twitt;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesInterface;

@Controller
@Scope("session")
public class UserController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

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
			// new person, add it
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
	

}
