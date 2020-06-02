package CRUD3.CRUD3.repository;

import CRUD3.CRUD3.model.parse.ParsedTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParsedTableRepo extends JpaRepository<ParsedTable, String> {
    @Query("SELECT p FROM ParsedTable p order by p.datePars desc")
    Page<ParsedTable> findPageable(Pageable page);
}
