package pro.abacus.webproject.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import pro.abacus.webproject.models.User;

@Service
public class ValidationServiceImpl implements ValidationService {


	private UserService userService;

	public ValidationServiceImpl(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean validate(User user, Errors errors) {
		return !(errors.hasErrors()||rejectIfDuplicate(user, errors));		
	}
	
	public boolean rejectIfDuplicate(User user, Errors errors){
		if (userService.isDuplicate(user)){
			errors.rejectValue("name","name.duplicate", "User with this username already exists");
			
			return true;
		}
		return false;
	}
	
	

}
