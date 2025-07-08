package playground.mapping.clubbed.model;

import jakarta.persistence.*;
import lombok.*;
import playground.mapping.clubbed.dto.StudentDTO;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "studentIdCard")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private Integer age;

    @OneToOne(mappedBy = "student")
    private StudentIdCard studentIdCard;

    public StudentDTO toStudentDTO() {
        return new StudentDTO(this);
    }

}
