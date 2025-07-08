package playground.mapping.clubbed.model;

import jakarta.persistence.*;
import lombok.*;
import playground.mapping.clubbed.dto.StudentIdCardDTO;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "student")
public class StudentIdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    public StudentIdCardDTO toStudentIdCardDTO() {
        return new StudentIdCardDTO(this);
    }
}
