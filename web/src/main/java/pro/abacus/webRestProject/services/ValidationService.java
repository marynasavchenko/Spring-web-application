package pro.abacus.webRestProject.services;

import org.springframework.validation.Errors;

import pro.abacus.webRestProject.models.User;

public interface ValidationService {
	
	public boolean validate(User user, Errors errors);

}
