package com.endava.twitt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserRestController {

	 private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	 @RequestMapping(value="/issuers", method=RequestMethod.GET)
	 @ResponseBody
	 public String listUsers(){
		 
		 return "restUsers";
	 }
	
}
