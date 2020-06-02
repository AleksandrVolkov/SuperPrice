package CRUD3.CRUD3.repository.repos;

import CRUD3.CRUD3.model.Mark;
import CRUD3.CRUD3.model.tovarmodel.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarkRepo extends JpaRepository<Mark,Long> {
}
