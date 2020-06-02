package CRUD3.CRUD3.controller.MainControllers;

import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.services.impl.Productimpl.PCService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@PreAuthorize("hasAuthority('Admin')")
@RequestMapping("/pc")
@CrossOrigin(origins = "*")
public class PcController extends AbstractController<PC, PCService> {

    public PcController(PCService service) {
        super(service);
    }

    @GetMapping("/minFreq")
    double minFreq(){
        return service.getMinFreq();
    }

    @GetMapping("/maxFreq")
    double maxFreq(){
        return service.getMaxFreq();
    }

    @GetMapping("/minPrice")
    double minPrice(){
        return service.getMinPrice();
    }

    @GetMapping("/maxPrice")
    double maxPrice(){
        return service.getMaxPrice();
    }

    @GetMapping("/cores")
    Integer[] cores(){
        return service.getCores();
    }

    @GetMapping("/ramSize")
    String[] ramSize(){
        return service.getRamSize();
    }

    @GetMapping("/ramModel")
    String[] ramModel(){
        return service.getRamModel();
    }
}
