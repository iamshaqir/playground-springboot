package playground.mapping.rev.onetoonebi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import playground.mapping.rev.onetoonebi.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
