package playground.mapping.onetomany;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Faker faker;

    public List<UserDetails> findAll() {
        return userRepository.findAll();
    }

    public UserDetailsDTO findById(Long id) {
        UserDetails userDetails = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        return userDetails.toUserDetailsDTO();
    }


    public void init() {
        Address address = faker.address();
        List<UserDetails> users = IntStream.range(0, 50)
                .mapToObj(user -> UserDetails.builder()
                        .name(faker.name().fullName())
                        .phone(faker.phoneNumber().phoneNumber())
                        .orderDetails(orderDetails())
                        .build())
                .toList();

        userRepository.saveAll(users);
    }

    private List<OrderDetails> orderDetails() {
        Random random = new Random();
        return IntStream.range(0, random.nextInt(5) + 1)
                .mapToObj(order -> OrderDetails.builder()
                        .productName(faker.commerce().productName())
                        .category(faker.commerce().department())
                        .price(new BigDecimal(faker.commerce().price()))
                        .build())
                .toList();
    }
}
