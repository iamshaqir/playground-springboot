package playground.mapping.rev.onetoonebi.dto;

import playground.mapping.rev.onetoonebi.Stock;
import playground.mapping.rev.onetoonebi.StockStatus;

public record StockDTO(int stockId, int stockQuantity, StockStatus stockStatus) {

    public StockDTO(Stock stock) {
        this(stock.getStockId(), stock.getStockQuantity(), stock.getStockStatus());
    }
}
