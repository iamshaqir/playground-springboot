package playground.sortingpagination.ex1;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import playground.sortingpagination.ex1.Product;
import playground.sortingpagination.ex1.ProductRepository;
import playground.sortingpagination.ex1.ProductService;

import java.util.List;

@Slf4j
@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService service;

    @BeforeEach
    void beforeEach() {
        service.save(10);
    }

    @Test
    void testSortByMethodAndDirection() {
        Sort name = Sort.by("name");
        List<Product> all = productRepository.findAll(name);
        log.info("All products sorted by [name] in ASC: {}", all.stream().map(Product::getName).toList());

        Sort nameDesc = Sort.by(Sort.Direction.DESC, "name");
        List<Product> allDesc = productRepository.findAll(nameDesc);
        log.info("All products sorted by [name] in DESC: {}", allDesc.stream().map(Product::getName).toList());
    }

    @Test
    void testSortByWithMultipleProperties() {
        Sort sort = Sort.by("color", "name");
        List<Product> products = productRepository.findAll(sort);
        log.info("All products sorted by [color, name] in ASC\n[Color]: {}\n[Name]: {}",
                products.stream().map(Product::getColor).toList(),
                products.stream().map(Product::getName).toList());

        Sort sortDesc = Sort.by("color", "name").descending();
        List<Product> productsDesc = productRepository.findAll(sortDesc);
        log.info("All products sorted by [color, name] in DESC\n[Color]: {}\n[Name]: {}",
                productsDesc.stream().map(Product::getColor).toList(),
                productsDesc.stream().map(Product::getName).toList());
    }

    @Test
    void testSortByWithOrderObjects() {
        Sort sort = Sort.by(Sort.Order.desc("price"), Sort.Order.asc("quantity"));
        List<Product> productsDesc = productRepository.findAll(sort);
        log.info("--- Products sorted [Price] by DESC & [Quantity] by ASC ---\n[Price]: {}\n[Quantity]: {}",
                productsDesc.stream().map(Product::getPrice).toList(),
                productsDesc.stream().map(Product::getQuantity).toList());
    }

    @Test
    void testSortAndMethod() {
        Sort primarySort = Sort.by("material");
        Sort combinedSort = primarySort.and(Sort.by("price").descending());

        List<Product> products = productRepository.findAll(combinedSort);
        log.info("--- Products [Material] & [Quantity] sorted by DESC ---\n[Material]: {}\n[Quantity]: {}",
                products.stream().map(Product::getPrice).toList(),
                products.stream().map(Product::getQuantity).toList());
    }

    @Test
    void playground() {
        List<String> sortFields = List.of("name", "quantity");

        Sort sort = Sort.by(sortFields.stream()
                .map(Sort.Order::asc)
                .toList());
    }
}