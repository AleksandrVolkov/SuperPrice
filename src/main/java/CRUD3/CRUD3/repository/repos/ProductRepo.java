package CRUD3.CRUD3.repository.repos;


import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepo extends CommonRepository<Product> {

    @Override
    @Query("SELECT p FROM Product p where p.price >= :min")
    Iterable<Product> findAllbyPrice(@Param("min") BigDecimal min);

    @Override
    @Query("SELECT p FROM Product p where p.price <= :max")
    Iterable<Product> findAllbyMaxPrice(@Param("max")BigDecimal maxPrice);

    @Override
    @Query("SELECT p " +
            "FROM Product p " +
            "where p.price >= :min and p.price <= :max")
    Iterable<Product> findAllbyPrice(@Param("min")BigDecimal minPrice,@Param("max")BigDecimal maxPrice);
    @Override
    @Query("SELECT p " +
            "FROM Printer p " +
            "where lower(p.name) like ?1"
    )
    Page<Product> findBySearch(String firstName, Pageable pageable);

}
