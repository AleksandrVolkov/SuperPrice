package CRUD3.CRUD3;

import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.repository.repos.PCRepo;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestPropertySource("/application-test.properties")
//public class PCRepoTest {
//
//    @Autowired
//    private PCRepo pcRepo;
//
//    @Test
//    public void contexLoads() throws Exception {
//        Assertions.assertThat(pcRepo).isNotNull();
//    }
//
//    @Test
//    public void save() throws Exception {
//        List<PC> list = new ArrayList<>();
//        PC pc3 = new PC(Long.valueOf(3), "product_type",
//                "shop", "short_image", "name", "short_description",
//                "link_on_full_description", BigDecimal.valueOf(44));
//        PC pc2 = new PC(Long.valueOf(2), "product_type",
//                "shop", "short_image", "name", "short_description",
//                "link_on_full_description", BigDecimal.valueOf(44));
//
//        PC pc1 = new PC(Long.valueOf(1), "product_type",
//                "shop", "short_image", "name", "short_description",
//                "link_on_full_description", BigDecimal.valueOf(22));
//
//        pc1.setCpuCoresCount(2);
//        pc2.setCpuCoresCount(4);
//        pc3.setCpuCoresCount(6);
//
//        pc1.setRamModel("DDR3");
//        pc2.setRamModel("DDR3L");
//        pc3.setRamModel("DDR4");
//
//        list.add(pc1);
//        list.add(pc2);
//        list.add(pc3);
//        pcRepo.saveAll(list);
//        Assert.assertEquals( pcRepo.findAll().size(), list.size());
//    }
//
//    @Test
//    public void getCores() {
//        Assert.assertEquals(pcRepo.getCores(), new Integer[]{2,4,6});
//    }
//
////    @Test
////    public void getRamModel() {
////        Assert.assertEquals(pcRepo.getRamModel(), new String[]{"DDR3", "DDR3L", "DDR4"});
////    }
//
//
//    @Test
//    public void getMinPrice() throws Exception {
//        Assert.assertEquals((int) pcRepo.getMinPrice(), 22);
//    }
//
////    @Test
////    public void getMaxPrice() throws Exception {
////        Assert.assertEquals((int) pcRepo.getMaxPrice(), 44);
////    }
//}
