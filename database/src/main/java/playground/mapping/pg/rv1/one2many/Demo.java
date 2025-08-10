package playground.mapping.pg.rv1.one2many;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class Demo implements CommandLineRunner {

    private final Faker faker;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Department hr = buildDepartment();
        List<Employee> employees = createEmployee(5);
        employees.forEach(hr::addEmployee);
        departmentRepository.save(hr);

        // Fetching data from Inverse side @OneToMany
        log.info("--- Fetching data from Inverse side @OneToMany ---");
        Department hrDept = departmentRepository.findById(hr.getId()).orElseThrow();
        log.info("HR Department: {}", hr);

        log.info("--- Fetching Employee details lazily ---");
        hr.getEmployees().forEach(System.out::println);

        log.info("--- Orphan removal example---");
        log.info("--- Getting employee from database ---");
        Employee firstEmployee = employees.stream().findFirst().orElseThrow();
        Employee employee = employeeRepository.findById(firstEmployee.getId()).orElseThrow();
        Department department = employee.getDepartment();
        log.info("Department of employee to be removed: {}", department);
        hrDept.removeEmployee(employee); // Removing employee using orphan removal
        log.info("Removing Employee: {}", firstEmployee);
    }

    private List<Employee> createEmployee(int size) {
        return IntStream.range(0, size)
                .mapToObj(i -> Employee.builder()
                        .name(faker.name().fullName())
                        .email(faker.internet().emailAddress())
                        .build())
                .toList();
    }

    private Department buildDepartment() {
        return Department.builder()
                .name(faker.commerce().department())
                .numberOfEmployees(faker.number().numberBetween(10, 50))
                .build();
    }
}
