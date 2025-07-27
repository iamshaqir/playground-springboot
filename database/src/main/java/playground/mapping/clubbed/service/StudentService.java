package playground.mapping.clubbed.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import playground.mapping.clubbed.dto.StudentDTO;
import playground.mapping.clubbed.model.Student;
import playground.mapping.clubbed.repository.StudentRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final Faker faker;

    public List<StudentDTO> findAll() {
        return studentRepository.findAll()
                .stream()
                .map(Student::toStudentDTO)
                .toList();
    }

    public StudentDTO findById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find student with ID: " + id));
        log.info("Returned Student entity: {}", student);
        return student.toStudentDTO();
    }

    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Couldn't find Student id card with ID: " + id);
        }
        studentRepository.deleteById(id);
    }
}
