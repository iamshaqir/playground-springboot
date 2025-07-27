package playground.mapping.clubbed.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.mapping.clubbed.dto.StudentIdCardDTO;
import playground.mapping.clubbed.model.StudentIdCard;
import playground.mapping.clubbed.service.StudentIdService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/studentIdCard")
public class StudentIdCardController {

    private final StudentIdService studentIdService;

    @PostMapping("/create/one")
    public ResponseEntity<StudentIdCardDTO> save() {
        return new ResponseEntity<>(studentIdService.saveOne(), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<StudentIdCardDTO> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(studentIdService.findById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentIdCardDTO> updateStudentIdCard(@PathVariable("id") Long id,
                                                                @Valid @RequestBody StudentIdCard studentIdCard) {
        return new ResponseEntity<>(studentIdService.updateStudentIdCard(id, studentIdCard), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        studentIdService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
