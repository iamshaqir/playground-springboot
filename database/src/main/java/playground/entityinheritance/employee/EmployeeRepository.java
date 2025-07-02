package playground.entityinheritance.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE TYPE(e) = playground.temp.employee.FullTimeEmployee")
    List<Employee> findAllFullTimeEmployees();

    @Query("SELECT e FROM Employee e WHERE TYPE(e) = playground.temp.employee.PartTimeEmployee")
    List<Employee> findAllPartTimeEmployees();

    @Query("SELECT e FROM Employee e WHERE TYPE(e) = playground.temp.employee.ContractorEmployee")
    List<Employee> findAllContractors();

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByLastNameContainingIgnoreCase(String lastName);
}
