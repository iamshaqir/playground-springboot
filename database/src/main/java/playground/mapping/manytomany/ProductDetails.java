package playground.mapping.manytomany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(property = "productId", generator = ObjectIdGenerators.PropertyGenerator.class)
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;
    private BigDecimal price;

    @ManyToMany(mappedBy = "productDetails")
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public void add(OrderDetails order) {
        this.orderDetails.add(order);
        order.getProductDetails().add(this);
    }
}
