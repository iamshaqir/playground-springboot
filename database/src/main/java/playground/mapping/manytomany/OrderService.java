package playground.mapping.manytomany;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;

    public OrderDetails insert(OrderDetails orderDetails) {
        List<ProductDetails> products = orderDetails.getProductDetails()
                .stream()
                .map(product -> productService.findById(product.getProductId()))
                .toList();

        orderDetails.setProductDetails(products);
        return orderRepository.save(orderDetails);
    }

    public OrderDetails findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("couldn't find order with ID: " + id));
    }
}
