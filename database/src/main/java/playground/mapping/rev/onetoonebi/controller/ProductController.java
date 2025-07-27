package playground.mapping.rev.onetoonebi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.mapping.rev.onetoonebi.StockStatus;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;
import playground.mapping.rev.onetoonebi.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create/one")
    public ResponseEntity<ProductDTO> save() {
        return new ResponseEntity<>(productService.saveOne(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/create/{size}")
    public ResponseEntity<List<ProductDTO>> save(@PathVariable("size") Integer size) {
        return new ResponseEntity<>(productService.save(size), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/stockStatus")
    public ResponseEntity<List<ProductDTO>> findOutOfStockProducts(@RequestParam("status") StockStatus stockStatus) {
        return new ResponseEntity<>(productService.getOutOfStockProducts(stockStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        productService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/disAssociate/{id}")
    public ResponseEntity<Void> disAssociating(@PathVariable("id") Integer id) {
        productService.disAssociate(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
