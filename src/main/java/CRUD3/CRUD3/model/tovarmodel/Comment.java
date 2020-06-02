package CRUD3.CRUD3.model.tovarmodel;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String userName;

    private Integer pluslike;

    private Integer dislike;

    private LocalDate date;

    private String comments;

    private Long productId;
}
