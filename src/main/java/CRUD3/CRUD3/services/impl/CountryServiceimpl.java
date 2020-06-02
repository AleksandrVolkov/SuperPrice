//package CRUD3.CRUD3.services.impl;
//
//import CRUD3.CRUD3.model.Country;
//import CRUD3.CRUD3.repository.CountryRepository;
//import CRUD3.CRUD3.services.CountryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CountryServiceimpl implements CountryService {
//
//    @Autowired
//    private CountryRepository repository;
//
//    @Override
//    public Country add(Country report) {
//        return repository.save(report);
//    }
//
//    @Override
//    public List<Country> getCountries() {
//        return repository.findAll();
//    }
//
//    public Country delete(Country report) {
//        var rep=report;
//        repository.delete(report);
//        return report;
//    }
//
//    public List<Country> addAll(List<Country> c){
//        if(c!= null) {
//            return repository.saveAll(c);
//        }
//        throw new IllegalArgumentException();
//    }
//
//    @Override
//    public void delete(Long id) {
//        repository.deleteById(id);
//    }
//}
