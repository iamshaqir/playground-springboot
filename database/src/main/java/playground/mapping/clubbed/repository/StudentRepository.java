package playground.mapping.clubbed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.mapping.clubbed.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
