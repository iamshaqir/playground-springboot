package playground.mapping.rev.onetoonebi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playground.mapping.rev.onetoonebi.StockStatus;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;
import playground.mapping.rev.onetoonebi.dto.StockDTO;
import playground.mapping.rev.onetoonebi.service.StockService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findOutOfStockProducts(@RequestParam("status") StockStatus stockStatus) {
        return new ResponseEntity<>(stockService.getOutOfStockProducts(stockStatus), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
        stockService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
