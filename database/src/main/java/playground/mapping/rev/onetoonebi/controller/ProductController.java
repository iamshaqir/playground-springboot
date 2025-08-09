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

    @PostMapping("/create/{size}")
    public ResponseEntity<List<ProductDTO>> save(@PathVariable("size") Integer size) {
        return new ResponseEntity<>(productService.save(size), HttpStatus.CREATED);
    }

    @GetMapping("/eager/{id}")
    public ResponseEntity<ProductDTO> findByIdEager(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.findByIdEager(id), HttpStatus.OK);
    }

    @GetMapping("/lazy/{id}")
    public ResponseEntity<ProductDTO> findByIdLazy(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(productService.findByIdLazy(id), HttpStatus.OK);
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

/*
--  SELECT p1_0.product_id, p1_0.product_name, s1_0.stock_id, s1_0.stock_quantity
    FROM product p1_0 LEFT OUTER JOIN stock s1_0
    ON s1_0.stock_id = p1_0.stock_id
 */