package pro.abacus.webRestProject.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.repositories.UserRepository;

public class UserDetailsServiceTest {
	private static final String ANY_USER_NAME = "Anton";
	
	private UserRepository userRepository = mock(UserRepository.class);
	
	private User user = mock(User.class);
	
	UserDetailsService userDetailService = new UserDetailsServiceImpl(userRepository);
	
	@Test (expected = UsernameNotFoundException.class)
	public void shouldThrowUsernameNotFoundException(){
		when(userRepository.findByName(ANY_USER_NAME)).thenReturn(null);
	
		userDetailService.loadUserByUsername(ANY_USER_NAME);
	}
	
	@Test
	public void shouldReturnInstanceWebUserDetails(){
		when(userRepository.findByName(ANY_USER_NAME)).thenReturn(user);
		UserDetails userDetails =userDetailService.loadUserByUsername(ANY_USER_NAME);
		
		assertNotNull(userDetails);
	}

}
