package playground.mapping.pg.rv1.one2one;

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

    private final PhoneRepository phoneRepository;
    private final Faker faker;

    public List<PhoneDTO> save(Integer size) {

        List<Phone> phones = IntStream.range(0, size)
                .mapToObj(i -> createPhone())
                .toList();
        return mapToPhoneDTO(phoneRepository.saveAll(phones));
    }

    private Phone createPhone() {
        Phone phone = Phone.builder()
                .number(faker.phoneNumber().phoneNumber())
                .build();

        PhoneDetails phoneDetails = PhoneDetails.builder()
                .provider(faker.company().name())
                .technology(faker.internet().domainName())
                .build();

        phone.addDetails(phoneDetails);
        return phone;
    }

    private List<PhoneDTO> mapToPhoneDTO(List<Phone> phones) {
        return phones.stream()
                .map(Phone::toPhoneDTO)
                .toList();
    }

}
