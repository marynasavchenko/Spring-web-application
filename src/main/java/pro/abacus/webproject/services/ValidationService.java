package pro.abacus.webproject.services;

import org.springframework.validation.Errors;

import pro.abacus.webproject.domain.User;

public interface ValidationService {
	boolean validate(User user, Errors errors);
}
