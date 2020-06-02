package CRUD3.CRUD3.services.impl.Productimpl;

import CRUD3.CRUD3.model.PostPOJO.AbstractPost;
import CRUD3.CRUD3.model.PostPOJO.MonitorPost;
import CRUD3.CRUD3.model.tovarmodel.Monitor;
import CRUD3.CRUD3.repository.repos.MonitorRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MonitorService extends AbstractService<Monitor, MonitorRepo> {

    public MonitorService(MonitorRepo repository) {
        super(repository);
    }

    @Override
    public Iterable<Monitor> getAllbyParams(AbstractPost post,int page) {
        var post1 = (MonitorPost) post;
        Pageable pageable = PageRequest.of(page, 10);
        return repository.findAllbyParams(
                                            post1.getMinPrice(),
                                            post1.getMaxPrice(),
                                            post1.getMinScreenFrequency(),
                                            post1.getMaxScreenFrequency(),
                                            post1.getMinScreen(),
                                            post1.getMaxScreen(),
                                            post1.getMatrixType(),
                                            post1.getScreenResolution(),
                                            pageable
        );
    }

    public double getMinPrice(){
        return repository.getMinPrice();
    }

    public double getMaxPrice() {
        return repository.getMaxPrice();
    }

    public double getMinScreen() {
        return repository.getMinScreen();
    }

    public double getMaxScreen() {
        return repository.getMaxScreen();
    }

    public Integer getMinFreq() {
        return repository.getMinScreenFreq();
    }

    public Integer getMaxFreq() {
        return repository.getMaxScreenFreq();
    }

    public String[] getMatrixType() {
        return repository.getMatrixType();
    }

    public String[] getScreenResolution() {
        return repository.getScreenResolution();
    }
}
