package playground.mapping.rev.onetoonebi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import playground.mapping.rev.onetoonebi.Stock;
import playground.mapping.rev.onetoonebi.StockStatus;
import playground.mapping.rev.onetoonebi.dto.ProductDTO;
import playground.mapping.rev.onetoonebi.repository.StockRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<ProductDTO> getOutOfStockProducts(StockStatus stockStatus) {
        List<Stock> outOfStockProducts = stockRepository.findByStockStatus(stockStatus);

        if (outOfStockProducts == null || outOfStockProducts.isEmpty()) {
            throw new RuntimeException("No Out of stock products");
        }
        return outOfStockProducts.stream()
                .map(stock -> stock.getProduct().toProductDTO())
                .toList();
    }

    public void deleteById(Integer id) {
        stockRepository.deleteById(id);
    }
}
