package CRUD3.CRUD3.repository.repos;

import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.repository.CommonRepository;
import lombok.Builder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;

public interface MonitorRepo extends CommonRepository<Monitor> {

    @Override
    @Query("SELECT p FROM Monitor p where p.price >= :min")
    Iterable<Monitor> findAllbyPrice(@Param("min") BigDecimal min);

    @Override
    @Query("SELECT p FROM Monitor p where p.price <= :max")
    Iterable<Monitor> findAllbyMaxPrice(@Param("max") BigDecimal maxPrice);

    @Override
    @Query("SELECT p " +
            "FROM Monitor p " +
            "where p.price >= :min and p.price <= :max")
    Iterable<Monitor> findAllbyPrice(@Param("min") BigDecimal minPrice, @Param("max") BigDecimal maxPrice);

    @Query("SELECT p " +
            "FROM Monitor p " +
            "where  p.matrixType IN :matrix and" +
            "       p.screenResolution IN :resolution and" +
            "       p.price >= :minp and p.price <= :maxp and" +
            "       p.screen >= :minScreen and p.screen <=:maxScreen and" +
            "       p.screenFrequency >= :miff and p.screenFrequency <= :maff"
    )
    Page<Monitor> findAllbyParams(@Param("minp") BigDecimal minPrice,
                                  @Param("maxp") BigDecimal maxPrice,
                                  @Param("miff") int minf,
                                  @Param("maff") int maxf,
                                  Double minScreen,
                                  Double maxScreen,
                                  Collection<String> matrix,
                                  Collection<String> resolution,
                                  Pageable page
    );

    @Override
    @Query("SELECT p " +
            "FROM Monitor p " +
            "where lower(p.name) like %?1% "
    )
    Page<Monitor> findBySearch(String firstName, Pageable pageable);

    @Query("SELECT distinct m.screenResolution " +
            "FROM Monitor m " +
            "where m.screenResolution is not NULL " +
            "order by m.screenResolution")
    String[] getScreenResolution();


    @Query("SELECT distinct m.matrixType " +
            "FROM Monitor m " +
            "where m.matrixType is not NULL " +
            "order by m.matrixType")
    String[] getMatrixType();


    @Query("SELECT min(m.screenFrequency)" +
            "FROM Monitor m")
    Integer getMinScreenFreq();

    @Query("SELECT max(m.screenFrequency)" +
            "FROM Monitor m")
    Integer getMaxScreenFreq();

    @Query("SELECT min(m.screen)" +
            "FROM Monitor m")
    double getMinScreen();

    @Query("SELECT max(m.screen)" +
            "FROM Monitor m")
    double getMaxScreen();

    @Query("SELECT min(m.price)" +
            "FROM Monitor m")
    double getMinPrice();

    @Query("SELECT max(m.price)" +
            "FROM Monitor m")
    double getMaxPrice();


}
