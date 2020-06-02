package CRUD3.CRUD3.controller.Auth;

import CRUD3.CRUD3.dto.AdminUserDto;
import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.repository.MyUserRepository;
import CRUD3.CRUD3.services.UserService;
import CRUD3.CRUD3.services.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {


    private final UserService userService;

    private final MyUserRepository userRepository;

    private final RoleService roleService;

    @Autowired
    public AdminRestControllerV1(UserService userService, MyUserRepository userRepository, RoleService roleService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @GetMapping(value = "get_users")
    public ResponseEntity<List<AdminUserDto>> getUsers() {
        Iterable<MyUser> users = userService.findByRole(roleService.findByName("ROLE_USER"));
        if (users == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<AdminUserDto> result = AdminUserDto.fromUsers((List<MyUser>) users);
        result.removeIf(p -> p.getRoles().contains("ROLE_ADMIN"));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get_all_users")
    public ResponseEntity<List<AdminUserDto>> getAllUsers() {
        Iterable<MyUser> users = userService.findByRole(roleService.findByName("ROLE_USER"));
        if (users == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<AdminUserDto> result = AdminUserDto.fromUsers((List<MyUser>) users);
        result.forEach(p ->
                p.setRoles(p.getRoles().replace("ROLE_", "").trim().replace(" ", ", "))
        );
        result.sort(Comparator.comparing(adminUserDto -> !adminUserDto.getRoles().contains("ADMIN")));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get_admins")
    public ResponseEntity<List<AdminUserDto>> getAdmins() {
        Iterable<MyUser> users = userService.findByRole(roleService.findByName("ROLE_ADMIN"));
        if (users == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<AdminUserDto> result = AdminUserDto.fromUsers((List<MyUser>) users);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "get_user/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") String id) {
        MyUser user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @GetMapping(value = "set_moder/{id}")
    //    public ResponseEntity<AdminUserDto> setModerRole(@PathVariable(name = "id") String id) {
    //        MyUser user = userService.findById(id);
    //        if (user == null)
    //            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //
    //        user = userService.setUserRole(user, "ROLE_MODER");
    //        userRepository.save(user);
    //        AdminUserDto result = AdminUserDto.fromUser(user);
    //        return new ResponseEntity<>(result, HttpStatus.OK);
    //    }

    @GetMapping(value = "set_admin/{id}")
    public ResponseEntity<AdminUserDto> setAdminRole(@PathVariable(name = "id") String id) {
        MyUser user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        user = userService.setUserRole(user, "ROLE_ADMIN");
        userRepository.save(user);
        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "remove_admin/{id}")
    public ResponseEntity<AdminUserDto> removeAdminRole(@PathVariable(name = "id") String id) {
        MyUser user = userService.findById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        user = userService.removeUserRole(user, "ROLE_ADMIN");
        userRepository.save(user);
        AdminUserDto result = AdminUserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //    @GetMapping(value = "remove_moder/{id}")
    //    public ResponseEntity<AdminUserDto> removeModerRole(@PathVariable(name = "id") String id) {
    //        MyUser user = userService.findById(id);
    //        if (user == null)
    //            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //        user = userService.removeUserRole(user, "ROLE_MODER");
    //        userRepository.save(user);
    //        AdminUserDto result = AdminUserDto.fromUser(user);
    //        return new ResponseEntity<>(result, HttpStatus.OK);
    //    }

}

