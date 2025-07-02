package playground.entityinheritance.composite;

import java.io.Serializable;
import java.util.Objects;

public class UserDetailCK implements Serializable {

    private String name;
    private String address;

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof UserDetailCK userDetailCK)) return false;

        return this.name.equalsIgnoreCase(userDetailCK.name) &&
                this.address.equalsIgnoreCase(userDetailCK.address);
    }
}
