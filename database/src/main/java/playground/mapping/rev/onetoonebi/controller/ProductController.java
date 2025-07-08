package playground.mapping.rev.onetoonebi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;
import playground.mapping.rev.onetoonebi.service.ProductService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/one")
    public ResponseEntity<ProductDTO> save() {
        return new ResponseEntity<>(productService.saveOne(), HttpStatus.CREATED);
    }
}
