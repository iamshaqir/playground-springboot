package playground.mapping.rev.onetoonebi.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.mapping.rev.onetoonebi.Product;
import playground.mapping.rev.onetoonebi.repository.ProductRepository;
import playground.mapping.rev.onetoonebi.Stock;
import playground.mapping.rev.onetoonebi.StockStatus;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Faker faker;

    public ProductDTO saveOne() {

        Product product = Product.builder()
                .productName(faker.commerce().productName())
                .productPrice(new BigDecimal(faker.commerce().price()))
                .build();

        Stock stock = Stock.builder()
                .stockQuantity(faker.number().randomDigit())
                .stockStatus(StockStatus.IN_STOCK)
                .build();

        product.setStock(stock);

        return productRepository.save(product).toProductDTO();
    }
}
