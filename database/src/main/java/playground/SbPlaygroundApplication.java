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

@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EntityScan("playground.mapping.onetomany")
@ComponentScan(basePackages = "playground.mapping.onetomany")
@EnableJpaRepositories(basePackages = "playground.mapping.onetomany")
public class SbPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbPlaygroundApplication.class, args);
    }

    @Bean
    public CommandLineRunner controller() {
        return args -> {
//            userService.saveAll(); UserService userService
//            orderService.saveAll(); OrderService orderService
        };
    }

    @Bean
    public Faker faker() {
        return new Faker();
    }

}
/*
    #MAPPING
    playground.mapping.onetoone.uni
    playground.mapping.onetoone.bi
    playground.mapping.onetomany

    #Hibernate Inheritance
    playground.entityinheritance.composite
    playground.entityinheritance.mappedsuperclass
    playground.entityinheritance.creditcard
    playground.entityinheritance.vehicle.VehicleService
 */