package playground.entityinheritance.mappedsuperclass;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    @Transactional
    public Product save(Product product) {
        log.info("Saving product: {}", product);
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Product available with id:" + id));
    }

    public void update(Product product) {
        Long productId = product.getId();
        Product savedProduct = repository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No Product available with id:" + productId));
        savedProduct.setPrice(product.getPrice());
        repository.save(savedProduct);
    }
}
