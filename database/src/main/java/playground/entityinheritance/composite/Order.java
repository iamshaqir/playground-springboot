package playground.entityinheritance.composite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ORDER_DETAILS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQUENCE_GEN")
    @SequenceGenerator(name = "ORDER_SEQUENCE_GEN", sequenceName = "ORDER_SEQUENCE", initialValue = 1, allocationSize = 20)
    private Long id;
    private String productName;
    private String category;
    private BigDecimal price;
}
