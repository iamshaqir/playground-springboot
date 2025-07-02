package playground.entityinheritance.common;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiErrorResponse(HttpStatus status, String message, LocalDateTime localDateTime) {
    public ApiErrorResponse(HttpStatus status, String message) {
        this(status, message, LocalDateTime.now());
    }
}
