package CRUD3.CRUD3.controller.MainControllers;

import CRUD3.CRUD3.model.Mark;
import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.ReturnString;
import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.CommentRepo;
import CRUD3.CRUD3.repository.repos.MarkRepo;
import CRUD3.CRUD3.security.jwt.JwtTokenProvider;
import CRUD3.CRUD3.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public abstract class AbstractController<E extends Product, S extends CommonService<E>>
        implements CommonController<E> {

    protected final S service;

    @Autowired
    private MarkRepo markRepo;
    @Autowired
    private JwtTokenProvider jwtAuthenticationProvider;

    @Autowired
    private CommentRepo commentRepo;

    protected AbstractController(S service) {
        this.service = service;

    }

    @Override
    public Iterable<E> getProducts(@RequestParam("page") int page){
        return service.getConcreteProducts(page);
    }

//    @Override
//    public E getProduct(Long productId){
//        return service.getConcreteProduct(productId);
//    }

    @Override
    public Iterable<E> getProductsByMin(@RequestParam("min") BigDecimal min) {
        return service.getProductByPrice(min);
    }

    @Override
    public Iterable<E> getProductsByMax(@RequestParam("max") BigDecimal max){
        return service.getProductByMaxPrice(max);
    }

    @Override
    public Iterable<E> getProducts(@RequestParam("min") BigDecimal min,@RequestParam("max") BigDecimal max){
        return service.getProductByPrice(min,max);
    }

    @Override
    public<T extends AbstractPost> Iterable<E> getProductsByParams(@RequestBody T post,@RequestParam("page") int page){
        return service.getAllbyParams(post,page);
    }

    @Override
    public <T extends  Product> ReturnString like(@RequestBody T p, HttpServletRequest req){
        String username=jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));
        boolean flag = markRepo.findAll().stream().anyMatch(mark -> mark.getProductId().equals(p.getProduct_id())
                                                                     && mark.getUserEmail().equals(username));
        if(!flag){

            markRepo.save(new Mark(username,p.getProduct_id()));
            p.setProduct_type(p.getClass().getSimpleName().toLowerCase());
            service.saveLike((E) p);
            return  new ReturnString("true",false,false);
        }
        return new ReturnString("",true,true);
    }

    @Override
    public <T extends  Product> ReturnString dislike(@RequestBody T p,HttpServletRequest req){
        String username=jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));
        boolean flag = markRepo.findAll().stream().anyMatch(mark -> mark.getProductId().equals(p.getProduct_id())
                && mark.getUserEmail().equals(username));
        if(!flag) {

            markRepo.save(new Mark(username, p.getProduct_id()));
            p.setProduct_type(p.getClass().getSimpleName().toLowerCase());
            service.saveDislike((E) p);
            return  new ReturnString("true",false,false);
        }
        return new ReturnString("",true,true);
    }

    @Override
    public Iterable<E> getProductsBySearch(@PathVariable(value =  "search") String search, @RequestParam("page") int page){
        if(search.contains("[") || search.contains("]"))
            search.replaceAll("/\\[(.+)\\]/","");
        return service.getConcreteProductBySearch(search,page);

    }

    @Override
    public Iterable<E> orderByPrice(int page){
        return service.getOrderBy(page);
    }

    @Override
    public Iterable<E> orderByMinPrice(int page){
        return service.getOrderByMin(page);
    }

    @Override
    public Iterable<E> orderByMark(@RequestParam("page")  int page){
        return  service.getOrderByMark(page);
    }

//    @Override
//    public Comment comment(Comment comment){
//        if(comment.getUserName()==null){
//            comment.setUserName("Гость");
//        }
//        comment.setDate(LocalDate.now());
//        return commentRepo.save(comment);
//    }



}
