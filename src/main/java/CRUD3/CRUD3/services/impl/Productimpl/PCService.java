package CRUD3.CRUD3.services.impl.Productimpl;

import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.PostPOJO.PCPost;
import CRUD3.CRUD3.model.tovarmodel.PC;
import CRUD3.CRUD3.repository.repos.PCRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PCService extends AbstractService<PC, PCRepo> {


    public PCService(PCRepo repository) {
        super(repository);
    }

    @Override
    public Iterable<PC> getAllbyParams(AbstractPost post,int page){
        var post1=(PCPost)post;
        Pageable pageable = PageRequest.of(page, 10);
        return  repository.findAllbyParams( post1.getMinPrice(), post1.getMaxPrice(),
                                            post1.getMinFreq(), post1.getMaxFreq(),
                                            post1.getRamSize(), post1.getRamType(),
                                            post1.getCores(),
                                            pageable);
    }

    public double getMinFreq(){
        return repository.getMinFreq();
    }

    public double getMaxFreq(){
        return repository.getMaxFreq();
    }

    public double getMinPrice(){
        return repository.getMinPrice();
    }

    public double getMaxPrice(){
        return repository.getMaxPrice();
    }

    public Integer[] getCores() {
        return repository.getCores();
    }

    public String[] getRamSize() {return  repository.getRamSize();}

    public String[] getRamModel() {return  repository.getRamModel();}
}
