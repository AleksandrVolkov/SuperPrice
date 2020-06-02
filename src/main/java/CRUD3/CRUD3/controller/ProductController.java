package CRUD3.CRUD3.controller;

import CRUD3.CRUD3.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/p")
public class ProductController {

    @Autowired
    private MyUserRepository myUserRepository;

//    @Autowired
////    ProductRepo productRepository;



//    @Autowired
//    CategoryRepository categoryRepository;
//
//    @Autowired
//    ProductTypeCateg productTypeCateg;

//    @PostMapping("/backet/add")
//    public void addProduct(@RequestBody Product product, KeycloakAuthenticationToken principal
//    ) throws Exception {
//
//        AccessToken token = principal.getAccount().getKeycloakSecurityContext().getToken();
//        MyUser user = new MyUser();
//        Backet backet;
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        if (!myUserRepository.findById(principal.getName()).isPresent()) {
//            user.setId(principal.getName());
//            user.setFirstName(token.getGivenName());
//            user.setLastName(token.getFamilyName());
//            user.setUserName(token.getPreferredUsername());
//            user.setEmail(token.getEmail());
//            user.setPicture(token.getPicture());
//            backet = new Backet();
//            backet.setProducts(List.of(product));
//            user.setBacket(backet);
//            response.put("added",Boolean.TRUE);
//        } else {
//            myUserRepository.findById(principal.getName()).get().getBacket().setProducts(List.of(product));
//            response.put("added",Boolean.TRUE);
//        }
//        myUserRepository.save(user);
//        // return response;

//    }

//    @GetMapping("/get")
//    public List<CreditCard> getProd(){
//
//        var x=new CreditCard();
//        x.setCardNumber(777);
//
//        bRepo.save(x);
//        return cRepo.findAll();
//    }
}
