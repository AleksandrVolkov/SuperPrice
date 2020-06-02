//package CRUD3.CRUD3.repository;
//
//import CRUD3.CRUD3.model.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
////import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//
//public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    @Query("SELECT u FROM Employee u ORDER BY name")
//    List<Employee> findAllOrderByName( String field);
//    @Query("SELECT count(u.id) FROM Employee u")
//    int getCount();
//}
