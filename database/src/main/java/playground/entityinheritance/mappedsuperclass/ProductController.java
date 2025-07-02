package playground.entityinheritance.mappedsuperclass;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(service.save(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@Valid @PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody Product product) {
        service.update(product);
        return ResponseEntity.accepted().build();
    }

}
