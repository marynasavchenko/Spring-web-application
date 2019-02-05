package pro.abacus.webproject.services;

import org.junit.Test;
import org.springframework.validation.Errors;
import pro.abacus.webproject.domain.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


public class ValidationServiceTest {

	private User user = mock(User.class);

	private UserService userService = mock(UserService.class);

	private Errors errors = mock(Errors.class);

	private ValidationService validationService = new ValidationServiceImpl(userService);

	@Test
	public void shouldReturnFalseIfThereIsErrors() throws Exception {
		when(errors.hasErrors()).thenReturn(true);

		assertFalse(validationService.validate(user, errors));
	}

	@Test
	public void shouldValidateIfThereIsNoErrors() {
		when(errors.hasErrors()).thenReturn(false);

		assertTrue(validationService.validate(user, errors));
	}

	@Test
	public void shouldReturnFalseIfUserIsDuplicated() {
		when(userService.isDuplicate(user)).thenReturn(true);

		assertFalse(validationService.validate(user, errors));
		verify(userService).isDuplicate(user);
	}
}
