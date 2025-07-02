package playground.entityinheritance.employee;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@DiscriminatorValue("PART_TIME")
public class PartTimeEmployee extends Employee {


    @Positive
    Integer hoursPerWeek;

    @Positive
    @Column(precision = 10, scale = 2)
    BigDecimal hourlyRate;
}
