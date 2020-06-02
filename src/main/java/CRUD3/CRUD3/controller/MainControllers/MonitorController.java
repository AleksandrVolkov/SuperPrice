package CRUD3.CRUD3.controller.MainControllers;

import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.services.impl.Productimpl.MonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mon")
public class MonitorController extends AbstractController<Monitor, MonitorService> {

    protected MonitorController(MonitorService service) {
        super(service);
    }

    @GetMapping("/screenResolution")
    String[] screenResolution(){ return service.getScreenResolution(); }

    @GetMapping("/matrixType")
    String[] matrixType(){
        return service.getMatrixType();
    }

    @GetMapping("/minPrice")
    double minPrice(){ return service.getMinPrice(); }

    @GetMapping("/maxPrice")
    double maxPrice(){
        return service.getMaxPrice();
    }

    @GetMapping("/minScreen")
    double minScreen(){
        return service.getMinScreen();
    }

    @GetMapping("/maxScreen")
    double maxScreen() {
        return service.getMaxScreen();
    }

    @GetMapping("/minFreq")
    Integer minFreq() {
        return service.getMinFreq();
    }

    @GetMapping("/maxFreq")
    Integer maxFreq() {
        return service.getMaxFreq();
    }

}
