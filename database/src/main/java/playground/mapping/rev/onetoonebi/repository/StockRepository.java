package playground.mapping.rev.onetoonebi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.mapping.rev.onetoonebi.Stock;
import playground.mapping.rev.onetoonebi.StockStatus;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByStockStatus(StockStatus stockStatus);
}
