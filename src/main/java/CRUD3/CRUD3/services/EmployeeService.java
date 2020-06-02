package CRUD3.CRUD3.services;

import CRUD3.CRUD3.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    List<Employee> getEmployees();
    List<Employee> getEmployeeOrderByName(String field);
    void saveEmployee(Employee employee);
    Employee getEmployee(Long id);
    int count();
}
