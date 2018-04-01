package pro.abacus.webRestProject;

import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.repositories.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
// run tests against a real database
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class UserRepositoryIntegrationTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void shouldSaveAndReturnUser() throws Exception {

		User savedUser = entityManager.persistFlushFind(new User("test21", "test21@gmail.com", "password21"));
		User user = userRepository.findByName("test21");

		Assertions.assertThat(user.getName()).isEqualTo(savedUser.getName());
		Assertions.assertThat(user.getEmail()).isEqualTo(savedUser.getEmail());
		Assertions.assertThat(user.getPassword()).isEqualTo(savedUser.getPassword());

	}

}
