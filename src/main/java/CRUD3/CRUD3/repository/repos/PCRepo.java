package CRUD3.CRUD3.repository.repos;

import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.repository.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface PCRepo extends CommonRepository<PC> {

    @Override
    @Query("SELECT p FROM PC p where p.price >= :min")
    Iterable<PC> findAllbyPrice(@Param("min") BigDecimal min);

    @Override
    @Query("SELECT p FROM PC p where p.price <= :max")
    Iterable<PC> findAllbyMaxPrice(@Param("max") BigDecimal maxPrice);

    @Override
    @Query("SELECT p " +
            "FROM PC p " +
            "where p.price >= :min and p.price <= :max")
    Iterable<PC> findAllbyPrice(@Param("min") BigDecimal minPrice, @Param("max") BigDecimal maxPrice);


    @Query("SELECT p " +
            "FROM PC p " +
            "where  p.ramSize IN :size and" +
            "       p.price >= :minp and p.price <= :maxp and" +
            "       p.ramModel IN :types and" +
            "       p.cpuFrequency >= :minf and p.cpuFrequency <= :maxf and" +
            "       p.cpuCoresCount IN :cores")
    Page<PC> findAllbyParams(@Param("minp") BigDecimal minPrice,
                             @Param("maxp") BigDecimal maxPrice,
                             @Param("minf") double minf,
                             @Param("maxf") double maxf,
                             Collection<String> size,
                             Collection<String> types,
                             Collection<Integer> cores,
                             Pageable pagebale
                                 );

    @Override
    @Query("SELECT p " +
            "FROM PC p " +
            "where lower(p.name) like %?1% "
    )
    Page<PC> findBySearch(String firstName, Pageable pageable);

    //@Override
    @Query("SELECT p " +
            "FROM PC p " +
            "ORDER BY p.price"
    )
    Page<PC> orderByPrice(Pageable pageable);

    @Query("SELECT min(p.cpuFrequency)" +
            "FROM PC p")
    double getMinFreq();

    @Query("SELECT max(p.cpuFrequency)" +
            "FROM PC p")
    double getMaxFreq();

    @Query("SELECT min(p.price)" +
            "FROM PC p")
    double getMinPrice();

    @Query("SELECT max(p.price)" +
            "FROM PC p")
    double getMaxPrice();

    @Query("SELECT distinct p.cpuCoresCount " +
            "FROM PC p " +
            "WHERE p.cpuCoresCount is not NULL " +
            "ORDER BY p.cpuCoresCount")
    Integer[] getCores();

    @Query("SELECT distinct p.ramSize " +
            "FROM PC p " +
            "WHERE p.ramSize is not NULL ")
    String[] getRamSize();

    @Query("SELECT distinct p.ramModel " +
            "FROM PC p " +
            "WHERE p.ramModel is not NULL " +
            "ORDER BY p.ramModel")
    String[] getRamModel();


}
