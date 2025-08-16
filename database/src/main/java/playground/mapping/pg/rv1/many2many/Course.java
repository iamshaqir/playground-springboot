package playground.mapping.pg.rv1.many2many;

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
@ToString(exclude = "students")
public class Course {

    @ManyToMany(mappedBy = "courses")
    private final Set<Student> students = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer duration;

    public Course(String title, Integer duration) {
        this.title = title;
        this.duration = duration;
    }

    // Helper methods to manage bidirectionality
    public void addStudent(Student student) {
        this.students.add(student);
        // Important for bidirectionality
        student.getCourses().add(this);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getCourses().remove(this);
    }

}
