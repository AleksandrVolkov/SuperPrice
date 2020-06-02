package CRUD3.CRUD3.controller;//package CRUD3.CRUD3.controller;
//
//import CRUD3.CRUD3.model.Employee;
import CRUD3.CRUD3.controller.OrderController;
import CRUD3.CRUD3.model.MyOrder;
import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.model.tovarmodel.Product;
import CRUD3.CRUD3.repository.OrderRepository;
import CRUD3.CRUD3.repository.repos.PCRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class OrderControllerTest {

    @LocalServerPort
    int randomServerPort;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderController controller;

    @Autowired
    private TestRestTemplate restTemplate;
    @Test
    void addOrder() throws URISyntaxException {

    }

//    @Test
//    void getOrders() throws MalformedURLException {
//
//        String  date=controller.getMaxDate().toString();
//        ResponseEntity<String> response = restTemplate.getForEntity(
//                new URL("http://localhost:" + randomServerPort + "/order/allorders").toString(), String.class);
//        String date1=orderRepository.getMaxDate().toString();
//        assertEquals(controller.getOrders(controller.getMinDate().toString(),controller.getMaxDate().toString()), orderRepository.findAll());
//    }

    @Test
    void getMinDate() {
        String  date=controller.getMinDate().toString();
        String date1=orderRepository.getMinDate().toString();
        assertEquals(date1, date);
    }

    @Test
    void fill() {

        Product mon=new Monitor();
        mon.setProduct_type("monitor");
        Monitor mon2=new Monitor();
        controller.fill(List.of(mon2));
        assertEquals(mon.getProduct_type(), mon2.getProduct_type());
    }

    @Test
    void getMaxDate() {
        String  date=controller.getMaxDate().toString();
        String date1=orderRepository.getMaxDate().toString();
        assertEquals(date1, date);
    }
}
