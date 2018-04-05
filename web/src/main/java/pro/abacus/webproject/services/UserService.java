package pro.abacus.webproject.services;

import pro.abacus.webproject.models.User;

public interface UserService {
	
	void save(User user);
	User findByUsername(String username);
	boolean isDuplicate (User user);

}
