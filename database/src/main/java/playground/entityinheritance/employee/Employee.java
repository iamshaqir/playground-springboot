package playground.entityinheritance.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;

    @NotBlank
    @Column(nullable = false)
    String firstName;

    @NotBlank
    @Column(nullable = false)
    String lastName;

    @NotBlank
    @Column(nullable = false, unique = true)
    String email;

    @NotNull
    @Column(nullable = false)
    LocalDate hireDate;

    @NotNull
    @Positive
    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal salary;
}
