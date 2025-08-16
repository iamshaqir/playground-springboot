package playground.mapping.pg.rv1.many2many;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "courses")
public class Student {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private final Set<Course> courses = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    public Student(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Set<Course> getCourses() {
        log.info("--- @ManyToMany is lazy by default, triggering to get courses,should see a join query after this ---");
        return courses;
    }

    // Helper methods to manage bidirectionality
    public void addCourse(Course course) {
        if (course != null) {
            courses.add(course);
            course.getStudents().add(this);
        }
    }

    public void removeCourse(Course course) {
        if (course != null) {
            this.courses.remove(course);
            // Important for bidirectionality
            course.getStudents().remove(this);
        }
    }
}
