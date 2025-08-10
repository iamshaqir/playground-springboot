package playground.mapping.pg.rv1.one2many;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "employees")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer numberOfEmployees;

    // Inverse side, mapped to department field in department table
    // ensures that if a department is deleted, its employees are also deleted
    // ensures that if an employee is removed from the 'employees' set, also deleted from database
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new HashSet<>();
        }
        if (employee != null) {
            employees.add(employee);
            employee.setDepartment(this);
        }
    }

    public void removeEmployee(Employee employee) {
        if (employee != null) {
            employees.remove(employee);
            employee.setDepartment(null);
        }
    }
}
