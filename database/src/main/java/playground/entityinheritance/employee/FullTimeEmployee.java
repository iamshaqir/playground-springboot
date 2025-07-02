package playground.entityinheritance.employee;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("FULL_TIME")
@EqualsAndHashCode(callSuper = true)
public class FullTimeEmployee extends Employee {

    @NotNull
    @Positive
    @Column(precision = 10, scale = 2)
    BigDecimal annualBonus;

    @NotNull
    @Positive
    Integer vacationDays;

}