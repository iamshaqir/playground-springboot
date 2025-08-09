package playground.mapping.pg.rv1;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneService {

    private final playground.mapping.pg.rv1.PhoneRepository phoneRepository;
    private final Faker faker;

    public List<playground.mapping.pg.rv1.PhoneDTO> save(Integer size) {

        List<playground.mapping.pg.rv1.Phone> phones = IntStream.range(0, size)
                .mapToObj(i -> createPhone())
                .toList();
        return mapToPhoneDTO(phoneRepository.saveAll(phones));
    }

    private playground.mapping.pg.rv1.Phone createPhone() {
        playground.mapping.pg.rv1.Phone phone = playground.mapping.pg.rv1.Phone.builder()
                .number(faker.phoneNumber().phoneNumber())
                .build();

        playground.mapping.pg.rv1.PhoneDetails phoneDetails = playground.mapping.pg.rv1.PhoneDetails.builder()
                .provider(faker.company().name())
                .technology(faker.internet().domainName())
                .build();

        phone.addDetails(phoneDetails);
        return phone;
    }

    private List<playground.mapping.pg.rv1.PhoneDTO> mapToPhoneDTO(List<playground.mapping.pg.rv1.Phone> phones) {
        return phones.stream()
                .map(playground.mapping.pg.rv1.Phone::toPhoneDTO)
                .toList();
    }

}
