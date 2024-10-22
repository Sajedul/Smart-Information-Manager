package com.infomanager.controller;


//import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.infomanager.entities.User;
import com.infomanager.forms.UserForm;
import com.infomanager.helpers.Message;
import com.infomanager.helpers.MessageType;
import com.infomanager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "redirect:/home";
	}

	@RequestMapping("/home")
	public String home(Model model) {
		System.out.println("Home page is runnaning..");

		model.addAttribute("name", "TailwindCSS Practice");
		model.addAttribute("youtube", "This message is for youtube");
		model.addAttribute("github", "This message is for Github");
		model.addAttribute("githubRepo", "This message is Github repo link");

		return "home";
	}

	// About Page
	@RequestMapping("/about")
	public String aboutPage(Model m) {
		System.out.println("About page loading");
		/* m.addAttribute("islogin", false); */
		return "about";
	}

	// Service Page
	@RequestMapping("/services")
	public String servicesPage() {
		System.out.println("About page loading");
		return "services";
	}

	@GetMapping("/contact")
	public String contact() {
		// return new String("contact");
		return "contact";
	}

	// showing login page
	@GetMapping("/login")
	public String login() {
		return new String("login");
	}

	// registration page
	@GetMapping("/register")
	public String register(Model model) {

		UserForm userForm = new UserForm();

		// userForm.setAbout("This is about : Write something about yourself");
		model.addAttribute("userForm", userForm);

		return "register";
	}

	// processing register
	@RequestMapping(value = "/do-register", method = RequestMethod.POST)
	public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult rBindingResult,
			HttpSession session) {
		System.out.println("Processing registration");
		// fetch form data
		// UserForm
		System.out.println(userForm);

		// validate form data
		if (rBindingResult.hasErrors()) {
			return "register";
		}

		

		// userservice

		// UserForm--> User
		// User user = User.builder()
		// .name(userForm.getName())
		// .email(userForm.getEmail())
		// .password(userForm.getPassword())
		// .about(userForm.getAbout())
		// .phoneNumber(userForm.getPhoneNumber())
		// .profilePic(
		// "https://icons8.com/icons/set/default-profile-picture")
		// .build();

		User user = new User();
		user.setName(userForm.getName());
		user.setEmail(userForm.getEmail());
		user.setPassword(userForm.getPassword());
		user.setAbout(userForm.getAbout());
		user.setPhoneNumber(userForm.getPhoneNumber());
		user.setEnabled(false);
		user.setProfilePic("https://icons8.com/icons/set/default-profile-picture");

		User savedUser = userService.saveUser(user);
		System.out.println("user saved :");

		// add the message:
		
		// String message = "Registration Successful";
		// Message message = Message.builder().content("Registration
		// Successful").type(MessageType.green).build();
		
		Message message = new Message("Registration Successful", MessageType.green);
		session.setAttribute("message", message);

		return "redirect:/register";

	}
	
}
