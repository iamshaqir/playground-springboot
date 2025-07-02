package playground.entityinheritance.creditcard;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "CashPaymentId")
@EqualsAndHashCode(callSuper = true)
public class CashPayment extends Payment {
    private String currency;
}
