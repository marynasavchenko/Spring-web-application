package pro.abacus.webproject.repositories;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import pro.abacus.webproject.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@After
	public void tearDown() throws Exception {
		userRepository.deleteAll();
	}

	@Test
	public void findByNameShouldReturnUser() throws Exception {
		this.entityManager.persist(new User("Anton", "anton@gmail.com", "password21"));
		User user = this.userRepository.findByName("Anton");
		assertEquals(user.getName(), "Anton");
		assertEquals(user.getEmail(), "anton@gmail.com");
		assertEquals(user.getPassword(), "password21");
	}

	@Test
	public void findByNameWhenNoUserShouldReturnNull() throws Exception {
		this.entityManager.persist(new User("Anton", "anton@gmail.com", "password21"));
		User user = this.userRepository.findByName("Joe");
		assertNull(user);
	}
}
