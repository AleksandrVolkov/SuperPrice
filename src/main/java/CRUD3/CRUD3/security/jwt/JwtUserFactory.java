package CRUD3.CRUD3.security.jwt;

import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(MyUser user) {
        return new JwtUser(
                user.getId(),
                user.getUserName(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getPassword()
//                user.getStatus().equals(Status.ACTIVE),
//                user.getStatus().equals(Status.BANNED),
//                user.getUpdated()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
