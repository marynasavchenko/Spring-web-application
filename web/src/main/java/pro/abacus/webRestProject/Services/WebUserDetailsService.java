package pro.abacus.webRestProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import pro.abacus.webRestProject.Repositories.UserRepository;
import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.models.WebUserDetails;

@Service
public class WebUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByName(username);

		/*
		 * Return the User (WebUserDetails) value, if present, otherwise throw an
		 * UsernameNotFoundException
		 */
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new WebUserDetails(user);

	}

}
