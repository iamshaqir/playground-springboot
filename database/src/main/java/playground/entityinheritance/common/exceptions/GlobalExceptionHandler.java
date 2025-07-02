package playground.entityinheritance.common.exceptions;

import playground.entityinheritance.common.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiErrorResponse handleNoVehicleFound(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String errors = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(","));
        log.error("[Validation error] Request failed: {}", errors);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, errors);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoVehicleFoundException.class)
    public ApiErrorResponse handleNoVehicleFound(NoVehicleFoundException exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Entity(s) not found: {}", exceptionMessage);
        return new ApiErrorResponse(HttpStatus.NOT_FOUND, exceptionMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiErrorResponse handleNoVehicleFound(Exception exception) {
        String exceptionMessage = exception.getMessage();
        log.error("Failed to respond {}", exceptionMessage);
        return new ApiErrorResponse(HttpStatus.BAD_REQUEST, exceptionMessage);
    }
}
