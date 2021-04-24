package com.epam.davydova.exceptions;

/**
 * This is a class of checked exception. Thrown when an applications attempts to use null when annotation required
 */
public class NoValueAnnotationException extends RuntimeException {

    public NoValueAnnotationException(String message) {
        super(message);
    }
}
