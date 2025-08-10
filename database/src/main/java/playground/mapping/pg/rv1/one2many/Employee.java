package playground.mapping.pg.rv1.one2many;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Employee")
@ToString(exclude = "department")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    // Owning side, has foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "DEPARTMENT_ID",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "EMPLOYEE_ID_FK")
    )
    private Department department;
}
