package playground.mapping.onetoone.bi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/addresses")
public class UserAddressController {

    private final UserAddressService userAddressService;

    @GetMapping("/{id}")
    public ResponseEntity<UserAddress> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userAddressService.findById(id));
    }
}
