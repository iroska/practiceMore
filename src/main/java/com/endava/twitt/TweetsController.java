package com.endava.twitt;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class TweetsController {

	private TweetServiceInterface tweetService;

	@Autowired(required = true)
	@Qualifier(value = "tweetService")
	public void setTweetService(TweetServiceInterface tweetService) {
		this.tweetService = tweetService;
	}
	
	@Autowired
	private UserServicesInterface userService;

	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/tweets", method = RequestMethod.GET)
	public String addTweet(@RequestParam String user_email,
			@RequestParam String descript,HttpSession session,Model model){
		
		if(session.equals(null)){
			return "redirect:/login";
		}
		
		if((descript==null||descript.equals(""))){
			return "redirect:/home";
		}
		
		if(user_email==null||user_email.equals("")){
			return "redirect:/login";
		}
		
		if(descript.length()>140){
			model.addAttribute("descriptionLengthError", "You can write no more than 140 characters please try again!");
			
			return "/home";
		}
		
		User user = userService.getUserByName(user_email);
		
		Tweets tweets=new Tweets();		
		tweets.setUser(user);
		tweets.setDescription(descript);
		tweets.setPublishedDate(new Date());
		
		this.tweetService.insertTweets(tweets);
		tweets=null;
		
		return "redirect:/home";
	}

	@RequestMapping(value = "/tweetsviwe", method = RequestMethod.GET)
	public String viweTweet(Model model) {
		model.addAttribute("tweet", new Tweets());
		model.addAttribute("tweetList", this.tweetService.getTweets());
		return "tweets";
	}

}
