package playground.entityinheritance.employee;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("CONTRACTOR")
public class ContractorEmployee extends Employee {

    String contractCompany;

    LocalDate contractEndDate;

    @Column(precision = 10, scale = 2)
    BigDecimal projectRate;
}
