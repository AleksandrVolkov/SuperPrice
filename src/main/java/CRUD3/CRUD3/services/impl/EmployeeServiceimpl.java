//package CRUD3.CRUD3.services.impl;
//
//import CRUD3.CRUD3.model.Employee;
//import CRUD3.CRUD3.repository.EmployeeRepository;
//import CRUD3.CRUD3.services.EmployeeService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class EmployeeServiceimpl implements EmployeeService {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @Override
//    public Employee addEmployee(Employee employee) {
//        return employeeRepository.save(employee);
//    }
//
//    @Override
//    public void deleteEmployee(Employee employee) {
//        employeeRepository.delete(employee);
//    }
//
//    @Override
//    public List<Employee> getEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    @Override
//    public List<Employee> getEmployeeOrderByName(String field){
//       // return employeeRepository.findAllOrderByName(field);
//        return  new ArrayList<>();
//    }
//
//    @Override
//    public void saveEmployee(Employee employee) {
//            employeeRepository.save(employee);
//    }
//
//
//    @Override
//    public Employee getEmployee(Long id) {
//       return employeeRepository.getOne(id);
//    }
//
//    @Override
//    public int count() {
//        //return employeeRepository.getCount();
//        return 0;
//    }
//}
