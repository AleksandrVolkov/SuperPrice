package CRUD3.CRUD3.services;

import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;

import java.util.List;


public interface UserService {
    List<MyUser> getAll();

    MyUser register(MyUser user);

    MyUser findByUsername(String username);

    MyUser findById(String id);

    List<MyUser> findByRole(Role role);

    void delete(String id);

    public MyUser removeUserRole(MyUser user, String roleName);

    public MyUser setUserRole(MyUser user, String roleName);
}
