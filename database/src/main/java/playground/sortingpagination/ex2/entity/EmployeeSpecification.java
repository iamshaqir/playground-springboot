package playground.sortingpagination.ex2.entity;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import playground.sortingpagination.ex2.dto.FilterDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://medium.com/devxtalks/implementing-pagination-sorting-and-filtering-in-spring-boot-42615dbd74a7">Pagination and Sorting example</a>
 */
public class EmployeeSpecification {

    public static Specification<Employee> getEmployeeSpecification(FilterDTO filterDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filterDTO.firstName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("firstName"), filterDTO.firstName()));
            }

            if (filterDTO.lastName() != null) {
                predicates.add(criteriaBuilder.equal(root.get("lastName"), filterDTO.lastName()));
            }

            if (filterDTO.birthYear() != null) {
                predicates.add(criteriaBuilder.equal(root.get("birthYear"), filterDTO.birthYear()));
            }

            if (filterDTO.salary() != null) {
                predicates.add(criteriaBuilder.equal(root.get("salary"), filterDTO.salary()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
