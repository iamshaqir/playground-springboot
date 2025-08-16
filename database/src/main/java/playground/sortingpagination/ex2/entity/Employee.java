package playground.sortingpagination.ex2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import playground.sortingpagination.ex2.dto.EmployeeDTO;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Integer birthYear;

    private Integer salary;

    public EmployeeDTO toEmployeeDTO() {
        return new EmployeeDTO(this);
    }
}
