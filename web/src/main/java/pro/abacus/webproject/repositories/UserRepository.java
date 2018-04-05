package pro.abacus.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.abacus.webproject.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Boolean existsByName(String name);

	public Boolean existsByPassword(String password);

	User findByName(String username);
	
}
