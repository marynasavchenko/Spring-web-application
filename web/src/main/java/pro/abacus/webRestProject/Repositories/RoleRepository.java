package pro.abacus.webRestProject.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import pro.abacus.webRestProject.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
