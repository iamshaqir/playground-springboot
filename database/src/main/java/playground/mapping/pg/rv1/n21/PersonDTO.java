package playground.mapping.pg.rv1.n21;

import java.util.List;

public record PersonDTO(String firstName, String lastName, List<Phone> phoneNumbers) {
}
