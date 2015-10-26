package com.endava.twitt;

import com.endava.twitt.models.GlobalVariables;
import com.endava.twitt.models.Tweets;
import com.endava.twitt.models.User;
import com.endava.twitt.services.TweetServiceInterface;
import com.endava.twitt.services.UserServicesInterface;
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

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

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
			@RequestParam String descript, HttpSession session, Model model) {

		if (session.equals(null)) {
			return "redirect:/login";
		}

		if ((descript == null || descript.equals(""))) {
			return "redirect:/home";
		}

		if (user_email == null || user_email.equals("")) {
			return "redirect:/login";
		}

		if (descript.length() > 140) {
			model.addAttribute("descriptionLengthError",
					"You can write no more than 140 characters please try again!");
			User user = userService.getUserByName(user_email);
			List<Tweets> allUsersTweets = user.getTweet();
			Integer listSize = allUsersTweets.size();
			Integer numberOfTweetsOnPage = new GlobalVariables().tweetsOnPage;
			Integer firstrow = 0;
			Integer rowcount = 0;
			if (listSize == 0) {
				firstrow = 0;
				rowcount = 0;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			} else if (listSize > 0 && listSize < numberOfTweetsOnPage) {
				firstrow = 0;
				rowcount = listSize;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			} else if (listSize >= numberOfTweetsOnPage) {
				firstrow = 0;
				rowcount = numberOfTweetsOnPage;
				session.setAttribute("firstRow", firstrow);
				session.setAttribute("rowCount", rowcount);
			}

			return "/home";
		}

		User user1 = userService.getUserByName(user_email);
		Tweets tweets = new Tweets();
		tweets.setUser(user1);
		tweets.setDescription(descript);
		tweets.setPublishedDate(new Date());
		this.tweetService.insertTweets(tweets);

		System.out.println("In tweets view = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));

		User user = userService.getUserByName(user_email);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		//Integer numberOfTweetsOnPage = new GlobalVariables().tweetsOnPage;
		Integer numberOfTweetsOnPage =5;
		Integer firstrow = 0;
		Integer rowcount = 0;
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = numberOfTweetsOnPage;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}

		System.out.println("Size = " + listSize + "In TweetControler = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));

		return "redirect:/home";
	}

	@RequestMapping(value = "/tweetsviwe", method = RequestMethod.GET)
	public String viweTweet(HttpSession session, Model model) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		model.addAttribute("tweet", new Tweets());
		model.addAttribute("tweetList", this.tweetService.getTweets());
		return "alltweets";
	}

	@RequestMapping(value = "/userstweet", method = RequestMethod.GET)
	public ModelAndView viweTweetOfUser(HttpSession session,
			@RequestParam String userEmail) {

		if (session.getAttribute("loadedUser") == null) {
			ModelAndView model = new ModelAndView("redirect:/login");
			return model;
		}
		
		session.removeAttribute("sizeUserTweetsUser");		
		session.removeAttribute("specialUser");
		session.removeAttribute("userIdTweets");

		ModelAndView model = new ModelAndView("personTweets");

		User user = userService.getUserByName(userEmail);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		session.setAttribute("numberOfUsersTweetsUser", listSize);
		
		//Integer numberOfTweetsOnPageUser = new GlobalVariables().tweetsOnPage;
		Integer numberOfTweetsOnPageUser = 5;
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
		session.setAttribute("specialUser", user);
		session.setAttribute("userIdTweets", user.getEmail());

		return model;
	}

	
	@RequestMapping(value = "/personTweets", method = RequestMethod.GET)
	public String ListOfUsersTweets(Model model, HttpSession session) {
		
		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}
		
		User user1 = userService.getUserByName((String) session
				.getAttribute("userIdTweets"));
		List<Tweets> allUsersTweets = user1.getTweet();
		Integer listSize = allUsersTweets.size();				
		session.setAttribute("sizeUserTweetsUser", listSize);
		
		Integer firstrowUser = (Integer) session.getAttribute("firstRowUser");
		Integer rowcountUser = (Integer) session.getAttribute("rowCountUser");
		System.out.println("Size = "+listSize +" In personTweets = "
				+ (Integer) session.getAttribute("firstRowUser") + " "
				+ (Integer) session.getAttribute("rowCountUser"));
		List<Tweets> userSubTweetsUser = allUsersTweets.subList(firstrowUser, rowcountUser);
		session.setAttribute("userTweetsSublistUser", userSubTweetsUser);	
		
		return "personTweets";
	}	

	@RequestMapping(value = "/editmytweet", method = RequestMethod.GET)
	public String editTweet(Model model, HttpSession session,
			@RequestParam String textToEdit, @RequestParam Integer idTweetToEdit) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		model.addAttribute("editedTweet", textToEdit);
		model.addAttribute("idEditedTweet", idTweetToEdit);
		return "editMyTweet";
	}

	@RequestMapping(value = "/saveUpdatedTweet", method = RequestMethod.POST)
	public String saveEditedTweet(Model model, HttpSession session,
			@RequestParam Integer idTweet, @RequestParam String updatedTweet,
			@RequestParam String userToEdit) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		User user1 = userService.getUserByName(userToEdit);
		Tweets tweets = new Tweets();
		tweets.setId(idTweet);
		tweets.setUser(user1);
		tweets.setDescription(updatedTweet);
		tweets.setPublishedDate(new Date());
		this.tweetService.updateTweet(tweets);

		System.out.println("In saveUpdatedTweet = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));

		User user = userService.getUserByName(userToEdit);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		//Integer numberOfTweetsOnPage = new GlobalVariables().tweetsOnPage;
		Integer numberOfTweetsOnPage =5;
		Integer firstrow = 0;
		Integer rowcount = 0;
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = numberOfTweetsOnPage;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}

		return "redirect:/home";
	}

	@RequestMapping(value = "/deletemytweet", method = RequestMethod.GET)
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

		User user = userService.getUserByName(userToDelete);
		List<Tweets> allUsersTweets = user.getTweet();
		Integer listSize = allUsersTweets.size();
		//Integer numberOfTweetsOnPage = new GlobalVariables().tweetsOnPage;
		Integer numberOfTweetsOnPage =5;
		Integer firstrow = 0;
		Integer rowcount = 0;
		if (listSize == 0) {
			firstrow = 0;
			rowcount = 0;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize > 0 && listSize < numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = listSize;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		} else if (listSize >= numberOfTweetsOnPage) {
			firstrow = 0;
			rowcount = numberOfTweetsOnPage;
			session.setAttribute("firstRow", firstrow);
			session.setAttribute("rowCount", rowcount);
		}

		System.out.println("Size = " + listSize + "In delete Tweet = "
				+ (Integer) session.getAttribute("firstRow") + " "
				+ (Integer) session.getAttribute("rowCount"));

		return "redirect:/home";
	}

	@RequestMapping(value = "/paginateTweetsUser", method = RequestMethod.GET)
	public String paginating(HttpSession session,
			@RequestParam Integer firstrowUser,
			@RequestParam Integer rowcountUser,
			@RequestParam String user_email, @RequestParam String pageUser,
			Model model) {

		if (session.getAttribute("loadedUser") == null) {
			return "redirect:/login";
		}

		//Integer numberOfTweetsOnPageUser = new GlobalVariables().tweetsOnPage;
		Integer numberOfTweetsOnPageUser =5;

		if (pageUser.equals("Next")) {
			Integer sizeTweetsListUser = (Integer) session
					.getAttribute("sizeUserTweetsUser");
			if ((sizeTweetsListUser % numberOfTweetsOnPageUser == 0 && rowcountUser >= numberOfTweetsOnPageUser)) {
				firstrowUser += numberOfTweetsOnPageUser;
				rowcountUser += numberOfTweetsOnPageUser;

				if (rowcountUser > sizeTweetsListUser) {
					firstrowUser -= numberOfTweetsOnPageUser;
					rowcountUser = sizeTweetsListUser;
					session.setAttribute("firstRowUser", firstrowUser);
					session.setAttribute("rowCountUser", rowcountUser);
					System.out.println("Size = " + sizeTweetsListUser
							+ " In Next 1 paginateTweetsUser = "
							+ (Integer) session.getAttribute("firstRowUser")
							+ " "
							+ (Integer) session.getAttribute("rowCountUser"));
				} else {
					session.setAttribute("firstRowUser", firstrowUser);
					session.setAttribute("rowCountUser", rowcountUser);
					System.out.println("Size = " + sizeTweetsListUser
							+ " In Next 1 ELSE paginateTweetsUser = "
							+ (Integer) session.getAttribute("firstRowUser")
							+ " "
							+ (Integer) session.getAttribute("rowCountUser"));
				}

			} else if (sizeTweetsListUser % numberOfTweetsOnPageUser == 0
					&& (rowcountUser < numberOfTweetsOnPageUser)) {

				System.out.println("Size = " + sizeTweetsListUser
						+ " In Next 2 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));
				
				return "redirect:/personTweets";

			} else if ((sizeTweetsListUser % numberOfTweetsOnPageUser != 0 && rowcountUser >= numberOfTweetsOnPageUser)
					&& (sizeTweetsListUser - rowcountUser) < numberOfTweetsOnPageUser) {
				Integer lastTweets = sizeTweetsListUser
						% numberOfTweetsOnPageUser;
				firstrowUser += numberOfTweetsOnPageUser;
				rowcountUser += lastTweets;

				if (rowcountUser > sizeTweetsListUser) {
					firstrowUser -= numberOfTweetsOnPageUser;
					rowcountUser = sizeTweetsListUser;
					session.setAttribute("firstRowUser", firstrowUser);
					session.setAttribute("rowCountUser", rowcountUser);
					System.out.println("Size = " + sizeTweetsListUser
							+ " In Next 3 paginateTweetsUser= "
							+ (Integer) session.getAttribute("firstRowUser")
							+ " "
							+ (Integer) session.getAttribute("rowCountUser"));
				} else {
					session.setAttribute("firstRowUser", firstrowUser);
					session.setAttribute("rowCountUser", rowcountUser);
					System.out.println("Size = " + sizeTweetsListUser
							+ " In Next 3 ELSE paginateTweetsUser = "
							+ (Integer) session.getAttribute("firstRowUser")
							+ " "
							+ (Integer) session.getAttribute("rowCountUser"));
				}

			} else if (sizeTweetsListUser % numberOfTweetsOnPageUser != 0
					&& rowcountUser >= numberOfTweetsOnPageUser
					&& (sizeTweetsListUser - rowcountUser) > numberOfTweetsOnPageUser) {
				firstrowUser += numberOfTweetsOnPageUser;
				rowcountUser += numberOfTweetsOnPageUser;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("Size = " + sizeTweetsListUser
						+ " In Next 4 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));

			} else if (sizeTweetsListUser % numberOfTweetsOnPageUser != 0
					&& (rowcountUser < numberOfTweetsOnPageUser)) {
				Integer lastTweets = sizeTweetsListUser
						% numberOfTweetsOnPageUser;
				firstrowUser = 0;
				rowcountUser = lastTweets;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("Size = " + sizeTweetsListUser
						+ " In Next 5 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));
			}
		}

		if (pageUser.equals("Previous")) {
			Integer sizeTweetsList = (Integer) session
					.getAttribute("sizeUserTweetsUser");
			if (sizeTweetsList % numberOfTweetsOnPageUser != 0
					&& (firstrowUser >= numberOfTweetsOnPageUser && (rowcountUser
							% numberOfTweetsOnPageUser != 0))) {
				Integer lastTweets = sizeTweetsList % numberOfTweetsOnPageUser;
				firstrowUser -= numberOfTweetsOnPageUser;
				rowcountUser -= lastTweets;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 1 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));

			} else if (sizeTweetsList % numberOfTweetsOnPageUser != 0
					&& (firstrowUser >= numberOfTweetsOnPageUser && (rowcountUser
							% numberOfTweetsOnPageUser == 0))) {

				firstrowUser -= numberOfTweetsOnPageUser;
				rowcountUser -= numberOfTweetsOnPageUser;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 1_2 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));

			} else if (sizeTweetsList % numberOfTweetsOnPageUser != 0
					&& (firstrowUser < numberOfTweetsOnPageUser && sizeTweetsList >= numberOfTweetsOnPageUser)) {

				firstrowUser = 0;
				rowcountUser = numberOfTweetsOnPageUser;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 1_3 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));

			} else if (sizeTweetsList % numberOfTweetsOnPageUser != 0
					&& (firstrowUser < numberOfTweetsOnPageUser && rowcountUser < numberOfTweetsOnPageUser)) {
				Integer lastTweets = sizeTweetsList % numberOfTweetsOnPageUser;
				firstrowUser = 0;
				rowcountUser = lastTweets;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 2 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));

			} else if ((firstrowUser >= numberOfTweetsOnPageUser && rowcountUser > numberOfTweetsOnPageUser)
					&& sizeTweetsList % numberOfTweetsOnPageUser == 0) {
				firstrowUser -= numberOfTweetsOnPageUser;
				rowcountUser -= numberOfTweetsOnPageUser;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 3 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));
			} else if ((firstrowUser < numberOfTweetsOnPageUser && sizeTweetsList < numberOfTweetsOnPageUser)
					&& sizeTweetsList % numberOfTweetsOnPageUser == 0) {
				Integer lastTweets = sizeTweetsList % numberOfTweetsOnPageUser;
				firstrowUser = 0;
				rowcountUser = lastTweets;
				session.setAttribute("firstRowUser", firstrowUser);
				session.setAttribute("rowCountUser", rowcountUser);
				System.out.println("In Previous 4 paginateTweetsUser= "
						+ (Integer) session.getAttribute("firstRowUser") + " "
						+ (Integer) session.getAttribute("rowCountUser"));
			}
		}
		session.removeAttribute("sizeUserTweetsUser");

		return "redirect:/personTweets";
	}
}
