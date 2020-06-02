package CRUD3.CRUD3.services.impl.Productimpl;

import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.CommonRepository;
import CRUD3.CRUD3.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public abstract class AbstractService<E extends Product, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public Iterable<E> getConcreteProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAll(pageable);
    }

    @Override
    public E getConcreteProduct(Long productId) {
        return repository.findById(productId).get();
    }

    @Override
    public Iterable<E> getProductByPrice(BigDecimal minPrice) {
        return repository.findAllbyPrice(minPrice);
    }

    @Override
    public Iterable<E> getProductByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.findAllbyPrice(minPrice, maxPrice);
    }

    @Override
    public Iterable<E> getProductByMaxPrice(BigDecimal maxPrice) {
        return repository.findAllbyMaxPrice(maxPrice);
    }

    public E saveLike(E product){
        fill(product);
        var x=repository.findById(product.getProduct_id()).get();
        x.setPluslike(product.getPluslike());
        return repository.save(x);
    }

    public E saveDislike(E product){
        fill(product);
        var x=repository.findById(product.getProduct_id()).get();
        x.setDislike(product.getDislike());
        return repository.save(x);

    }

//    public E saveComment(Comment comment){
//        fill(product);
//        return repository.save(product);
//    }

    public Iterable<E> getOrderBy(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return repository.orderByPrice(pageable);
    }

    public Iterable<E> getOrderByMin(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return repository.orderByMinPrice(pageable);
    }

    public Iterable<E> getConcreteProductBySearch(String search,int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findBySearch(search.toLowerCase().trim(),pageable);
    }

    public Iterable<E> getOrderByMark(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return  repository.orderByMark(pageable);
    }

    private void fill(Product p) {
        p.setProduct_type(p.getClass().getSimpleName().toLowerCase());
    }
}