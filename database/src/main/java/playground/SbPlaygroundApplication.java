package playground;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import playground.entityinheritance.composite.OrderService;

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

    @Bean
    public CommandLineRunner controller(OrderService orderService) {
        return (args -> orderService.saveAll());
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

}
//playground.entityinheritance.composite
//playground.entityinheritance.mappedsuperclass
//playground.entityinheritance.creditcard
//playground.entityinheritance.vehicle.VehicleService