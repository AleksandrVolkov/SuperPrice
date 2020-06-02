package CRUD3.CRUD3.repository;


import CRUD3.CRUD3.model.tovarmodel.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepo extends JpaRepository<Comment,Long> {


    @Query("SELECT p FROM Comment p where p.productId = :id")
    public Iterable<Comment> findAllbyId(@Param("id") Long id);
}
