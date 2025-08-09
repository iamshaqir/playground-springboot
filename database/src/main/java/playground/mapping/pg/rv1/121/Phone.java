package playground.mapping.pg.rv1;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import playground.mapping.pg.rv1.n21.Person;

@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "`number`")
    private String number;

    private Person person;
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "phone"
    )
    private playground.mapping.pg.rv1.PhoneDetails details;

    public void addDetails(playground.mapping.pg.rv1.PhoneDetails details) {
        details.setPhone(this);
        this.details = details;
    }

    public void removeDetails() {
        if (details != null) {
            details.setPhone(null);
            this.details = null;
        }
    }

    public playground.mapping.pg.rv1.PhoneDTO toPhoneDTO() {
        return new playground.mapping.pg.rv1.PhoneDTO(this);
    }
}
