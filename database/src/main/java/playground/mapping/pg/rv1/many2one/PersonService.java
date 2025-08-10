package playground.mapping.pg.rv1.many2one;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final Faker faker;

    public void save(Integer size) {
        List<Person> persons = IntStream.range(0, size)
                .mapToObj(i -> createPerson())
                .toList();

        personRepository.saveAll(persons);
    }

    private Person createPerson() {
        return Person.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .build();
    }

}
