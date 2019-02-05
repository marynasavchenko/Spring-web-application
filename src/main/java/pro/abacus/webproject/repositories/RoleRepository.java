package pro.abacus.webproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.abacus.webproject.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
