package playground.sortingpagination.ex2.dto;

public record EmployeeInDTO(FilterDTO filter, int pageNum, int pageSize, String sort) {
}
