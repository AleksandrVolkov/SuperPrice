package CRUD3.CRUD3.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;



public class JwtUser implements UserDetails {

    private final String id;
    private final String username;
    private final String city;
    private final String firstName;
    private final String lastName;
    private final String password;
//    private final boolean enabled;
//    private final boolean banned;
//    private final Date lastPasswordResetDate;
    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(
            String id,
            String username,
            String city,
            String firstName,
//            String email,
            String lastName,
            Collection<? extends GrantedAuthority> authorities,
            String password

//            boolean enabled,
//            boolean banned,
//            Date lastPasswordResetDate
    ) {
        this.id = id;
        this.username = username;
        this.city = city;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authorities = authorities;
        this.password = password;
//        this.enabled = enabled;
//        this.banned = banned;
//        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getCity() {
        return city;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    //    public String getFirstname() {
//        return city;
//    }

//    public String getLastname() {
//        return lastName;
//    }
//
//    public String getEmail() {
//        return email;
//    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    @JsonIgnore
//    public Date getLastPasswordResetDate() {
//        return lastPasswordResetDate;
//    }
}
