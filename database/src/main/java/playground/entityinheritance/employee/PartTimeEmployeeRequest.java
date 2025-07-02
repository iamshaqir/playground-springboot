package playground.entityinheritance.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PartTimeEmployeeRequest(
        @Valid EmployeeRequest employee,
        @NotNull @Positive Integer hoursPerWeek,
        @NotNull @Positive BigDecimal hourlyRate
) {
}
