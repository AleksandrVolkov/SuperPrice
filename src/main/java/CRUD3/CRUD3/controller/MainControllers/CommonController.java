package CRUD3.CRUD3.controller.MainControllers;

import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.ReturnString;
import CRUD3.CRUD3.model.tovarmodel.Product;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public interface CommonController<E extends Product> {


    @GetMapping
    Iterable<E> getProducts(int page);

//    @GetMapping("/{id}")
//    E getProduct(@PathVariable(value =  "id") Long productId);

    @GetMapping("/minprice")
    Iterable<E> getProductsByMin(@RequestParam("min") BigDecimal min);

    @GetMapping("/maxprice")
    Iterable<E> getProductsByMax(@RequestParam("max") BigDecimal max);

    @GetMapping("/price")
    Iterable<E> getProducts(@RequestParam("min") BigDecimal min,@RequestParam("max") BigDecimal max);

    @PostMapping("/params")
    public<T extends AbstractPost> Iterable<E> getProductsByParams(T post,@RequestParam("page") int page);

    @PostMapping("/like")
    <T extends  Product> ReturnString like(@RequestBody T product, HttpServletRequest req);

    @PostMapping("/dislike")
    <T extends  Product> ReturnString dislike(@RequestBody T product, HttpServletRequest req);

    @GetMapping("/{search}")
    Iterable<E> getProductsBySearch(@PathVariable(value =  "search") String search,int page);

    @GetMapping("/byprice")
    Iterable<E> orderByPrice(@RequestParam("page")  int page);

    @GetMapping("/byminprice")
    Iterable<E> orderByMinPrice(@RequestParam("page")  int page);

    @GetMapping("/bymark")
    Iterable<E> orderByMark(@RequestParam("page")  int page);


}