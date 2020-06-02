package CRUD3.CRUD3.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class MyUser {
    //    @GenericGenerator(name="system-uuid", strategy = "uuid")
//    @GeneratedValue(generator="system-uuid")
    @Id
    public String id;

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long idGeneration;

    public String userName;
    public String firstName;
    public String lastName;
    public String picture;
    public String email;

    @Column(name = "password")
    private String password;

//    @JsonIgnore
//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "BACKET_ID")
//    private Backet backet;


    @OneToMany(mappedBy = "myUser", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<MyOrder> orders;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Collection<Role> roles;


    public List<String> getRolesStr() {
        List<String> list = new ArrayList<>();
        roles.forEach(role -> list.add(role.getName()));
        return list;
    }



    @Override
    public String toString() {
        return "id" + getId();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }


}

////@Entity
//@Data
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    public String id;
//
//    public String userName;
//    public String firstName;
//    public String lastName;
//    public String picture;
//    public String email;
//
//    // @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
//    // public ArrayList<Order> orders;
//}
