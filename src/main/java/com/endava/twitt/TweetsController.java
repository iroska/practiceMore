package com.endava.twitt;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
@Scope("session")
public class TweetsController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TweetsController.class);

	private TweetServiceInterface tweetService;

	@Autowired(required = true)
	@Qualifier(value = "tweetService")
	public void setTweetService(TweetServiceInterface tweetService) {
		this.tweetService = tweetService;
	}
	
	@Autowired
	private UserServicesInterface userService;
	@Qualifier(value = "userService")
	public void setUserService(UserServicesInterface userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/tweets", method = RequestMethod.POST)
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
			User user = userService.getUserByName(user_email);
			List<Tweets> allUsersTweets=user.getTweet();		
			Integer listSize = allUsersTweets.size();
			Integer firstrow = 0;
			Integer rowcount = 0;		
			if (listSize == 0) {
				firstrow = 0;
				rowcount = 0;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			} else if (listSize > 0 && listSize < 5) {
				firstrow = 0;
				rowcount = listSize;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			} else if (listSize >= 5) {
				firstrow = 0;
				rowcount = 5;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			}			
			
			return "/home";
		}		
		
		User user1 = userService.getUserByName(user_email);
		Tweets tweets=new Tweets();		
		tweets.setUser(user1);
		tweets.setDescription(descript);
		tweets.setPublishedDate(new Date());
		this.tweetService.insertTweets(tweets);
		
		System.out.println("In tweets view = "+(Integer)session.getAttribute("firstRow")+" "+(Integer)session.getAttribute("rowCount"));
		
		User user = userService.getUserByName(user_email);
		List<Tweets> allUsersTweets=user.getTweet();		
		Integer listSize = allUsersTweets.size();
		Integer firstrow = 0;
		Integer rowcount = 0;		
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < 5) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= 5) {
			firstrow = 0;
			rowcount = 5;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}

		
		System.out.println("Size = "+listSize +"In TweetControler = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));
		
						
		return "redirect:/home";
	}

	@RequestMapping(value = "/tweetsviwe", method = RequestMethod.GET)
	public String viweTweet(HttpSession session,Model model) {
		
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}
		
		model.addAttribute("tweet", new Tweets());
		model.addAttribute("tweetList", this.tweetService.getTweets());
		return "alltweets";
	}
	
	@RequestMapping(value = "/userstweet", method = RequestMethod.GET)
	public ModelAndView viweTweetOfUser(HttpSession session, @RequestParam String userEmail) {
				
		if(session.getAttribute("loadedUser")==null){
			ModelAndView model= new ModelAndView("redirect:/login");
			return model;
		}		
		
		ModelAndView model= new ModelAndView("personTweets");
		User user = userService.getUserByName(userEmail);
		List<Tweets> allUsersTweets=user.getTweet();		
		Integer listSize = allUsersTweets.size();
		model.addObject("numberOfUsersTweets", listSize);
		/*Integer firstrow = 0;
		Integer rowcount = 0;		
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < 5) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= 5) {
			firstrow = 0;
			rowcount = 5;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}		*/
		
		
		model.addObject("users", new User());
		model.addObject("specialUser", user);		
		return model;
	}
	
	@RequestMapping(value = "/editmytweet", method = RequestMethod.GET)
	public String editTweet(Model model, HttpSession session, @RequestParam String textToEdit, @RequestParam Integer idTweetToEdit) {
		
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}	
				
		model.addAttribute("editedTweet",textToEdit );
		model.addAttribute("idEditedTweet",idTweetToEdit );
		return "editMyTweet";
	}
	
	
	@RequestMapping(value = "/saveUpdatedTweet", method = RequestMethod.POST)
	public String saveEditedTweet(Model model, HttpSession session,@RequestParam Integer idTweet, @RequestParam String updatedTweet,@RequestParam String userToEdit) {
		
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}			
		
		User user1 = userService.getUserByName(userToEdit);
		Tweets tweets=new Tweets();	
		tweets.setId(idTweet);
		tweets.setUser(user1);
		tweets.setDescription(updatedTweet);
		tweets.setPublishedDate(new Date());
		this.tweetService.updateTweet(tweets);
				
			
		System.out.println("In saveUpdatedTweet = "+(Integer)session.getAttribute("firstRow")+" "+(Integer)session.getAttribute("rowCount"));
		
		User user = userService.getUserByName(userToEdit);	
		List<Tweets> allUsersTweets=user.getTweet();		
		Integer listSize = allUsersTweets.size();
		Integer firstrow = 0;
		Integer rowcount = 0;		
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < 5) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= 5) {
			firstrow = 0;
			rowcount = 5;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}		
						
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/deletemytweet", method = RequestMethod.GET)
	public String deleteMyTweet(Model model, HttpSession session, @RequestParam Integer idTweetToDelete, @RequestParam String textTodelete,@RequestParam String userToDelete) {
		
		if(session.getAttribute("loadedUser")==null){
			return "redirect:/login";
		}			
		
		User user1 = userService.getUserByName(userToDelete);
		Tweets tweets=new Tweets();	
		tweets.setId(idTweetToDelete);
		tweets.setUser(user1);
		tweets.setDescription(textTodelete);
		tweets.setPublishedDate(new Date());
		this.tweetService.deleteUser(tweets);
		
		User user = userService.getUserByName(userToDelete);
		List<Tweets> allUsersTweets=user.getTweet();		
		Integer listSize = allUsersTweets.size();
		Integer firstrow = 0;
		Integer rowcount = 0;		
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < 5) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= 5) {
			firstrow = 0;
			rowcount = 5;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}
		
		System.out.println("Size = "+listSize +"In delete Tweet = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));				
						
		return "redirect:/home";
	}
}
