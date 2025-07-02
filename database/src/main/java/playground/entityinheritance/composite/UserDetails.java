package playground.entityinheritance.composite;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserDetailCK.class)
@Table(
        name = "USER_DETAILS",
        schema = "MAPPING",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "phone"), // single unique constraint
                @UniqueConstraint(columnNames = {"name", "email"}) //composite unique constraint
        },
        indexes = {
                @Index(name = "idx_phone", columnList = "phone"), // index on single column
                @Index(name = "idx_name_email", columnList = "name,email") // index on composite column
        }
)
public class UserDetails {

    private Long id;
    @Id
    private String name;
    private String email;
    private String phone;
    @Id
    private String address;
}
