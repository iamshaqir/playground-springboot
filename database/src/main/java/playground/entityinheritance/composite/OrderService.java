package playground.entityinheritance.composite;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final Faker faker;

    private final OrderRepository orderRepository;

    public void saveAll() {
        List<Order> list = IntStream.range(0, 100)
                .mapToObj(order -> Order.builder()
                        .productName(faker.commerce().productName())
                        .category(faker.commerce().department())
                        .price(new BigDecimal(faker.commerce().price()))
                        .build())
                .toList();

        orderRepository.saveAll(list);

    }

}
