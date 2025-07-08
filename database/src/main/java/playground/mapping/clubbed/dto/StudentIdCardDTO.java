package playground.mapping.clubbed.dto;

import playground.mapping.clubbed.model.StudentIdCard;

public record StudentIdCardDTO(Long id, String cardNumber, StudentDTO studentDTO) {

    public StudentIdCardDTO(StudentIdCard studentIdCard) {
        this(studentIdCard.getId(), studentIdCard.getCardNumber(), studentIdCard.getStudent().toStudentDTO());
    }
}
