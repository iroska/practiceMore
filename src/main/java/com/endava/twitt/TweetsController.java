package com.endava.twitt;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.services.TweetServiceInterface;

@Controller
public class TweetsController {
	
	private TweetServiceInterface tweetService;
	
	@Autowired(required=true)
	@Qualifier(value="tweetService")
	public void setTweetService(TweetServiceInterface tweetService) {
		this.tweetService = tweetService;
	}
	
	
	@RequestMapping(value="/tweets", method=RequestMethod.GET)	
    public String addTweet(@Valid @ModelAttribute("twee") Tweets twee, BindingResult result){
		
		
		if (result.hasErrors()){			
			return "home";
		}
						
		 if(true){
	            this.tweetService.insertTweets(twee);
	        }
		 System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
	        return "tweets";
    }
	
	
	
}
