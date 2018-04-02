package pro.abacus.webRestProject;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.repositories.RoleRepository;
import pro.abacus.webRestProject.repositories.UserRepository;
import pro.abacus.webRestProject.services.UserService;
import pro.abacus.webRestProject.services.UserServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	private static final String UNSECURED_PASSWORD = "1234test";
	private static final String SECURED_PASSWORD = "1BC29B36F623BA82AAF6724FD3B16718";
    private static final String ANY_USER_NAME = "Anton";
	
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock 
	private BCryptPasswordEncoder encoder;
	
	@Mock
	private User user;
	
	
	@Before
	public void setup(){
	userService = new UserServiceImplementation(userRepository, roleRepository, encoder);
	}
	
	@Test
	public void shouldSaveUserWithSecuredPasswordAndRole() throws Exception{
		
		when(user.getPassword()).thenReturn(UNSECURED_PASSWORD);
		when(encoder.encode(UNSECURED_PASSWORD)).thenReturn(SECURED_PASSWORD);
		
		userService.save(user);
		verify(user).setRoles(new HashSet<>()); 
		verify(user).setPassword(SECURED_PASSWORD);
		verify(userRepository).save(user);
	}
	
	@Test
	public void shouldReturnTrueWhenUserAlreadyExists() throws Exception{
		when(user.getName()).thenReturn(ANY_USER_NAME);
		when(userRepository.existsByName(ANY_USER_NAME)).thenReturn(true);
		
		assertTrue(userService.isDuplicate(user));
		verify(userRepository).existsByName(ANY_USER_NAME);
		
	}
	
	@Test
	public void shouldInvokeFindByName(){
		
		userService.findByUsername(ANY_USER_NAME);
		verify(userRepository).findByName(ANY_USER_NAME);
	}
	
	 
}
