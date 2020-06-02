package CRUD3.CRUD3.services.impl.Productimpl;

import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.PostPOJO.PrinterPost;
import CRUD3.CRUD3.model.tovarmodel.Printer;
import CRUD3.CRUD3.repository.repos.PrinterRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PrinterService extends AbstractService<Printer, PrinterRepo> {
    public PrinterService(PrinterRepo repository) {
        super(repository);
    }

    @Override
    public Iterable<Printer> getAllbyParams(AbstractPost post,int page){
        var post1=(PrinterPost)post;
        Pageable pageable = PageRequest.of(page, 10);
        return  repository.getAllByParams( post1.getPrinterType(),
                post1.getColor(),
                post1.getFormat(),
                post1.getTwoSidedPrinting(),
                post1.getMinSpeed(),
                post1.getMaxSpeed(),
                post1.getMinPrice(),
                post1.getMaxPrice(),
                pageable
        );
    }

    public String[] getFormat(){
        return repository.getFormat();
    }

    public double getMaxPrintSpeed(){
        return repository.getMaxPrintSpeed();
    }

    public double getMinPrintSpeed(){
        return repository.getMinPrintSpeed();
    }

    public double getMinPrice(){
        return repository.getMinPrice();
    }

    public double getMaxPrice() {
        return repository.getMaxPrice();
    }

   public String[] getType() {return repository.getType(); }

    public String[] getColor() {return repository.getColor(); }

    public String[] getTwoSidedPrinting() {return repository.getTwoSidedPrinting(); }

}
