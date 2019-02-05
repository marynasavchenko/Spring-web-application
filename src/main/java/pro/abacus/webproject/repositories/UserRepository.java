package pro.abacus.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.abacus.webproject.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Boolean existsByName(String name);

	Boolean existsByPassword(String password);

	User findByName(String username);
}
