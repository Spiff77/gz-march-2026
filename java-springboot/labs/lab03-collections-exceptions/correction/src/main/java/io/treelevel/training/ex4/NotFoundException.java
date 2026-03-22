package io.treelevel.training.ex4;

/**
 * Thrown when a requested resource cannot be found.
 * Extends RuntimeException so callers are not forced to catch it.
 * Spring Boot maps this to HTTP 404 when paired with @ControllerAdvice.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
