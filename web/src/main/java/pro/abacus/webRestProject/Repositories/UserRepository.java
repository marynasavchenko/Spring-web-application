package pro.abacus.webRestProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.abacus.webRestProject.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public Boolean existsByName(String name);

	public Boolean existsByPassword(String password);

	User findByName(String username);
}
