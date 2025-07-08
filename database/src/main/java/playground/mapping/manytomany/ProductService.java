package playground.mapping.manytomany;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Faker faker;

    public ProductDetails findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find product with ID: " + id));
    }

    public List<ProductDetails> findAll() {
        return productRepository.findAll();
    }

    public void init() {
        List<ProductDetails> products = IntStream.range(0, 100)
                .mapToObj(product -> ProductDetails.builder()
                        .name(faker.commerce().productName())
                        .price(new BigDecimal(faker.commerce().price()))
                        .build()
                ).toList();

        productRepository.saveAll(products);
    }
}
