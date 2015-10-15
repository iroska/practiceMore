package com.endava.twitt;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class UserController {

	private UserServicesInterface userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(Model model) {
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

		return "users";
	}

}
