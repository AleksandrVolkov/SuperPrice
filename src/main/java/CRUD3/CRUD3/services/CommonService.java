package CRUD3.CRUD3.services;

import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.tovarmodel.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface CommonService<E extends Product> {

    Iterable<E> getConcreteProducts(int page);

    E getConcreteProduct(Long productId);

    Iterable<E> getProductByPrice(BigDecimal minPrice);

    Iterable<E> getProductByMaxPrice(BigDecimal maxPrice);

    Iterable<E> getProductByPrice(BigDecimal minPrice,BigDecimal maxPrice);

    Iterable<E> getAllbyParams(AbstractPost post, int pageable);

    E saveLike(E product);

    E saveDislike(E product);

    Iterable<E> getConcreteProductBySearch(String search,int page);
  //  E saveComment(E product);

    Iterable<E> getOrderBy(int page);

    Iterable<E> getOrderByMin(int page);

    Iterable<E> getOrderByMark(int page);
}
