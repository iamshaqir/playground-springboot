package playground.mapping.clubbed.dto;

import playground.mapping.clubbed.model.Student;

public record StudentDTO(Long id, String firstName, String lastName,
                         String email, Integer age) {

    public StudentDTO(Student student) {
        this(student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(), student.getAge());
    }
}
