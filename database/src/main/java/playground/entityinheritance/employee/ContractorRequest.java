package playground.entityinheritance.employee;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContractorRequest(
        @Valid EmployeeRequest employee,
        @NotBlank String contractCompany,
        @NotNull LocalDate contractEndDate,
        @NotNull BigDecimal projectRate
) {
}
