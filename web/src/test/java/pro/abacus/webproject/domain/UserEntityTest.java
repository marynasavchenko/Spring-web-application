package pro.abacus.webproject.domain;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pro.abacus.webproject.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserEntityTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Rule
	public ExpectedException exception =ExpectedException.none();
	
	@Test
	public void createWhenNameIsNullShouldThrowException() throws Exception {
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Username must not be empty");
		new User(null, null, null);
	}
	
	@Test
	public void createWhenNameIsEmptyShouldThrowException() throws Exception {
		this.exception.expect(IllegalArgumentException.class);
		this.exception.expectMessage("Username must not be empty");
		new User("", null, null);
	}
	

	@Test
	public void saveShouldPersistData() throws Exception {

		User savedUser = entityManager.persistFlushFind(new User("test21", "test21@gmail.com", "password21"));

		assertEquals("test21",savedUser.getName());
		assertEquals("test21@gmail.com",savedUser.getEmail());
		assertEquals("password21",savedUser.getPassword());
	}
	
	
}
