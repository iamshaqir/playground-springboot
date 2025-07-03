package playground.entityinheritance.composite;

import java.io.Serializable;
import java.util.Objects;

public class UserDetailCK implements Serializable {

    private String name;
    private String address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailCK that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
