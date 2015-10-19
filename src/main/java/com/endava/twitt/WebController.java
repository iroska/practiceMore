package com.endava.twitt;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class WebController {
		
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session){
		if(session.getAttribute("loadedUser")==null){
			return "login";
		}
		
		return "redirect:/home";			
	}
	
	

}
