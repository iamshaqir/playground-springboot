package playground.sortingpagination.ex1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/save/{size}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@PathVariable("size") Integer size) {
        productService.save(size);
    }

    @GetMapping
    public ApiResponse<List<Product>> getProducts() {
        List<Product> products = productService.findAllProducts();
        return new ApiResponse<>(products.size(), products);
    }

    @GetMapping("/{field}")
    public ApiResponse<List<Product>> getProductsByFieldSortDesc(@PathVariable("field") String field) {
        List<Product> products = productService.findByFieldSort(field);
        return new ApiResponse<>(products.size(), products);
    }
}
