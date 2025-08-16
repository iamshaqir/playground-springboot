package playground.sortingpagination.ex2.dto;

import playground.sortingpagination.ex2.entity.Employee;

public record EmployeeDTO(
        String firstName,
        String lastName,
        Integer birthYear,
        Integer salary
) {
    public EmployeeDTO(Employee employee) {
        this(employee.getFirstName(), employee.getLastName(), employee.getBirthYear(), employee.getSalary());
    }
}
