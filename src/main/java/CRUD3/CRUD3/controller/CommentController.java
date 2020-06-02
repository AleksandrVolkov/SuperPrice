package CRUD3.CRUD3.controller;

import CRUD3.CRUD3.model.tovarmodel.Comment;
import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/comment")
public class CommentController {



   // private ProductRepo productRepo;
    private CommentRepo commentRepo;

    @Autowired
    public CommentController( CommentRepo commentRepo){
      //  this.productRepo=productRepo;
        this.commentRepo=commentRepo;
    }

    @PostMapping("/like")
    public<T extends  Product> void like(@RequestBody T product){

        fill(product);
      //  productRepo.save(product);
    }

    @PostMapping("/dislike")
    public void dislike(Product product){


       // productRepo.save(product);
    }

    @PostMapping("/comment")
    public Iterable<Comment> comment(@RequestBody Comment comment){
        if(comment.getUserName()==null){
            comment.setUserName("Гость");
        }
        comment.setDate(LocalDate.now());
        commentRepo.save(comment);
        return  commentRepo.findAllbyId(comment.getProductId());
    }

    @GetMapping("/getcomments")
    public Iterable<Comment> getComment(@RequestParam("id") String id){

        return  commentRepo.findAllbyId(Long.valueOf(id));
    }

    @PostMapping("/comment/like")
    public void getComment(@RequestBody Comment comment){

        commentRepo.save(comment);
    }

    @PostMapping("/comment/dislike")
    public void dComment(@RequestBody Comment comment){

        commentRepo.save(comment);
    }

    private void fill(Product p) {

            p.setProduct_type(p.getClass().getSimpleName().toLowerCase());

    }
}
