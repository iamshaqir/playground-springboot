package playground.mapping.rev.onetoonebi.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import playground.mapping.rev.onetoonebi.Product;
import playground.mapping.rev.onetoonebi.StockStatus;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStock_StockStatus(StockStatus stockStatus);
    @Override
    @EntityGraph(attributePaths = "stock")
    List<Product> findAll();

    @Query("SELECT p FROM Product p JOIN FETCH p.stock")
    List<Product> findAllEagerly();

}
