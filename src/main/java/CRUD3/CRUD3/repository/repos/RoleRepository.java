package CRUD3.CRUD3.repository.repos;

import CRUD3.CRUD3.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
