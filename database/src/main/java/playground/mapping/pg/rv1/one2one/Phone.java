package playground.mapping.pg.rv1.one2one;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import playground.mapping.pg.rv1.many2one.Person;

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

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "phone"
    )
    private PhoneDetails details;

    public void addDetails(PhoneDetails details) {
        details.setPhone(this);
        this.details = details;
    }

    public void removeDetails() {
        if (details != null) {
            details.setPhone(null);
            this.details = null;
        }
    }

    public PhoneDTO toPhoneDTO() {
        return new PhoneDTO(this);
    }
}
