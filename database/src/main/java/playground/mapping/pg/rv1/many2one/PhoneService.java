package playground.mapping.pg.rv1.many2one;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneService {

    private final PhoneRepository phoneRepository;
    private final PersonRepository personRepository;
    private final Faker faker;

    public List<PhoneDTO> save(Integer size) {
        return personRepository.findAll().stream()
                .flatMap(person -> {
                    int noOfPhones = RandomGenerator.getDefault().nextInt(size / 20) + 1;
                    log.info("Created {} phones for Person : {}", noOfPhones, person);
                    List<Phone> phones = createPhone(person, noOfPhones);
                    return phoneRepository.saveAll(phones).stream();
                })
                .map(Phone::toPhoneDTO)
                .toList();
    }

    private List<Phone> createPhone(Person person, int numOfPhones) {
        return IntStream.range(0, numOfPhones)
                .mapToObj(i -> {
                    Phone phone = Phone.builder()
                            .number(faker.phoneNumber().phoneNumber())
                            .build();
                    phone.setPerson(person);
                    return phone;
                }).toList();
    }

}
