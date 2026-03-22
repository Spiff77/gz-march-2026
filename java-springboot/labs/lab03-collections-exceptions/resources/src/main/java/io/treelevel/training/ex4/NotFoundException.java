package io.treelevel.training.ex4;

/**
 * Thrown when a requested resource cannot be found.
 * Extends RuntimeException so callers are not forced to catch it.
 * Spring Boot maps this to HTTP 404 when paired with @ControllerAdvice.
 */
public class NotFoundException extends RuntimeException {

    // TODO: Add a constructor that takes a String message and passes it to super

    // TODO: Add a constructor that takes a String message and a Throwable cause and passes both to super
}
