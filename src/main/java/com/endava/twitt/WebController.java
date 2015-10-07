package com.endava.twitt;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.User;




/**
 * Handles requests for the application home page.
 */
@Controller
public class WebController {

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView homeLogPage(@Valid @ModelAttribute("user") User user, BindingResult result) {
		
		
		ModelAndView model = new ModelAndView("homeLogPage");
		return model;
		
	}
	
	//get user input from form
	@RequestMapping(value="/signups", method=RequestMethod.POST)
	public ModelAndView signPage(@ModelAttribute("user") User user) {
								
		ModelAndView model = new ModelAndView("signup");					
		return model;
	}	
	
	@RequestMapping(value="/loginSubmissionSuccess", method=RequestMethod.POST)
	public ModelAndView loginSubmissionSuccess(@ModelAttribute("user") User user, BindingResult result ) {
		System.out.println("dasdasdas");
		if (result.hasErrors()){
			
			ModelAndView model = new ModelAndView("homeLogPage");
			return model;
		}
		
		ModelAndView model = new ModelAndView("successfulSubmission");
		return model;
	}
}
