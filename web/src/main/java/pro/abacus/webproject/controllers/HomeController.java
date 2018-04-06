package pro.abacus.webproject.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import pro.abacus.webproject.domain.User;
import pro.abacus.webproject.services.UserService;
import pro.abacus.webproject.services.ValidationService;

@Controller
@RequestMapping(path = "/")
public class HomeController {
	
	final static Logger log = LoggerFactory.getLogger(HomeController.class);

	private UserService userService;
	
	private ValidationService validationService;
	
	@Autowired
	public HomeController(UserService userService, ValidationService validationService){
		this.userService=userService;	
		this.validationService=validationService;
	}

	@GetMapping("/registration")
	public String showRegistrationForm(Model model, User user) {
		return "registration";
	}

	@PostMapping("/registration")
	public String processRegistrationForm(@Valid User user, Errors errors) {
		
		if (validationService.validate(user, errors)){
			userService.save(user);
        	log.info("User saved: " + user);
    		return "login";
		}
		else {
			log.info("Processing user registration: " + user);
			return "registration";
		}
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public @ResponseBody String processLoginForm(Model model, String error) {
		return "login";
	}

}
