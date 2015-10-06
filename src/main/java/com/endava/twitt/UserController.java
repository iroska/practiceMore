package com.endava.twitt;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.User;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class UserController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		
		//binder.setDisallowedFields(new String[] {"firstName"});
		
	}
	
	private UserServicesInterface userService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/showUsers", method=RequestMethod.GET)
	public String listUsers(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("userList", this.userService.getUser());
        return "showUsers";		
	}
	
	@RequestMapping(value = "/showUsers/add", method = RequestMethod.POST)
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result){
				
		if (result.hasErrors()){			
			return "homeLogPage";
		}
						
		 if(user.getId() == 0){
	            //new person, add it
	            this.userService.insertUser(user);
	        }else{
	            //existing person, call update
	            this.userService.updateUser(user);
	        }
		
	        return "successfulSubmission";
    }
	
	@RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId, Model model)
    {
		 model.addAttribute("user", this.userService.getPersonById(userId));
	        model.addAttribute("userList", this.userService.getUser());
	        return "showUsers";
    }
	
	
	
	

}
