package playground.mapping.onetoone.uni;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final Faker faker;

    public List<UserDetails> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserDetails update(Long id, UserDetails userDetails) {
        UserDetails savedUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user with id: " + id));
        savedUser.setUserAddress(userDetails.getUserAddress());
        return userRepository.save(savedUser);
    }

    public void init() {
        Address address = faker.address();
        List<UserDetails> users = IntStream.range(0, 50)
                .mapToObj(user -> UserDetails.builder()
                        .name(faker.name().fullName())
                        .phone(faker.phoneNumber().phoneNumber())
                        .userAddress(
                                UserAddress.builder()
                                        .street(address.streetAddress())
                                        .city(address.city())
                                        .state(address.state())
                                        .country(address.country())
                                        .pincode(address.zipCode())
                                        .build()
                        )
                        .build())
                .toList();

        userRepository.saveAll(users);
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user with id: " + id));
        userRepository.deleteById(id);
    }
}
