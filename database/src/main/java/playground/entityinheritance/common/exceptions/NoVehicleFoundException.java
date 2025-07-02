package playground.entityinheritance.common.exceptions;

public class NoVehicleFoundException extends RuntimeException {
    public NoVehicleFoundException(String message) {
        super(message);
    }
}
