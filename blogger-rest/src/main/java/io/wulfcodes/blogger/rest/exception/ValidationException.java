package io.wulfcodes.blogger.rest.exception;

public class ValidationException extends RuntimeException {

    public ValidationException() {
        super("Token validation failed!");
    }

    public ValidationException(String message) {
        super(message);
    }

}
