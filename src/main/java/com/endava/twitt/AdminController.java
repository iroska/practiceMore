package com.endava.twitt;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.GlobalVariables;
import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	private UserServicesInterface userService;

	@Autowired(required = true)
	@Qualifier(value = "userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}
	
	private TweetServiceInterface tweetService;

	@Autowired(required = true)
	@Qualifier(value = "tweetService")
	public void setTweetService(TweetServiceInterface tweetService) {
		this.tweetService = tweetService;
	}
	
	@RequestMapping(value = "/userstweet_admin", method = RequestMethod.GET)
	public ModelAndView viweTweetOfUser(HttpSession session,
			@RequestParam String userEmail) {

		if (session.getAttribute("loadedUser") == null) {
			ModelAndView model = new ModelAndView("redirect:/login");
			return model;
		}
		
		session.removeAttribute("sizeUserTweetsUser");		
		session.removeAttribute("specialUser");
		session.removeAttribute("userID");
		session.removeAttribute("currentUserData");
		session.removeAttribute("currentUser");

		ModelAndView model = new ModelAndView("personTweetsAdmin");
		logger.debug("List user's tweets in admin mode.");
		
		User user = userService.getUserByName(userEmail);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		session.setAttribute("numberOfUsersTweetsUser", listSize);
		
		Integer numberOfTweetsOnPageUser = new GlobalVariables().tweetsOnPage;
		Integer firstrowUser = 0;
		Integer rowcountUser = 0;
		if (listSize == 0) {
			firstrowUser = 0;
			rowcountUser = 0;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPageUser) {
			firstrowUser = 0;
			rowcountUser = listSize;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		} else if (listSize >= numberOfTweetsOnPageUser) {
			firstrowUser = 0;
			rowcountUser = numberOfTweetsOnPageUser;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		}
		
		List<Tweets> userSubTweetsUser = allUsersTweets.subList(firstrowUser, rowcountUser);
		session.setAttribute("userTweetsSublistUser", userSubTweetsUser);
		session.setAttribute("sizeUserTweetsUser", listSize);		
		session.setAttribute("sessionUser", user);
		session.setAttribute("userID", user.getEmail());

		return model;
	}
	

	@RequestMapping(value = "/delite_user", method = RequestMethod.GET)
	public String deliteUser(HttpSession session,
			@RequestParam String userEmail) {
		if (session.getAttribute("loadedUser") == null) {			
			return "redirect:/login";
		}
		
		try{
		logger.debug("Try to delete user "+userEmail);
		this.userService.deleteUser(userEmail);		
		return "redirect:/admin";
		}catch (HibernateException e){
			logger.error("Couldn't delete user. "+userEmail);
			return "redirect:/admin";
		}
		

	}
		
	
	@RequestMapping(value = "/delite_user_tweet", method = RequestMethod.GET)
	public String deleteMyTweet(Model model, HttpSession session,
			@RequestParam Integer idTweetToDelete,
			@RequestParam String textTodelete, @RequestParam String userToDelete) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		User user1 = userService.getUserByName(userToDelete);
		Tweets tweets = new Tweets();
		tweets.setId(idTweetToDelete);
		tweets.setUser(user1);
		tweets.setDescription(textTodelete);
		tweets.setPublishedDate(new Date());
		this.tweetService.deleteUser(tweets);
		
		session.setAttribute("currentUserData", user1);
		session.setAttribute("currentUser", user1.getEmail());
		
		return "redirect:/personTweetsAdmin";
	}	
	
	@RequestMapping(value = "/personTweetsAdmin", method = RequestMethod.GET)
	public String personMyTweet(Model model, HttpSession session){
		
		if (session.getAttribute("loadedUser") == null) {
			
			return "redirect:/login";
		}
		
				
		User user = userService.getUserByName((String)session.getAttribute("currentUser"));
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		session.setAttribute("numberOfUsersTweetsUser", listSize);
		
		Integer numberOfTweetsOnPageUser = new GlobalVariables().tweetsOnPage;
		Integer firstrowUser = 0;
		Integer rowcountUser = 0;
		if (listSize == 0) {
			firstrowUser = 0;
			rowcountUser = 0;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPageUser) {
			firstrowUser = 0;
			rowcountUser = listSize;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		} else if (listSize >= numberOfTweetsOnPageUser) {
			firstrowUser = 0;
			rowcountUser = numberOfTweetsOnPageUser;
			session.setAttribute("firstRowUser", firstrowUser);
			session.setAttribute("rowCountUser", rowcountUser);
		}
		
		List<Tweets> userSubTweetsUser = allUsersTweets.subList(firstrowUser, rowcountUser);
		session.setAttribute("userTweetsSublistUser", userSubTweetsUser);

		session.setAttribute("sizeUserTweetsUser", listSize);		
		session.setAttribute("sessionUser", user);
		session.setAttribute("userID", user.getEmail());
		
		
		return "personTweetsAdmin";
		
		
	}
	
	
	
	
	
}
