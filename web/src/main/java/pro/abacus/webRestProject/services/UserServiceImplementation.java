package pro.abacus.webRestProject.services;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pro.abacus.webRestProject.models.User;
import pro.abacus.webRestProject.repositories.*;

@Service
public class UserServiceImplementation implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public UserServiceImplementation(UserRepository userRepository,RoleRepository roleRepository, BCryptPasswordEncoder encoder){
		this.userRepository=userRepository;
		this.roleRepository=roleRepository;
		this.bCryptPasswordEncoder=encoder;
	}

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
