package playground.mapping.onetomany.bi;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String phone;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<>();

    public void add(OrderDetails orderDetails) {
        this.orderDetails.add(orderDetails);
        orderDetails.setUserDetails(this);
    }

    public void remove(OrderDetails orderDetails) {
        this.orderDetails.remove(orderDetails);
        orderDetails.setUserDetails(null);
    }

    public UserDetailsDTO toUserDetailsDTO() {
        return new UserDetailsDTO(this);
    }
}
