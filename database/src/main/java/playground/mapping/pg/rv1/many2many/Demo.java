package playground.mapping.pg.rv1.many2many;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Demo implements CommandLineRunner {

    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Student alice = new Student("alice", "alice@gmail.com");
        Student bob = new Student("bob", "bob@gmail.com");
        Student caren = new Student("caren", "caren@hotmail.com");
        Student daniel = new Student("daniel", "daniel@gmail.com");
        Student emma = new Student("emma", "emma@hotmail.com");
        Student frank = new Student("frank", "frank@gmail.com");

        Course maths = new Course("maths", 180);
        Course physics = new Course("physics", 90);
        Course chemistry = new Course("chemistry", 50);

        alice.addCourse(maths);
        alice.addCourse(physics);
        alice.addCourse(chemistry);

        bob.addCourse(physics);
        bob.addCourse(chemistry);

        caren.addCourse(physics);
        caren.addCourse(chemistry);

        daniel.addCourse(maths);
        daniel.addCourse(physics);
        daniel.addCourse(chemistry);

        emma.addCourse(maths);
        emma.addCourse(physics);

        frank.addCourse(physics);
        frank.addCourse(chemistry);

        // Saving owning side saves inverse side as well, bidirectional mapping
        studentRepo.saveAll(List.of(alice, bob, caren, daniel, emma, frank));

        // selection from the owning side
        Student fetchedAlice = studentRepo.findById(alice.getId()).orElseThrow();
        log.info("Student: {}", fetchedAlice);

        List<String> aliceEnrolledCourses = fetchedAlice.getCourses().stream().map(Course::getTitle).toList();
        log.info("[{}] enrolled for {} courses", fetchedAlice.getName(), aliceEnrolledCourses);
    }
}
