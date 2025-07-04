package playground.mapping.onetomany;

import java.util.List;

public record UserDetailsDTO(String name, String phone, List<OrderDetails> orders) {

    public UserDetailsDTO(UserDetails userDetails) {
        this(userDetails.getName(), userDetails.getPhone(), userDetails.getOrderDetails());
    }

    @Override
    public List<OrderDetails> orders() {
        System.out.println("Querying Order details ::::");
        return orders;
    }
}
