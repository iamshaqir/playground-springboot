package playground.mapping.onetoone.bi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAddressService {

    private final UserAddressRepository addressRepository;

    public UserAddress findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID : " + id));
    }
}
