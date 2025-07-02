package playground.entityinheritance.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {


    public Employee toFullTimeEmployee(FullTimeEmployeeRequest request) {
        return FullTimeEmployee.builder()
                .firstName(request.employee().firstName())
                .lastName(request.employee().lastName())
                .email(request.employee().email())
                .salary(request.employee().salary())
                .hireDate(request.employee().hireDate())
                .annualBonus(request.annualBonus())
                .vacationDays(request.vacationDays())
                .build();
    }

    public Employee toPartTimeEmployee(PartTimeEmployeeRequest request) {
        return PartTimeEmployee.builder()
                .firstName(request.employee().firstName())
                .lastName(request.employee().lastName())
                .email(request.employee().email())
                .salary(request.employee().salary())
                .hireDate(request.employee().hireDate())
                .hourlyRate(request.hourlyRate())
                .hoursPerWeek(request.hoursPerWeek())
                .build();
    }

    public Employee toContractor(ContractorRequest request) {
        return ContractorEmployee.builder()
                .firstName(request.employee().firstName())
                .lastName(request.employee().lastName())
                .email(request.employee().email())
                .salary(request.employee().salary())
                .hireDate(request.employee().hireDate())
                .contractCompany(request.contractCompany())
                .contractEndDate(request.contractEndDate())
                .projectRate(request.projectRate())
                .build();
    }
}

