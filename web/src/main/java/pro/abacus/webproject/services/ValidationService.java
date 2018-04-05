package pro.abacus.webproject.services;

import org.springframework.validation.Errors;

import pro.abacus.webproject.models.User;

public interface ValidationService {
	
	public boolean validate(User user, Errors errors);

}
