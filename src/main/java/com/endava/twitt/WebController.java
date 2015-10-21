package com.endava.twitt;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
@Scope("session")
public class WebController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(WebController.class);
		
		
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(HttpSession session){
		
		
		if(session.getAttribute("loadedUser")==null){
			logger.info("Session false redirecting to login URL WebController class.");
			return "redirect:/login";
		}
		logger.info("Try to connect to home URL WebController class.");
		return "redirect:/home";			
	}
	
	

}
