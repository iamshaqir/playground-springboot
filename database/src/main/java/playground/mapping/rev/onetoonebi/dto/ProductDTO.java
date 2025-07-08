package playground.mapping.rev.onetoonebi.dto;

import playground.mapping.rev.onetoonebi.Product;

import java.math.BigDecimal;

public record ProductDTO(int productId, String productName, BigDecimal productPrice, StockDTO stockDTO) {
    public ProductDTO(Product product) {
        this(product.getProductId(), product.getProductName(), product.getProductPrice(), product.getStock().toStockDTO());
    }
}
