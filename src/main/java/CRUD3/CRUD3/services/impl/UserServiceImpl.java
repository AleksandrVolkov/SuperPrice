package CRUD3.CRUD3.services.impl;

import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;
import CRUD3.CRUD3.repository.MyUserRepository;
import CRUD3.CRUD3.repository.repos.RoleRepository;
import CRUD3.CRUD3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Service
//@Slf4j
public class UserServiceImpl implements UserService {

    private final MyUserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(MyUserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public MyUser setUserRole(MyUser user, String roleName) {
        Collection<Role> userRoles = user.getRoles();
        if (userRoles.size() < 2)
            userRoles.add(roleRepository.findByName(roleName));
        else {
            boolean flag = false;
            for (Role role : userRoles) {
                if (role.getName().equals(roleName))
                    flag = true;
            }
            if (!flag)
                userRoles.add(roleRepository.findByName(roleName));
        }
        user.setRoles(userRoles);
        return user;
    }

    @Override
    public MyUser removeUserRole(MyUser user, String roleName) {
        Collection<Role> userRoles = user.getRoles();

        userRoles.removeIf(role -> role.getName().equals(roleName));

        user.setRoles(userRoles);
        return user;
    }

    @Override
    public MyUser register(MyUser user) {
//        Role roleUser = roleRepository.findByName("ROLE_USER");
//        List<Role> userRoles = new ArrayList<>();
//        userRoles.add(roleUser);
//
//        user.setRoles(userRoles);
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        user.setBacket(new Backet(user, new ArrayList<>()));
//
////        String id = user.getIdGeneration().toString();
//
//        MyUser registeredUser = userRepository.save(user);
//
////        log.info("IN register - user: {} successfully registered", registeredUser);
//
//        return registeredUser;
//        roleRepository.deleteAll();

        Role r = new Role();
        r.setName("ROLE_USER");
        r.setId(1l);
        roleRepository.save(r);
        Role r2 = new Role();
        r2.setName("ROLE_ADMIN");
        r2.setId(2l);
        roleRepository.save(r2);

        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setRoles(userRoles);
        if (user.getPassword() != null)
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        //user.setBacket(new Backet(user, new ArrayList<>()));
        if (user.getId() == null) {
            String Id = UUID.randomUUID().toString();
            while (userRepository.existsById(Id))
                Id = UUID.randomUUID().toString();
            user.setId(Id);
        }
//        String id = user.getIdGeneration().toString();

        MyUser registeredUser = userRepository.save(user);

//        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    @Override
    public List<MyUser> getAll() {
        List<MyUser> result = userRepository.findAll();
//        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public MyUser findByUsername(String username) {
        var result = userRepository.findByEmail(username);
        // var s=result.stream().filter(x->x.getEmail()==username).findFirst();
//        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public List<MyUser> findByRole(Role role) {
        List<MyUser> result = new ArrayList<>();
        List<MyUser> list = userRepository.findAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRoles().contains(role)) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    @Override
    public MyUser findById(String id) {
        MyUser result = userRepository.findById(id).orElse(null);

        if (result == null) {
//            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

//        log.info("IN findById - user: {} found by id: {}", result);
        return result;
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
//        log.info("IN delete - user with id: {} successfully deleted");
    }
}
