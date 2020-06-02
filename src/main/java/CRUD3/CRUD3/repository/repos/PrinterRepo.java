package CRUD3.CRUD3.repository.repos;

import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.repository.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;

public interface PrinterRepo extends CommonRepository<Printer> {


    @Override
    @Query("SELECT p FROM Printer p where p.price >= :min")
    Iterable<Printer> findAllbyPrice(@Param("min") BigDecimal min);

    @Override
    @Query("SELECT p FROM Printer p where p.price <= :max")
    Iterable<Printer> findAllbyMaxPrice(@Param("max")BigDecimal maxPrice);

    @Override
    @Query("SELECT p " +
            "FROM Printer p " +
            "where p.price >= :min and p.price <= :max")
    Iterable<Printer> findAllbyPrice(@Param("min")BigDecimal minPrice,@Param("max")BigDecimal maxPrice);


    @Query("select p "+
            "from Printer p "+
            "where 	p.type IN :types and "+
            "	p.color IN :colors and "+
            "	p.format IN :formats and"+
            "	p.twoSidedPrinting IN :printMode and "+
            "	p.maxPrintSpeed >= :min and p.maxPrintSpeed <= :max and"+
            "   p.price >= :minPrice and p.price <= :maxPrice "
    )
    Page<Printer> getAllByParams(	Collection<String> types,
                                              Collection<String> colors,
                                              Collection<String> formats,
                                              Collection<String> printMode,
                                              @Param("min") double minSpeed,
                                              @Param("max") double maxSpeed,
                                              BigDecimal minPrice,
                                              BigDecimal maxPrice,
                                              Pageable pageable
    );

    @Override
    @Query("SELECT p " +
            "FROM Printer p " +
            "where lower(p.name) like %?1% "
    )
    Page<Printer> findBySearch(String firstName, Pageable pageable);

    @Query("SELECT distinct p.format " +
            "FROM Printer p " +
            "where p.format is not NULL")
    String[] getFormat();

    @Query("SELECT min(p.maxPrintSpeed)" +
            "FROM Printer p")
    double getMinPrintSpeed();

    @Query("SELECT max(p.maxPrintSpeed)" +
            "FROM Printer p")
    double getMaxPrintSpeed();

    @Query("SELECT min(p.price)" +
            "FROM Printer p")
    double getMinPrice();

    @Query("SELECT max(p.price)" +
            "FROM Printer p")
    double getMaxPrice();

    @Query("SELECT distinct p.type " +
            "FROM Printer p " +
            "where p.type is not NULL")
    String[] getType();

    @Query("SELECT distinct p.color " +
            "FROM Printer p " +
            "where p.color is not NULL")
    String[] getColor();

    @Query("SELECT distinct p.twoSidedPrinting " +
            "FROM Printer p " +
            "where p.twoSidedPrinting is not NULL")
    String[] getTwoSidedPrinting();
}
