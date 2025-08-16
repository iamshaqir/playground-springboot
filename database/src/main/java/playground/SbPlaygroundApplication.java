package playground;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@EnableJpaAuditing
@SpringBootApplication
@EnableTransactionManagement
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
@EntityScan("playground.sortingpagination.ex2")
@ComponentScan(basePackages = "playground.sortingpagination.ex2")
@EnableJpaRepositories(basePackages = "playground.sortingpagination.ex2")
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
    # PAGINATION and SORTING
    playground.sortingpagination

    #MAPPING
    playground.mapping.onetoone.uni
    playground.mapping.onetoone.bi
    playground.mapping.onetomany.uni
    playground.mapping.onetomany.bi
    playground.mapping.manytoone
    playground.mapping.manytomany
    playground.mapping.clubbed
    playground.mapping.rev
    playground.mapping.rev.onetoonebi
    playground.mapping.pg.rv1.one2one
    playground.mapping.pg.rv1.many2one
    playground.mapping.pg.rv1.one2many
    playground.mapping.pg.rv1.many2many

    #Hibernate Inheritance
    playground.entityinheritance.mappedsuperclass
    playground.entityinheritance.composite
    playground.entityinheritance.creditcard
    playground.entityinheritance.vehicle.VehicleService
 */