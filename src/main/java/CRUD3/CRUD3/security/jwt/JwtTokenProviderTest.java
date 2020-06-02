package CRUD3.CRUD3.security.jwt;

import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


class JwtTokenProviderTest {
    private String secret = "secret";

    private long validityInMilliseconds = 3600000;

    MyUser createUser() {
        MyUser user = new MyUser();
        user.setUserName("Olegich");
        user.setFirstName("Oleg");
        user.setLastName("Olegov");
        user.setEmail("Oleg@mail.ru");
        user.setOrders(new ArrayList<>());
        user.setPassword("pass");
        Role roleUser = new Role();
        roleUser.setId(1L);
        roleUser.setName("ROLE_USER");
        roleUser.setUsers(Collections.singletonList(user));
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setRoles(userRoles);
      //  user.setBacket(new Backet(user, new ArrayList<>()));
        return user;
    }

    String createTestToken(String username, List<Role> list) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", jwtTokenProvider.getRoleNames(list));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    boolean testValidateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        if (claims.getBody().getExpiration().before(new Date())) return false;
        return true;
    }

    public List<String> testGetRoleNames(List<Role> userRoles) {
        List<String> result = new ArrayList<>();

        userRoles.forEach(role -> {
            result.add(role.getName());
        });

        return result;
    }
//
//    @Test
//    void createToken() {
//        MyUser user = createUser();
//        String username = user.getUserName();
//        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
//        jwtTokenProvider.setSecret(secret);
//        jwtTokenProvider.setValidityInMilliseconds(validityInMilliseconds);
//        String testToken = createTestToken(username, (List<Role>) user.getRoles());
//        String codeToken = jwtTokenProvider.createToken(username, (List<Role>) user.getRoles());
//        Assert.assertEquals(testToken, codeToken);
//    }


    @Test
    void validateToken() {
        MyUser user = createUser();
        String username = user.getUserName();
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        jwtTokenProvider.setSecret(secret);
        jwtTokenProvider.setValidityInMilliseconds(validityInMilliseconds);
        String testToken = createTestToken(username, (List<Role>) user.getRoles());
        String codeToken = jwtTokenProvider.createToken(username, (List<Role>) user.getRoles());
        Assert.assertEquals(testValidateToken(testToken), jwtTokenProvider.validateToken(codeToken));
    }

    @Test
    void getRoleNames() {
        MyUser user = createUser();
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        Assert.assertEquals(testGetRoleNames((List<Role>) user.getRoles()),
                jwtTokenProvider.getRoleNames((List<Role>) user.getRoles()));

    }
}
