package CRUD3.CRUD3;

import CRUD3.CRUD3.controller.MainControllers.PcController;
import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.repository.repos.PCRepo;
import CRUD3.CRUD3.services.impl.Productimpl.PCService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class PcControllerTest {
//
////    @Autowired
////    PCService pcService;
////
//    @MockBean
//    PCRepo pcRepo;
//
//    @Autowired
//    PcController pcController;
//
//    public PcControllerTest() {
//        pcRepo.save(new PC(Long.valueOf(1), "product_type",
//                "shop", "short_image", "name",
//                "short_description",
//                "link_on_full_description", BigDecimal.valueOf(44)));
//    }
//
//    @Test
//    public void contexLoads() throws Exception {
//        assertThat(pcController).isNotNull();
//    }
//
//    @Test
//    public void test() throws Exception {
//        pcController.getProduct(Long.valueOf(1));
//        //assertThat(pcController).isNotNull();
//    }
//}
