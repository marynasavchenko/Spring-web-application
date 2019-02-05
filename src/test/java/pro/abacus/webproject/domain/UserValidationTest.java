package pro.abacus.webproject.domain;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.hibernate.validator.HibernateValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnitParamsRunner.class)
public class UserValidationTest {

	private LocalValidatorFactoryBean validatorFactory;

	@Before
	public void setup() {
		validatorFactory = new LocalValidatorFactoryBean();
		validatorFactory.setProviderClass(HibernateValidator.class);
		validatorFactory.afterPropertiesSet();
	}

	private static final Object[] getInvalidUserNameEmailPassword() {
		return new Object[]{
				new Object[]{"", "", ""},
				new Object[]{"", "test@gmail.com", "7894561223"},
				new Object[]{"p", "test@gmail.com", "7894561223"},
				new Object[]{"31symbols123456789qwertyuiop[]a", "test@gmail.com", "7894561223"},
				new Object[]{"name", "test", "7894561223"},
				new Object[]{"alex", "test@gmail.com", "qwe"},

		};
	}

	private static final Object[] getValidUserNameEmailPassword() {
		return new Object[]{
				new Object[]{"tonia", "tonia89@gmail.com", "12345678"},
				new Object[]{"to", "tonia89@gmail.com", "12345678"},
				new Object[]{"30symbols123456789qwertyuiop[]", "tonia89@gmail.com", "12345678"}

		};
	}

	@Test
	@Parameters(method = "getInvalidUserNameEmailPassword")
	public void shouldNotValidateUser(String invalidName, String invalidEmail, String invalidPassword) {
		User user = new User();
		user.setName(invalidName);
		user.setEmail(invalidEmail);
		user.setPassword(invalidPassword);

		Set<ConstraintViolation<User>> violations = validatorFactory.validate(user);

		assertFalse(violations.isEmpty());
	}

	@Test
	@Parameters(method = "getValidUserNameEmailPassword")
	public void shouldValidateUser(String validName, String validEmail, String validPassword) {
		User user = new User();
		user.setName(validName);
		user.setEmail(validEmail);
		user.setPassword(validPassword);

		Set<ConstraintViolation<User>> violations = validatorFactory.validate(user);

		assertTrue(violations.isEmpty());
	}
}
