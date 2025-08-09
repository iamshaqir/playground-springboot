package playground;

import com.github.javafaker.Faker;
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
@EntityScan("playground.mapping.pg.rv1.n21")
@ComponentScan(basePackages = "playground.mapping.pg.rv1.n21")
@EnableJpaRepositories(basePackages = "playground.mapping.pg.rv1.n21")
public class SbPlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbPlaygroundApplication.class, args);
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
    playground.mapping.onetomany.uni
    playground.mapping.onetomany.bi
    playground.mapping.manytoone
    playground.mapping.manytomany
    playground.mapping.clubbed
    playground.mapping.rev
    playground.mapping.pg.rv1.121
    playground.mapping.pg.rv1.n21

    #Hibernate Inheritance
    playground.entityinheritance.mappedsuperclass
    playground.entityinheritance.composite
    playground.entityinheritance.creditcard
    playground.entityinheritance.vehicle.VehicleService
 */