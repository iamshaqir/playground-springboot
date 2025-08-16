package playground.sortingpagination.ex2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import playground.sortingpagination.ex2.dto.EmployeeDTO;
import playground.sortingpagination.ex2.dto.EmployeeInDTO;
import playground.sortingpagination.ex2.dto.FilterDTO;
import playground.sortingpagination.ex2.dto.SortDTO;
import playground.sortingpagination.ex2.entity.Employee;

import java.util.List;
import java.util.stream.IntStream;

import static playground.sortingpagination.ex2.entity.EmployeeSpecification.getEmployeeSpecification;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final Faker faker;
    private final ObjectMapper mapper;
    private final EmployeeRepository employeeRepository;

    public void save(int size) {

        List<Employee> employees = IntStream.range(0, size)
                .mapToObj(i -> Employee.builder()
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .birthYear(faker.number().numberBetween(1980, 2025))
                        .salary(faker.number().numberBetween(30000, 150000))
                        .build()).toList();
        employeeRepository.saveAll(employees);
    }

    public Page<EmployeeDTO> get(EmployeeInDTO employeeInDTO) {
        FilterDTO filter = employeeInDTO.filter();
        log.info("Filter conditions: {}", filter);
        Specification<Employee> filterPredicates = getEmployeeSpecification(filter);

        List<SortDTO> sortDTOList = sortStringToSortDTO(employeeInDTO.sort());
        Sort sort = sortFromSortDTO(sortDTOList);
        log.info("Sort conditions: {}", sortDTOList);
        PageRequest pageRequest = PageRequest.of(employeeInDTO.pageNum(), employeeInDTO.pageSize(), sort);

        Page<Employee> employeesPage = employeeRepository.findAll(filterPredicates, pageRequest);
        return employeesPage.map(Employee::toEmployeeDTO);
    }

    private List<SortDTO> sortStringToSortDTO(String sort) {

        try {
            log.info("Parsing [sort] string to json: {}", sort);
            return mapper.readValue(sort, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error("Exception during parsing [sort] string to json: {}", e.getMessage());
        }
        return null;
    }

    private Sort sortFromSortDTO(List<SortDTO> sortDTOList) {

        if (sortDTOList == null || sortDTOList.isEmpty()) {
            return Sort.unsorted();
        }

        List<Sort.Order> sortOrderList = sortDTOList.stream()
                .map(sortDTO -> new Sort.Order("desc".equalsIgnoreCase(sortDTO.direction())
                        ? Sort.Direction.DESC
                        : Sort.Direction.ASC,
                        sortDTO.field())
                ).toList();

        return Sort.by(sortOrderList);
    }
}
