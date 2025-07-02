package playground.entityinheritance.employee;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper mapper;

    public Employee save(FullTimeEmployeeRequest request) {
        Employee employee = mapper.toFullTimeEmployee(request);
        return employeeRepository.save(employee);
    }

    public Employee save(PartTimeEmployeeRequest request) {
        Employee employee = mapper.toPartTimeEmployee(request);
        return employeeRepository.save(employee);
    }

    public Employee save(ContractorRequest request) {
        Employee employee = mapper.toContractor(request);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getFullTimeEmployees() {
        return employeeRepository.findAllFullTimeEmployees();
    }

    public List<Employee> getPartTimeEmployees() {
        return employeeRepository.findAllPartTimeEmployees();
    }

    public List<Employee> getContractors() {
        return employeeRepository.findAllContractors();
    }

    public List<Employee> searchByFirstName(String firstName) {
        return employeeRepository.findByFirstNameContainingIgnoreCase(firstName);
    }

    public List<Employee> searchByLastName(String lastName) {
        return employeeRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
