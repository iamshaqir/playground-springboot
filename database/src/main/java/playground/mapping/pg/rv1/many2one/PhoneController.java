package playground.mapping.pg.rv1.many2one;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/phones")
public class PhoneController {

    private final PhoneService phoneService;

    @PostMapping("/create/{size}")
    public ResponseEntity<List<PhoneDTO>> save(@PathVariable("size") Integer size) {
        return new ResponseEntity<>(phoneService.save(size), HttpStatus.CREATED);
    }
}
