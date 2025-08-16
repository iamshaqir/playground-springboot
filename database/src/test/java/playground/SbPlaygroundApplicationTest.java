package playground;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@SpringBootTest
@EnableTransactionManagement
@EntityScan("playground.sortingpagination")
@ComponentScan(basePackages = "playground.sortingpagination")
@EnableJpaRepositories(basePackages = "playground.sortingpagination")
class SbPlaygroundApplicationTest {

    @Test
    void contextLoads() {
    }
}