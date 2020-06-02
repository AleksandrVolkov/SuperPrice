package CRUD3.CRUD3.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="mark")
@Data
@AllArgsConstructor
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String userEmail;

    Long productId;

    public Mark(String userEmail,Long productId){
        this.userEmail=userEmail;
        this.productId=productId;
    }

    public Mark(){

    }
}
