package pro.abacus.webRestProject.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.validation.Errors;

import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.services.UserService;
import pro.abacus.webRestProject.services.ValidationService;
import pro.abacus.webRestProject.services.ValidationServiceImpl;

public class ValidationServiceTest {
	
	private User user = mock(User.class);
	
	private UserService userService = mock(UserService.class);
	
	
	private Errors errors = mock(Errors.class); 
	
	//create sut
	private ValidationService validationService = new ValidationServiceImpl(userService);
	
	//if there are errors return false 
	
	@Test
	public void shouldReturnFalseIfThereIsErrors() throws Exception{
		// add error
		when(errors.hasErrors()).thenReturn(true);
		
		assertFalse(validationService.validate(user, errors));
	}
	
	@Test
	public void shouldValidateIfThereIsNoErrors(){
		when(errors.hasErrors()).thenReturn(false);
		
		assertTrue(validationService.validate(user, errors));
	}
	
	@Test
	public void shouldReturnFalseIfUserIsDuplicated(){
		when(userService.isDuplicate(user)).thenReturn(true);
		
		assertFalse(validationService.validate(user, errors));
		verify(userService).isDuplicate(user);
	}
	

}
