package com.endava.twitt;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
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

		
		
		User user = userService.loginUser(userEmail, password);
		User user1 = userService.getUserByName(userEmail);
		if (user == null) {
			model.addAttribute("loginError", "Wrong email address or password");
			return "login";
		} else if (user1.getRole().equals("ROLE_ADMIN")) {

			session.setAttribute("loadedUser", user);

			return "redirect:/admin";
		} 

			session.setAttribute("loadedUser", user);
						
			return "redirect:/home";		
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("loadedUser");
		return "login";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		session.setAttribute("newloadedUser", session.getAttribute("loadedUser"));
		
		
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
