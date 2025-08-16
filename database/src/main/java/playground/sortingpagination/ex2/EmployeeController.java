package playground.sortingpagination.ex2;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.sortingpagination.ex2.dto.EmployeeDTO;
import playground.sortingpagination.ex2.dto.EmployeeInDTO;
import playground.sortingpagination.ex2.dto.FilterDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/save/{size}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@PathVariable("size") Integer size) {
        employeeService.save(size);
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<EmployeeDTO>> get(@Valid @RequestBody FilterDTO filter,
                                                 @RequestParam(name = "pageNum", defaultValue = "0") int pageNum,
                                                 @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                                 @RequestParam(
                                                         name = "sort",
                                                         defaultValue = "[{\"field\":\"firstName\",\"direction\":\"asc\"}]"
                                                 )
                                                 String sort) {
        return ResponseEntity.ok(employeeService.get(new EmployeeInDTO(filter, pageNum, pageSize, sort)));
    }
}
