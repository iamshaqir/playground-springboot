package playground.mapping.rev.onetoonebi;

import jakarta.persistence.*;
import lombok.*;
import playground.mapping.rev.onetoonebi.dto.StockDTO;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "product")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private StockStatus stockStatus;

    @OneToOne(mappedBy = "stock")
    private Product product;

    public StockDTO toStockDTO() {
        return new StockDTO(this);
    }
}
