package CRUD3.CRUD3.controller;


import CRUD3.CRUD3.model.MyOrder;
import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.repository.MyUserRepository;
import CRUD3.CRUD3.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {


    MyUserRepository repository;


    JwtTokenProvider jwtAuthenticationProvider;

    @Autowired
    public UserController(JwtTokenProvider jwtAuthenticationProvider,
                          MyUserRepository userRepositoryry
    ) {
        this.repository = userRepositoryry;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;

    }
//    @Autowired
//    MyUserRepository userRepository;
//
//    @GetMapping("/cabinet")
//    @ResponseBody
//    public MyUser getUser(HttpServletRequest httpRequest) throws VerificationException {
//
//        String s = httpRequest.getHeader("Authorization").replace("Bearer ", "");
//        AccessToken token = RSATokenVerifier.create(s).getToken();
//
//        System.out.printf("sub = %s%n", token.getSubject());
//
//
//        MyUser user = new MyUser();
//        user.setId(token.getId());
//        user.setFirstName(token.getGivenName());
//        user.setLastName(token.getFamilyName());
//        user.setUserName(token.getPreferredUsername());
//        user.setEmail(token.getEmail());
//        user.setPicture(token.getPicture());
//        return user;
//    }
//
//    @GetMapping("/users/{date}")
//    @ResponseBody
//    public List<MyUser> getUsers(@PathVariable(value = "date") String field) {
//      return repository.findAll();
//
//    }

//    @RequestMapping("/getEmployeesOrderBy/{name}")
//    public List<Employee> getEmployeesOrderBy(@PathVariable(value = "name") String field) {
//        //String authHeader = request.getHeader("Order");
//       // return employeeService.getEmployeeOrderByName(field);
//    }

    @GetMapping("/cabinet")
    //  @ResponseBody
    public MyUser getUser(HttpServletRequest req) {
        String username = jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));

        MyUser byEmail = repository.findByEmail(username);
//        byEmail.setOrders(byEmail.getOrders().stream().sorted(Comparator.comparing(MyOrder::getOrderDate).reversed()).collect(Collectors.toList()));
        Collection<MyOrder> orders = byEmail.getOrders();
        Collections.reverse((List<?>) orders);
        byEmail.setOrders(orders);
        return byEmail;

    }

    @PutMapping("/edit")
    public MyUser editUser(@RequestBody MyUser user) {


        //  String username=jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));
//        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
//        Jwt token = jwtAuth.getToken();
        var u = repository.findById(user.getId()).get();
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setUserName(user.getUserName());
        u.setPicture(user.getPicture());
        // u.setFirstName(user.getFirstName());

        return repository.save(u);
//        user.setEmail(token.getSubject());

    }
}
