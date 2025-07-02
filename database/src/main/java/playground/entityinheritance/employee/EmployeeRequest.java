package playground.entityinheritance.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record EmployeeRequest(

        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank String email,
        @NotNull LocalDate hireDate,
        @NotNull @Positive BigDecimal salary
) {
}
