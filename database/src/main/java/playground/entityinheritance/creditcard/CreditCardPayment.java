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
@PrimaryKeyJoinColumn(name = "CreditCardPaymentId")
@EqualsAndHashCode(callSuper = true)
public class CreditCardPayment extends Payment {

    private String cardNumber;
    private String cardType;
}
