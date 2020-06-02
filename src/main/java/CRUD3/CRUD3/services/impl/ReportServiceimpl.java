//package CRUD3.CRUD3.services.impl;
//
//import CRUD3.CRUD3.model.Report;
//import CRUD3.CRUD3.repository.ReportRepository;
//import CRUD3.CRUD3.services.ReportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//@Service
//public class ReportServiceimpl implements ReportService {
//
//    @Autowired
//    private ReportRepository repository;
//
//    @Override
//    public Report add(Report report) {
//        return repository.save(report);
//    }
//
//    @Override
//    public List<Report> getReports() {
//        return repository.findAll();
//    }
//
//    @Override
//    public Report delete(Report report) {
//        var rep=report;
//        repository.delete(report);
//        return report;
//    }
//
//    @Override
//    public void delete(Long id) {
//        repository.deleteById(id);
//    }
//}
