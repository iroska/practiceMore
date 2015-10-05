package com.endava.twitt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.User;


/**
 * Handles requests for the application home page.
 */
@Controller
public class WebController {

	@RequestMapping("/")
	public ModelAndView homeLogPage() {
		ModelAndView model = new ModelAndView("homeLogPage");
		return model;
	}
	
	//get user input from form
	@RequestMapping("/signup")
	public ModelAndView signPage(
			@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("userEmail") String userEmail,
			@RequestParam("userPassword") String userPassword) {
		
		User user=new User();
		
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(userEmail);
		user.setPassword(userPassword);
				
		ModelAndView model = new ModelAndView("signup");
		model.addObject("firstName",firstName);
		model.addObject("lastName",lastName);
		model.addObject("userEmail",userEmail);
		model.addObject("userPassword",userPassword);		
		
		return model;
	}	
	
	@RequestMapping(value="/loginSubmissionSuccess", method=RequestMethod.POST)
	public ModelAndView submissionSuccessPage() {
		ModelAndView model = new ModelAndView("successfulSubmission");
		return model;
	}
}
