package CRUD3.CRUD3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

/**
 * Simple domain object that represents application user's role - ADMIN, USER, etc.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@Entity
@Table(name = "roles")
@Data
public class Role  {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Collection<MyUser> users;


}
