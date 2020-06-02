package CRUD3.CRUD3.controller.Auth;

import CRUD3.CRUD3.dto.AdminUserDto;
import CRUD3.CRUD3.dto.AuthenticationRequestDto;
import CRUD3.CRUD3.dto.Oauth2;
import CRUD3.CRUD3.dto.RegistrationRequestDto;
import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;
import CRUD3.CRUD3.repository.MyUserRepository;
import CRUD3.CRUD3.security.jwt.JwtTokenProvider;
import CRUD3.CRUD3.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

@RestController
@RequestMapping(value = "/api/v1/auth/")
//@RequestMapping(value = "/login")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    private MyUserRepository userRepository;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @GetMapping("/log")
    public ResponseEntity login() {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", "us");
        response.put("token", "tok");

        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            MyUser user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, (List<Role>) user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("roles", user.getRolesStr());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/login_oauth2")
    public ResponseEntity loginByOauth2(@RequestBody Oauth2 oauth2) {
        boolean tt = userRepository.existsById(oauth2.getId());
        MyUser user = userRepository.findById(oauth2.getId()).orElseGet(() -> {
            MyUser newUser = new MyUser();
            // надо как то задать айдишник
            newUser.setId(oauth2.getId());
            newUser.setUserName(oauth2.getName());
            newUser.setFirstName(oauth2.getFirstName());
            newUser.setLastName(oauth2.getLastName());
            newUser.setEmail(oauth2.getEmail());
            newUser.setPicture(oauth2.getPhotoUrl());
            newUser.setOrders(new ArrayList<>());
          //  newUser.setBacket(new Backet());
            newUser = userService.register(newUser);
            return newUser;
        });

        String token = jwtTokenProvider.createToken(user.getEmail(), (List<Role>) user.getRoles());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", user.getEmail());
        response.put("token", token);
        response.put("roles", user.getRolesStr());

        return ResponseEntity.ok(response);
    }

    @PostMapping("registration")
    public ResponseEntity registration(@RequestBody RegistrationRequestDto requestDto) {
        try {
            String login = requestDto.getEmail();//email

            if (userRepository.findByUserName(login) != null) {
                throw new UsernameNotFoundException("User with email: " + login + " already");
            }

            MyUser user = new MyUser();
            user.setUserName(requestDto.getUserName());
            user.setFirstName(requestDto.getFirstName());
            user.setLastName(requestDto.getLastName());
            user.setEmail(requestDto.getEmail());
            user.setPicture(requestDto.getPicture());
            user.setOrders(new ArrayList<>());
            user.setPassword(requestDto.getPassword());
            user = userService.register(user);//шифруется тут

//            userRepository.save(user);

            String token = jwtTokenProvider.createToken(login, (List<Role>) user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", login);
            response.put("roles", user.getRolesStr());
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = "set_admin/{id}")
    public ResponseEntity<AdminUserDto> setAdminRole(@PathVariable(name = "id") String id) {
        MyUser user = userService.findByUsername(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        user = userService.setUserRole(user, "ROLE_ADMIN");
        userRepository.save(user);
        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    //http://localhost:9090/login/oauth2
//    @PostMapping("oauth2")
////    @Bean
//    public ResponseEntity principalExtractor(RoleRepository roleRepository, PrincipalExtractor principalExtractor) {
////        int i = 10;
//        MyUser users;
//        principalExtractor = map1 -> {
//            String id = (String) map1.get("sub");
//
//            MyUser user = userRepository.findById(id).orElseGet(() -> {
//                MyUser newUser = new MyUser();
//
////из за автоматической генерации айдишника в юзере not set google id
//                // поле с email сделать уникальным, а то какова хуйа
//
//                newUser.setId(id);
//                newUser.setUserName((String) map1.get("name"));
//                newUser.setFirstName((String) map1.get("given_name"));
//                newUser.setLastName((String) map1.get("family_name"));
//                newUser.setPicture((String) map1.get("picture"));
//                newUser.setEmail((String) map1.get("email"));
//                newUser.setOrders(new ArrayList<>());
//                newUser.setBacket(new Backet(newUser, new ArrayList<>()));
//
//                Role roleUser = roleRepository.findByName("ROLE_USER");
//                List<Role> userRoles = new ArrayList<>();
//                userRoles.add(roleUser);
//
//                newUser.setRoles(userRoles);
////                newUser.setGender((String) map.get("gender"));
////                newUser.setLocale((String) map.get("locale"));
//
//                return newUser;
//            });
//            return userRepository.save(user);
//        };
//        Map<Object, Object> response = new HashMap<>();
////            Map<Object, Object> response = new HashMap<>();
////            response.put("login", login);
////            response.put("token", token);
//
////            return new HashMap<>();
////            return map -> {
////                String id = (String) map.get("sub");
////
////                MyUser user = userRepository.findById(id).orElseGet(() -> {
////                    MyUser newUser = new MyUser();
////
//////из за автоматической генерации айдишника в юзере not set google id
////                    // поле с email сделать уникальным, а то какова хуйа
////
////                    newUser.setId(id);
////                    newUser.setUserName((String) map.get("name"));
////                    newUser.setFirstName((String) map.get("given_name"));
////                    newUser.setLastName((String) map.get("family_name"));
////                    newUser.setPicture((String) map.get("picture"));
////                    newUser.setEmail((String) map.get("email"));
////                    newUser.setOrders(new ArrayList<>());
////                    newUser.setBacket(new Backet(newUser, new ArrayList<>()));
////
////                    Role roleUser = roleRepository.findByName("ROLE_USER");
////                    List<Role> userRoles = new ArrayList<>();
////                    userRoles.add(roleUser);
////
////                    newUser.setRoles(userRoles);
//////                newUser.setGender((String) map.get("gender"));
//////                newUser.setLocale((String) map.get("locale"));
////
////                    return newUser;
////                });
////
//////            user.setLastVisit(LocalDateTime.now());
////
////                return userRepository.save(user);
////            };
//        return ResponseEntity.ok(response);
//    }


}
