package playground.mapping.rev.onetoonebi.dto;

import lombok.extern.slf4j.Slf4j;
import playground.mapping.rev.onetoonebi.Product;

import java.math.BigDecimal;

@Slf4j
public record ProductDTO(int productId, String productName, BigDecimal productPrice, StockDTO stockDTO) {
    public ProductDTO(Product product) {
        this(product.getProductId(), product.getProductName(), product.getProductPrice(), product.getStock().toStockDTO());
    }
}
