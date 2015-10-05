package com.endava.twitt;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class UserController {
	
	private UserServicesInterface userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String listUsers(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("userList", this.userService.getUser());
        return "showUsers";		
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user)
    {
		 if(user.getId() == 0){
	            //new person, add it
	            this.userService.insertUser(user);
	        }else{
	            //existing person, call update
	            this.userService.updateUser(user);
	        }
	         
	        return "redirect:/user";
    }
	
	@RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId, Model model)
    {
		 model.addAttribute("user", this.userService.getPersonById(userId));
	        model.addAttribute("userList", this.userService.getUser());
	        return "showUsers";
    }
	
	
	
	

}
