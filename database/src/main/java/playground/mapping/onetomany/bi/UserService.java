package playground.mapping.onetomany.bi;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Slf4j
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

    public UserDetails orphanRemoval(Long id) {
        UserDetails userDetails = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
        log.debug("Querying order table lazily ::::::");
        List<OrderDetails> orderDetails = userDetails.getOrderDetails();
        log.info("Removing order: {}", orderDetails.get(0));
        orderDetails.remove(0);
        return userRepository.save(userDetails);
    }


    public void init() {
        Address address = faker.address();
        List<UserDetails> users = IntStream.range(0, 50)
                .mapToObj(user -> UserDetails.builder()
                        .name(faker.name().fullName())
                        .phone(faker.phoneNumber().phoneNumber())
                        .build())
                .toList();

        users.forEach(user -> {
            Random random = new Random();
            IntStream.range(0, random.nextInt(5) + 1)
                    .forEach(action -> user.add(createOrder()));
        });

        userRepository.saveAll(users);
    }

    private OrderDetails createOrder() {
        return OrderDetails.builder()
                .productName(faker.commerce().productName())
                .category(faker.commerce().department())
                .price(new BigDecimal(faker.commerce().price()))
                .build();
    }
}
