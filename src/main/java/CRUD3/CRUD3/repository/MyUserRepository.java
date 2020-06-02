package CRUD3.CRUD3.repository;

import CRUD3.CRUD3.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUser,String> {
    MyUser findByUserName(String name);
    MyUser findByEmail(String email);
}
