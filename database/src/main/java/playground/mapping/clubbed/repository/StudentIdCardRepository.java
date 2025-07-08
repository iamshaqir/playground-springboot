package playground.mapping.clubbed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.mapping.clubbed.model.StudentIdCard;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, Long> {
}
