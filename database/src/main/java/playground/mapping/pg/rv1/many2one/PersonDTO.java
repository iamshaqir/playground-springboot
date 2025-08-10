package playground.mapping.pg.rv1.many2one;

import java.util.List;

public record PersonDTO(String firstName, String lastName, List<Phone> phoneNumbers) {
}
