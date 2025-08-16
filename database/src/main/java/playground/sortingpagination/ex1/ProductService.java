package playground.sortingpagination.ex1;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final Faker faker;
    private final ProductRepository productRepository;

    public void save(int size) {

        List<Product> products = IntStream.rangeClosed(1, size)
                .mapToObj(i -> Product.builder()
                        .name(faker.commerce().productName())
                        .material(faker.commerce().material())
                        .color(faker.commerce().color())
                        .price(Double.parseDouble(faker.commerce().price()))
                        .quantity(faker.number().numberBetween(10, 80))
                        .build())
                .toList();

        log.info("Created {} products", size);
        productRepository.saveAll(products);
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> findByFieldSort(String fieldName) {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, fieldName));
    }

}
