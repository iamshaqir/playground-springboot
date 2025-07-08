package playground.mapping.manytomany;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDetails> save(@Valid @RequestBody OrderDetails orderDetails) {
        return ResponseEntity.ok(orderService.insert(orderDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetails> save(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }
}
