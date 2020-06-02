package CRUD3.CRUD3.repository;


import CRUD3.CRUD3.model.MyOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//@RepositoryRestResource(collectionResourceRel = "client", path = "clients")
public interface OrderRepository extends CrudRepository<MyOrder,Long> {


    @Query("SELECT o.orderDate,COUNT(o.id) " +
            "FROM MyOrder o " +
            "WHERE :maxd >=o.orderDate and o.orderDate >=:mind " +
            "GROUP BY o.orderDate " +
            "ORDER BY o.orderDate")
    Iterable<MyOrder> getByDate(@Param("mind") LocalDate mindate, @Param("maxd") LocalDate mindate1);

    @Query("SELECT o.orderDate,SUM(o.price) " +
            "FROM MyOrder o " +
            "WHERE :maxd >=o.orderDate and o.orderDate >=:mind " +
            "GROUP BY o.orderDate " +
            "ORDER BY o.orderDate")
    Iterable<MyOrder> getBySum(@Param("mind") LocalDate mindate, @Param("maxd") LocalDate mindate1);

    @Query("SELECT MIN(o.orderDate)" +
            "FROM MyOrder o")
    LocalDate getMinDate();

    @Query("SELECT MAX(o.orderDate)" +
            "FROM MyOrder o")
    LocalDate getMaxDate();

    @Query("SELECT o " +
            "FROM MyOrder o " +
            "WHERE o.orderDate >=:mind ")
    MyOrder getAllbyDate(@Param("mind") LocalDate mindate);
}
