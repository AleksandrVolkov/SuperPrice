package CRUD3.CRUD3.controller;

//import CRUD3.CRUD3.esrepo.CustomerRepository;
//import CRUD3.CRUD3.esrepo.EsRepoPc;
//import CRUD3.CRUD3.model.esModels.Customer;
//import CRUD3.CRUD3.model.esModels.PcEs;
//import CRUD3.CRUD3.repository.repos.PCRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RestController
//@RequestMapping("/es")
//public class ESearchController {
//    @Autowired
//    private EsRepoPc repository;
//
//    @Autowired
//   private PCRepo repo;
//
//    @GetMapping("/save")
//    public int saveCustomer() {
//        Collection<PcEs>list = new LinkedList<>();
//        var list1=repo.findAll();
//        for (var p:list1){
//            list.add(new PcEs(p.getProduct_id(),p.getName()));
//        }
//        repository.saveAll(list);
//        return list.size();
//    }
//
//    @GetMapping("/findAll")
//    public Iterable<PcEs> findAllCustomers() {
//        return repository.findAll();
//    }
//
////    @GetMapping("/findByName/{firstName}")
////    public List<PcEs> findByFirstName(@PathVariable String firstName,@RequestParam("page") int page) {
////        Pageable pageable = PageRequest.of(page, 10);
////        return repository.findByName(firstName,pageable).getContent();
////    }
//
////    @GetMapping("/findByName")
////    public List<PcEs> findByFirstName2(@RequestParam("name") String firstName) {
////       // Pageable pageable = PageRequest.of(page, 10);
////        return repository.
////    }
//}

