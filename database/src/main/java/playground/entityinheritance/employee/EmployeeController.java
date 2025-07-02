package playground.entityinheritance.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/full-time")
    public ResponseEntity<Employee> save(@Valid @RequestBody FullTimeEmployeeRequest request) {
        return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);

    }

    @PostMapping("/part-time")
    public ResponseEntity<Employee> save(@Valid @RequestBody PartTimeEmployeeRequest request) {
        return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);
    }

    @PostMapping("/contractor")
    public ResponseEntity<Employee> save(@Valid @RequestBody ContractorRequest request) {
        return new ResponseEntity<>(employeeService.save(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/full-time")
    public ResponseEntity<List<Employee>> getFullTimeEmployees() {
        List<Employee> employees = employeeService.getFullTimeEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/part-time")
    public ResponseEntity<List<Employee>> getPartTimeEmployees() {
        List<Employee> employees = employeeService.getPartTimeEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/contractors")
    public ResponseEntity<List<Employee>> getContractors() {
        List<Employee> employees = employeeService.getContractors();
        return ResponseEntity.ok(employees);
    }

    public ResponseEntity<List<Employee>> searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        List<Employee> employees;
        if (firstName != null) {
            employees = employeeService.searchByFirstName(firstName);
        } else if (lastName != null) {
            employees = employeeService.searchByLastName(lastName);
        } else {
            employees = employeeService.getAllEmployees();
        }
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
