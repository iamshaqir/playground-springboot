package playground.entityinheritance.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record FullTimeEmployeeRequest(
        @Valid EmployeeRequest employee,
        @NotNull @Positive BigDecimal annualBonus,
        @NotNull @Positive Integer vacationDays
) {
}
