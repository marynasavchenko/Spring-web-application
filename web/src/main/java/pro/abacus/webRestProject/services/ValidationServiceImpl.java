package pro.abacus.webRestProject.services;

import org.springframework.validation.Errors;

import pro.abacus.webRestProject.models.User;

public class ValidationServiceImpl implements ValidationService {
	
	private User user;
	
	private Error error;
	
	public ValidationServiceImpl(User user, Error error){
		this.user = user;
		this.error = error;
	}

	@Override
	public boolean validate(User user, Errors errors) {
		// TODO Auto-generated method stub
		return false;
	}

}
