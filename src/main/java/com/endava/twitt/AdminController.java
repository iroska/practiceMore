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
import com.endava.twitt.services.FollowServiceInterface;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	private FollowServiceInterface followService;

	@Autowired(required = true)
	@Qualifier(value = "followService")
	public void setFollowService(FollowServiceInterface followService) {
		this.followService = followService;
	}

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

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(HttpSession session, Model model) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		model.addAttribute("user", new User());
		model.addAttribute("userList", this.userService.getUser());

		return "admin";
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

		try {
			logger.debug("Try to retrive sublist of users");
			List<Tweets> userSubTweetsUser = allUsersTweets.subList(
					firstrowUser, rowcountUser);
			session.setAttribute("userTweetsSublistUser", userSubTweetsUser);
		} catch (IndexOutOfBoundsException e) {
			logger.error("Error to fulfill request with indexes "
					+ firstrowUser + " " + rowcountUser);
			return new ModelAndView("redirect:/admin");
		}
		
		Integer numberOfTweetsOnPage1 = new GlobalVariables().tweetsOnPage;
		Integer numberOfPages;
		Integer selectedPageUser;
		if (listSize % numberOfTweetsOnPage1 == 0 && numberOfTweetsOnPage1 > 0) {
			numberOfPages = listSize / numberOfTweetsOnPage1;
		} else if (numberOfTweetsOnPage1 == 0) {
			numberOfPages = 0;
		} else {
			numberOfPages = Math.abs(listSize / numberOfTweetsOnPage1) + 1;
		}

		if (rowcountUser % numberOfTweetsOnPage1 == 0) {
			selectedPageUser = rowcountUser / numberOfTweetsOnPage1;
		} else if (rowcountUser % numberOfTweetsOnPage1 != 0) {
			selectedPageUser = Math.abs(rowcountUser / numberOfTweetsOnPage1) + 1;
		} else {
			selectedPageUser = 0;
		}
		
		session.setAttribute("selectedRealPageUserAdmin", selectedPageUser);
		session.setAttribute("numberOfRealPagesUserAdmin", numberOfPages);
		session.setAttribute("sizeUserTweetsUser", listSize);
		session.setAttribute("sessionUser", user);
		session.setAttribute("userIDAdmin", user.getEmail());

		return model;
	}

	@RequestMapping(value = "/delite_user", method = RequestMethod.GET)
	public String deliteUser(HttpSession session, @RequestParam String userEmail) {
		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		User user = userService.getUserByName(userEmail);

		if (user.getRole().equals("ROLE_ADMIN")) {
			return "redirect:/admin";
		}
		try {
			logger.debug("Try to delete user " + userEmail);

			this.followService.deleteAllUserFollow(userEmail);

			this.userService.deleteUser(userEmail);
			return "redirect:/admin";
		} catch (HibernateException e) {
			logger.error("Couldn't delete user. " + userEmail);
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
		logger.debug("Try to delete user's tweet");
		User user1 = userService.getUserByName(userToDelete);
		Tweets tweets = new Tweets();
		tweets.setId(idTweetToDelete);
		tweets.setUser(user1);
		tweets.setDescription(textTodelete);
		tweets.setPublishedDate(new Date());
		this.tweetService.deleteUser(tweets);
		logger.debug("User's tweet was delited");
		
		
		User user = userService.getUserByName(userToDelete);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		Integer numberOfTweetsOnPage = new GlobalVariables().tweetsOnPage;
		Integer firstRowUser = 0;
		Integer rowCountUser = 0;
		if (listSize == 0) {
			firstRowUser = 0;
			rowCountUser = 0;
			session.setAttribute("firstRowUser", firstRowUser);
			session.setAttribute("rowCountUser", rowCountUser);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPage) {
			firstRowUser = 0;
			rowCountUser = listSize;
			session.setAttribute("firstRowUser", firstRowUser);
			session.setAttribute("rowCountUser", rowCountUser);
		} else if (listSize >= numberOfTweetsOnPage) {
			firstRowUser = 0;
			rowCountUser = numberOfTweetsOnPage;
			session.setAttribute("firstRowUser", firstRowUser);
			session.setAttribute("rowCountUser", rowCountUser);
		}

		logger.debug("Size = " + listSize + "In delete Tweet = "
				+ (Integer) session.getAttribute("firstRowUser") + " "
				+ (Integer) session.getAttribute("rowCountUser"));
		
		session.setAttribute("currentUserData", user1);
		session.setAttribute("currentUser", user1.getEmail());

		return "redirect:/personTweetsAdmin";
	}

	@RequestMapping(value = "/personTweetsAdmin", method = RequestMethod.GET)
	public String personMyTweet(Model model, HttpSession session) {

		if (session.getAttribute("loadedUser") == null) {

			return "redirect:/login";
		}

		session.removeAttribute("selectedRealPageUserAdmin");
		session.removeAttribute("numberOfRealPagesUserAdmin");

		User user1 = userService.getUserByName((String) session
				.getAttribute("userIDAdmin"));
		List<Tweets> allUsersTweets = user1.getTweet();
		Integer listSize = allUsersTweets.size();
		session.setAttribute("sizeUserTweetsUser", listSize);
		session.setAttribute("numberOfUsersTweetsUser", listSize);

		Integer firstrowUser = (Integer) session.getAttribute("firstRowUser");
		Integer rowcountUser = (Integer) session.getAttribute("rowCountUser");
		System.out.println("Size = " + listSize + " In personTweets = "
				+ (Integer) session.getAttribute("firstRowUser") + " "
				+ (Integer) session.getAttribute("rowCountUser"));
		
		
		try {
			logger.debug("Try to retrive sublist of users");
		List<Tweets> userSubTweetsUser = allUsersTweets.subList(firstrowUser,
				rowcountUser);
		session.setAttribute("userTweetsSublistUser", userSubTweetsUser);
		} catch (IndexOutOfBoundsException e) {
			logger.error("Error to fulfill request with indexes "
					+ firstrowUser + " " + rowcountUser);
			return "redirect:/home";
		}
		

		Integer numberOfTweetsOnPage1 = new GlobalVariables().tweetsOnPage;
		Integer numberOfPages;
		Integer selectedPageUser;
		if (listSize % numberOfTweetsOnPage1 == 0 && numberOfTweetsOnPage1 > 0) {
			numberOfPages = listSize / numberOfTweetsOnPage1;
		} else if (numberOfTweetsOnPage1 == 0) {
			numberOfPages = 0;
		} else {
			numberOfPages = Math.abs(listSize / numberOfTweetsOnPage1) + 1;
		}

		if (rowcountUser % numberOfTweetsOnPage1 == 0) {
			selectedPageUser = rowcountUser / numberOfTweetsOnPage1;
		} else if (rowcountUser % numberOfTweetsOnPage1 != 0) {
			selectedPageUser = Math.abs(rowcountUser / numberOfTweetsOnPage1) + 1;
		} else {
			selectedPageUser = 0;
		}

		session.setAttribute("selectedRealPageUserAdmin", selectedPageUser);
		session.setAttribute("numberOfRealPagesUserAdmin", numberOfPages);
		/*session.setAttribute("sizeUserTweetsUser", listSize);
		session.setAttribute("sessionUser", user);
		session.setAttribute("userID", user.getEmail());*/

		return "personTweetsAdmin";

	}

}
