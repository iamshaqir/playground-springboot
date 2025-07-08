package playground.mapping.clubbed.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import playground.mapping.clubbed.dto.StudentIdCardDTO;
import playground.mapping.clubbed.model.Student;
import playground.mapping.clubbed.model.StudentIdCard;
import playground.mapping.clubbed.repository.StudentIdCardRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentIdService {

    private final StudentIdCardRepository studentIdCardRepository;
    private final Faker faker;

    public StudentIdCardDTO saveOne() {

        StudentIdCard studentIdCard = StudentIdCard.builder()
                .cardNumber(String.valueOf(faker.number().randomNumber(12, true)))
                .build();

        Student student = Student.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .age(faker.number().numberBetween(5, 24))
                .build();

        studentIdCard.setStudent(student);
        return studentIdCardRepository.save(studentIdCard).toStudentIdCardDTO();
    }

    public List<StudentIdCardDTO> findAll() {
        return studentIdCardRepository.findAll()
                .stream()
                .map(StudentIdCard::toStudentIdCardDTO)
                .toList();
    }

    public StudentIdCardDTO findById(Long id) {
        StudentIdCard studentIdCard = studentIdCardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find Student id card with ID: " + id));
        log.info("Returned Student Id card entity: {}", studentIdCard);
        return studentIdCard.toStudentIdCardDTO();
    }

    public void deleteById(Long id) {
        if (!studentIdCardRepository.existsById(id)) {
            throw new RuntimeException("Couldn't find Student id card with ID: " + id);
        }
        studentIdCardRepository.deleteById(id);
    }
}
