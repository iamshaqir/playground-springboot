package playground.mapping.onetoone.uni;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddressCK implements Serializable {

    private String street;
    private String pincode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAddressCK that)) return false;
        return Objects.equals(getStreet(), that.getStreet()) && Objects.equals(getPincode(), that.getPincode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreet(), getPincode());
    }
}
