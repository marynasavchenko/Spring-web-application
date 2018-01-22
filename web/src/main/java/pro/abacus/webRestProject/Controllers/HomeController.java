package pro.abacus.webRestProject.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pro.abacus.webRestProject.Repositories.UserRepository;
import pro.abacus.webRestProject.Services.UserService;
import pro.abacus.webRestProject.models.User;

@Controller
@RequestMapping(path = "/")
public class HomeController {

	@Autowired
	private UserService userService;
	
	/*@Autowired
	private UserRepository userRepository;*/

	@GetMapping("/registration")
	public String showRegistrationForm(Model model, User user) {
		return "registration";
	}

	// handle post request with validation
	@PostMapping("/registration")
	public String processRegistrationForm(@Valid User user, Errors errors) {

		if (errors.hasErrors()) {
			return "registration";
		}
        if (userService.isDuplicate(user)){
        	errors.rejectValue("name","name.duplicate", "User with this username already exists");
        	return "registration";
		}
        else{
        	userService.save(user);
        	// add logger
    		return "login";
        }	
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "login";
	}

	@PostMapping("/login")
	public @ResponseBody String processLoginForm(Model model, String error) {
		// add logger
		return "login";
	}

}
