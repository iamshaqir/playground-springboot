package playground.mapping.pg.rv1.many2one;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;

    @PostMapping("/create/{size}")
    public ResponseEntity<Void> save(@PathVariable("size") Integer size) {
        personService.save(size);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
