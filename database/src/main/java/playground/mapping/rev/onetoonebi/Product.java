package playground.mapping.rev.onetoonebi;

import jakarta.persistence.*;
import lombok.*;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "stock")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    private BigDecimal productPrice;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stock_id", referencedColumnName = "stockId")
    private Stock stock;

    public ProductDTO toProductDTO() {
        return new ProductDTO(this);
    }

    public void setStock(Stock stock) {
        // if setting new stock for existing product i.e. updating existing product, remove first product
        if (stock != null && stock.getProduct().equals(this)) {
            stock.setProduct(null);
        }
        this.stock = stock;
        if (stock != null) {
            stock.setProduct(this);
        }
    }
}
