package CRUD3.CRUD3.controller.MainControllers;

import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.services.impl.Productimpl.PrinterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/printer")
public class PrinterController extends AbstractController<Printer, PrinterService> {
    protected PrinterController(PrinterService service) {
        super(service);
    }

    @GetMapping("/format")
    String[] minFormat(){
        return service.getFormat();
    }

    @GetMapping("/minPrintSpeed")
    double minPrintSpeed(){ return service.getMinPrintSpeed(); }

    @GetMapping("/maxPrintSpeed")
    double maxPrintSpeed(){
        return service.getMaxPrintSpeed();
    }

    @GetMapping("/minPrice")
    double minPrice(){
        return service.getMinPrice();
    }

    @GetMapping("/maxPrice")
    double maxPrice(){ return service.getMaxPrice(); }

    @GetMapping("/color")
    String[] color(){
        return service.getColor();
    }

    @GetMapping("/type")
    String[] type(){
        return service.getType();
    }

    @GetMapping("/twoSidedPrinting")
    String[] twoSidedPrinting(){
        return service.getTwoSidedPrinting();
    }
}
