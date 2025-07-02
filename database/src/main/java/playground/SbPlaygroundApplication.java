package playground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("playground.entityinheritance.composite")
@ComponentScan(basePackages = "playground.entityinheritance.composite")
@EnableJpaRepositories(basePackages = "playground.entityinheritance.composite")
public class SbPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbPlaygroundApplication.class, args);
    }

}
//playground.entityinheritance.composite
//playground.entityinheritance.mappedsuperclass
//playground.entityinheritance.creditcard
//playground.entityinheritance.vehicle.VehicleService