package CRUD3.CRUD3.controller;


import CRUD3.CRUD3.model.MyOrder;
import CRUD3.CRUD3.model.MyUser;
import CRUD3.CRUD3.model.OrderProduct;
import CRUD3.CRUD3.model.ProductCountKey;
import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.MyUserRepository;
import CRUD3.CRUD3.repository.OrderRepository;
import CRUD3.CRUD3.security.jwt.JwtTokenProvider;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {

    private JavaMailSender javaMailSender;
    private MyUserRepository myUserRepository;
    //  private ProductRepository productRepository;
   // private ProdOrderRepo prodOrderRepo;
    private OrderRepository orderRepository;
    private JwtTokenProvider jwtAuthenticationProvider;

    public OrderController(
            MyUserRepository myUserRepository,
            JwtTokenProvider jwtAuthenticationProvider,
            OrderRepository orderRepository,
            JavaMailSender javaMailSender) {
        this.orderRepository = orderRepository;
        this.jwtAuthenticationProvider=jwtAuthenticationProvider;
        this.myUserRepository = myUserRepository;
        this.javaMailSender = javaMailSender;

    }


//    @GetMapping("/products")
//    public List<Product> products() {
//
//        return productRepository.findAll();
//    }

//    @GetMapping("/backet")
//    public Collection<Product> getBacket(Principal principal) {
//        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
//        Jwt token = jwtAuth.getToken();
//        //  String s = httpRequest.getHeader("Authorization").replace("Bearer ", "");
////         = RSATokenVerifier.create(s).getToken();
////        System.out.printf("sub = %s%n", token.getSubject());
//        Backet backet;
//        if (!myUserRepository.findById(token.getSubject()).isPresent()) {///!myUserRepository.findById(principal.getName()).isPresent()) {
//            MyUser user = new MyUser();
//            user.setId(token.getSubject());
//            user.setFirstName(token.getClaim("firstName"));
//            user.setLastName(token.getClaim("lastName"));
//            user.setUserName(token.getClaim("email"));
//            user.setEmail(token.getClaim("email"));
////            user.setPicture(token.getPicture());
//            backet = new Backet();
//            //user.setBacket(backet);
//            myUserRepository.save(user);
//        } else {
//            backet = myUserRepository.findById(token.getSubject()).get().getBacket();
//        }
//        return backet.getProducts();
//
//    }

    //
//    @PostMapping("/backet/add")
//    public <T extends Product> void addProduct(@RequestBody T product,
//                                               Principal principal) throws Exception {
//
//        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
//        Jwt token = jwtAuth.getToken();
//        MyUser user;
//        Map<String, Boolean> response = new HashMap<>();
//        Backet backet;
//        product.setProduct_type(product.getClass().getSimpleName().toLowerCase());
//        if (!myUserRepository.findById(token.getSubject()).isPresent()) {///!myUserRepository.findById(principal.getName()).isPresent()) {
//            user = new MyUser();
//            user.setId(token.getSubject());
//            user.setFirstName(token.getClaim("firstName"));
//            user.setLastName(token.getClaim("lastName"));
//            user.setUserName(token.getClaim("email"));
//            user.setEmail(token.getClaim("email"));
//            //user.setPicture(token.getPicture());
//            backet = new Backet();
//            user.setBacket(backet);
//            //response.put("added",Boolean.TRUE);
//        } else {
//            user = myUserRepository.findById(token.getSubject()).get();
//            if (user.getBacket().getProducts() != null) {
//                List<Product> l = new ArrayList<>();
//                for (Product p : user.getBacket().getProducts()) {
//                    l.add(p);
//                }
//                l.add(product);
//                user.getBacket().setProducts(l);
//            } else {
//                //   user = myUserRepository.findById(token.getSubject()).get();
//                ArrayList<Product> ps = new ArrayList<>();
//                ps.add(product);
//                user.getBacket().setProducts(ps);
//            }
//        }
//        myUserRepository.save(user);
//        //return response;
//
//    }

//    //
//    @DeleteMapping("/backet/{id}")
//    public Map<String, Boolean> deleteBacket(@PathVariable(value = "id") Long productId,
//                                             Principal principal) throws Exception {
//
//        //String email=jwtAuthenticationProvider.getUsername
//        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
//        Jwt token = jwtAuth.getToken();
//        Map<String, Boolean> response = new HashMap<String, Boolean>();
//        var user = myUserRepository.findById(token.getSubject()).get();
//        if (user != null) {
//            user.getBacket().getProducts().removeIf(product -> product.getProduct_id().equals(productId));
//            //backetRepository.findById(1L).get().setProducts(list);
//            response.put("deleted", Boolean.TRUE);
//            myUserRepository.save(user);
//            return response;
//        }
//        response.put("deleted", Boolean.FALSE);
//        return response;
//    }

    //
//    @PostMapping("/add")
//    public void addOrder(@RequestBody List<? extends Product> products,// KeycloakAuthenticationToken principal,
//                                           HttpServletRequest req) throws Exception {
//
//        OrderProdyct[] orderProdycts = new OrderProdyct[products.size()];
//        fill(products);
//        String username=jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));
////        JwtUser token=(JwtUser)req;
//     //   JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) principal;
//     //   Jwt token = jwtAuth.getToken();
//
//        MyUser user2;
//        if(myUserRepository.findByEmail(username)==null){
//            user2=new MyUser();
//          //  user2.setBacket(new Backet());
//            user2.setId(username);
//        }
//        else {
//            user2=myUserRepository.findByEmail(username);
//        }
//
//        var ord = new MyOrder();
//        ord.setProducts((List<Product>) products);
//        ord.setPrice(products.stream().reduce(BigDecimal.valueOf(0),
//                (x, y) -> x.add((y.getPrice()).multiply(BigDecimal.valueOf((y).getCount1()))),
//                (x, y) -> x.add(y)));
//        //user2.getBacket().setProducts(new ArrayList<>());
//        //ord.setMyUser(user2);
//        ord.setOrderDate(LocalDate.now());
//        var list = new ArrayList<MyOrder>();
//        list.add(ord);
//        user2.setOrders(list);
//        sendMail(user2.getEmail(), products, ord.getPrice());
//        myUserRepository.save(user2);
//        var x = (List<MyOrder>) orderRepository.findAll();
//        var j = x.size();
//        var ordId = x.get(j - 1);
//        // var ordId=z.get(z.size()-1).getId();
//        for (int i = 0; i < orderProdycts.length; i++) {
//            orderProdycts[i] = new OrderProdyct();
//            orderProdycts[i].setCount(products.get(i).getCount1());
//            orderProdycts[i].setOrderId(ordId.getId());
//            orderProdycts[i].setProductId(products.get(i).getProduct_id());
//        }
//        prodOrderRepo.saveAll(List.of(orderProdycts));
//        //return new ResponseEntity<>("ok", HttpStatus.OK);
//    }

    @PostMapping("/add")
    public void addOrder(@RequestBody List<? extends Product> products,// KeycloakAuthenticationToken principal,
                         HttpServletRequest req) throws Exception {
      //  OrderProdyct[] orderProdycts = new OrderProdyct[products.size()];
        fill(products);
        String username=jwtAuthenticationProvider.getUsername(jwtAuthenticationProvider.resolveToken(req));
        Set<OrderProduct> orderProduct= new HashSet<>();
        MyUser user2;
        if(myUserRepository.findByEmail(username)==null){
            user2=new MyUser();
            user2.setEmail(username);
        }
        else {
            user2=myUserRepository.findByEmail(username);
        }
        var ord = new MyOrder();
       // ord.setProducts((List<Product>) products);
        ord.setPrice(products.stream().reduce(BigDecimal.valueOf(0),
                (x, y) -> x.add((y.getPrice()).multiply(BigDecimal.valueOf((y).getCount1()))),
                (x, y) -> x.add(y)));

       ord.setProductCounts(orderProduct);
        ord.setOrderDate(LocalDate.now());
        var list = new ArrayList<MyOrder>();
        ord.setMyUser(user2);
        orderRepository.save(ord);
        list.add(ord);
        for (var i:products){
            var o=new OrderProduct();
            o.setCount(i.getCount1());
            o.setProduct(i);
            o.setOrder(ord);
            var k=new ProductCountKey();
            k.setOrderId(ord.getId());
            k.setProductId(i.getProduct_id());
            o.setId(k);
            orderProduct.add(o);
        }
        ord.setProductCounts(orderProduct);
        user2.setOrders(list);
        sendMail(user2.getEmail(), products, ord.getPrice());
        myUserRepository.save(user2);
//        var x = (List<MyOrder>) orderRepository.findAll();
//        var j = x.size();
//        var ordId = x.get(j - 1);
//        // var ordId=z.get(z.size()-1).getId();
//        for (int i = 0; i < orderProdycts.length; i++) {
//            orderProdycts[i] = new OrderProdyct();
//            orderProdycts[i].setCount(products.get(i).getCount1());
//            orderProdycts[i].setOrderId(ordId.getId());
//            orderProdycts[i].setProductId(products.get(i).getProduct_id());
//        }
//        prodOrderRepo.saveAll(List.of(orderProdycts));
        //return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @GetMapping("/orders")
    public Iterable<MyOrder> getOrders(@RequestParam(name = "mindate") String minDate,
                                       @RequestParam(name = "maxdate") String maxDate) {

        return orderRepository.getByDate(LocalDate.parse(minDate), LocalDate.parse(maxDate));
    }

    @GetMapping("/bysum")
    public Iterable<MyOrder> getOrdersBySum(@RequestParam(name = "mindate") String minDate,
                                            @RequestParam(name = "maxdate") String maxDate) {

        return orderRepository.getBySum(LocalDate.parse(minDate), LocalDate.parse(maxDate));
    }

    @GetMapping("/mindate")
    public LocalDate getMinDate() {

        return orderRepository.getMinDate();
    }

    @GetMapping("/allorders")
    public List<MyOrder> get(@RequestParam(name = "date") String minDate) {
        if(minDate.equals("null") || minDate.isEmpty())
            return (List<MyOrder>) orderRepository.findAll();
        return ((List<MyOrder>)orderRepository.findAll()).stream().filter(x->x.getOrderDate()!=null && x.getOrderDate().compareTo(LocalDate.parse(minDate))>=0).collect(Collectors.toList());
    }

    @GetMapping("/maxdate")
    public LocalDate getMaxDate() {
        return orderRepository.getMaxDate();
    }


    public void fill(List<? extends Product> products) {
        for (var p : products) {
            p.setProduct_type(p.getClass().getSimpleName().toLowerCase());
        }
    }

    private void sendMail(String mail, List<? extends Product> products, BigDecimal price) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");
        StringBuilder htmlMsg = new StringBuilder();
        for (var product : products) {
            htmlMsg.append("  <img src=")
                    .append(product.getShort_image())
                    .append(">").append("<h1>")
                    .append("Ваш заказ ")
                    .append(product.getName())
                    .append("</h1>")
                    .append("<p>")
                    .append(product.getShort_description()).
                    append("</p>").append(" <span>").
                    append(product.getShop())
                    .append("</span>")
                    .append("<p>")
                    .append("Кол-во " + product.getCount1())
                    .append("</p>")
                    .append("<p>"+ product.getLink_on_full_description() +"</p>")
                    .append("<h1>")
                    .append("Цена за один товар ")
                    .append(product.getPrice())
                    .append(" </h1> ");
        }
        htmlMsg.append("<h1>")
                .append("Общая цена за все товары ")
                .append(price)
                .append(" </h1> ");
        message.setContent(htmlMsg.toString(), "text/html; charset=UTF-8");
        helper.setTo(mail);
        helper.setSubject("Test send HTML email");
        this.javaMailSender.send(message);

    }

}


