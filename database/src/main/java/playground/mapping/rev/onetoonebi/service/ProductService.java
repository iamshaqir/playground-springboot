package playground.mapping.rev.onetoonebi.service;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.mapping.rev.onetoonebi.Product;
import playground.mapping.rev.onetoonebi.Stock;
import playground.mapping.rev.onetoonebi.StockStatus;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;
import playground.mapping.rev.onetoonebi.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Faker faker;

    public ProductDTO saveOne() {
        return productRepository.save(createProduct()).toProductDTO();
    }

    private Product createProduct() {

        Product product = Product.builder()
                .productName(faker.commerce().productName())
                .productPrice(new BigDecimal(faker.commerce().price()))
                .build();

        int stockQuantity = faker.number().randomDigit();
        Stock stock = Stock.builder()
                .stockQuantity(stockQuantity)
                .product(product)
                .stockStatus(stockQuantity != 0 ? StockStatus.IN_STOCK : StockStatus.OUT_OF_STOCK)
                .build();

        product.setStock(stock);
        return product;
    }

    public List<ProductDTO> save(Integer size) {

        List<Product> products = IntStream.range(0, size)
                .mapToObj(i -> createProduct())
                .toList();
        return mapToProductDTO(productRepository.saveAll(products));
    }

    public List<ProductDTO> findAll() {
        return mapToProductDTO(productRepository.findAll());
    }

    private List<ProductDTO> mapToProductDTO(List<Product> products) {
        return products.stream()
                .map(Product::toProductDTO)
                .toList();
    }

    public List<ProductDTO> getOutOfStockProducts(StockStatus stockStatus) {
        List<Product> outOfStockProducts = productRepository.findByStock_StockStatus(stockStatus);

        if (outOfStockProducts == null || outOfStockProducts.isEmpty()) {
            throw new RuntimeException("No Out of stock products");
        }
        return outOfStockProducts.stream()
                .map(Product::toProductDTO)
                .toList();
    }

    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }

    public void disAssociate(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setStock(null);
        productRepository.save(product);
    }

    public ProductDTO findById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow();
        return product.toProductDTO();
    }
}
