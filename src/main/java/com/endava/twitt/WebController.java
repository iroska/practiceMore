package com.endava.twitt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Handles requests for the application home page.
 */
@Controller
public class WebController {
		
		
	@RequestMapping("/")
	public String index(){
		return "login";
	}
	
	
	
	

}
