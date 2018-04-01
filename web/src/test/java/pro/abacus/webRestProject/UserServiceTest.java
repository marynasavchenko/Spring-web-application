package pro.abacus.webRestProject;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import pro.abacus.webRestProject.repositories.UserRepository;
import pro.abacus.webRestProject.services.UserService;
import pro.abacus.webRestProject.services.UserServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void setup(){
	userService = new UserServiceImplementation(null, null, null);
	}
	

}
