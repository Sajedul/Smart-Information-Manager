package com.infomanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.infomanager.entities.User;
import com.infomanager.helpers.Helper;
import com.infomanager.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    
    //Insted of calling this method from controller we can call it from root controller that will 
    //allow us to show this message in every other pages
    
    
	/*
	 * @ModelAttribute public void addLoggedInUserInformation(Model model,
	 * Authentication authentication) {
	 * 
	 * String username=Helper.getEmailOfLoggedInUser(authentication);
	 * logger.info("User Logged in:{}", username);
	 * 
	 * //fetch data form database
	 * 
	 * User user = userService.getUserByEmail(username);
	 * 
	 * System.out.println(user.getName()); System.out.println(user.getEmail());
	 * 
	 * model.addAttribute("loggedInUser", user); }
	 */
       
    
    // user dashboard

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
        
    }
    
    @RequestMapping(value = "/profile")
    public String userProfile(Model model, Authentication authentication) {

		/*
		 * String username=Helper.getEmailOfLoggedInUser(authentication);
		 * logger.info("User Logged in:{}", username);
		 * 
		 * //fetch data form database
		 * 
		 * User user = userService.getUserByEmail(username);
		 * 
		 * System.out.println(user.getName()); System.out.println(user.getEmail());
		 * 
		 * model.addAttribute("loggedInUser", user);
		 * 
		 * System.out.println("User profile"+user);
		 */
        return "user/profile";
    }

}
