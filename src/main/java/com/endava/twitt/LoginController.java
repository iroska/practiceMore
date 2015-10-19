package com.endava.twitt;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
@Scope("session")
public class LoginController {

	@Autowired
	private UserServicesInterface userService;

	
	@Qualifier(value = "userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@Autowired
	private TweetServiceInterface tweetService;
	
	
	@Qualifier(value = "tweetService")
	public void setTweetService(TweetServiceInterface tweetService) {
		this.tweetService = tweetService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginShowForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String verifyLogin(@RequestParam String userEmail,
			@RequestParam String password, HttpSession session, Model model) {

		session.removeAttribute("loadedUser");
		session.removeAttribute("newloadedUser");
		
		
		User user = userService.loginUser(userEmail, password);
		User user1 = userService.getUserByName(userEmail);
		
		if (user == null) {
			model.addAttribute("loginError", "Please provide correct email and password!");
			return "login";
		} else if (user1.getRole().equals("ROLE_ADMIN")) {
			session.setAttribute("loadedUser", user);
			return "redirect:/admin";
		} 

			session.setAttribute("loadedUser", user);
			//session.setAttribute("listUsers", user);
						
			return "redirect:/home";		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loadedUser");
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		if(session.getAttribute("loadedUser")==null){
			return "login";
		}
		session.setAttribute("newloadedUser", session.getAttribute("loadedUser"));		
		session.setAttribute("existingUser", session.getAttribute("sessionUser"));
		model.addAttribute("users", new User());
		model.addAttribute("userList", this.userService.getUser());
		return "home";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/loginTest", method = RequestMethod.POST)
	public String loginTest() {
		return "loginTest";
	}

}
