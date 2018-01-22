package pro.abacus.webRestProject.Services;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pro.abacus.webRestProject.Repositories.*;
import pro.abacus.webRestProject.models.User;

@Service
public class UserServiceImplementation implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new HashSet<>(roleRepository.findAll()));
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {

		return userRepository.findByName(username);
	}

	@Override
	public boolean isDuplicate(User user) {
		
		if(userRepository.existsByName(user.getName())){
			return true;
		}
		return false;
	}

}
