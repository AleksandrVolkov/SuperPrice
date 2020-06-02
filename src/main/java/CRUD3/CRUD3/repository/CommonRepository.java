package CRUD3.CRUD3.repository;

import CRUD3.CRUD3.model.tovarmodel.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.math.BigDecimal;

@NoRepositoryBean
public interface CommonRepository<E extends Product> extends JpaRepository<E, Long> {

    @Override
    Page<E> findAll(Pageable pageable);

    Iterable<E> findAllbyPrice(BigDecimal field);

    Iterable<E> findAllbyMaxPrice(BigDecimal field);

    Iterable<E> findAllbyPrice(BigDecimal min,BigDecimal max);


    Page<E> findBySearch(String firstName, Pageable pageable);

    @Query("select p from #{#entityName} p order by p.price")
    Page<E> orderByPrice(Pageable pageable);

    @Query("select p from #{#entityName} p order by p.price desc")
    Page<E> orderByMinPrice(Pageable pageable);

    @Query("select p,(p.pluslike + p.dislike) as mark from #{#entityName} p order by mark desc nulls last")
    Page<E> orderByMark(Pageable pageable);

//    @Query("select p from {#entityName}")
//////    Iterable<E> getAll();
    // Iterable<E> findAllbyParams(AbstractPost post);

    // Iterable<E> findAllbyParams(AbstractPost post);
}
