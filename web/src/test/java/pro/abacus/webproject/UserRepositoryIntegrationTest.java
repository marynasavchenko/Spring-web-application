package pro.abacus.webproject;

import static org.junit.Assert.*;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pro.abacus.webproject.models.User;
import pro.abacus.webproject.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException exception =ExpectedException.none();
	
	@Test
	public void createWhenUsernameIsNullShouldThrowException() throws Exception {
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Username must not be empty");
		new User(null);
	}

	@Test
	public void saveShouldPersistData() throws Exception {

		User savedUser = entityManager.persistFlushFind(new User("test21", "test21@gmail.com", "password21"));

		assertEquals("test21",savedUser.getName());
		assertEquals("test21",savedUser.getEmail());
		assertEquals("test21",savedUser.getPassword());
	}
	
	
}
