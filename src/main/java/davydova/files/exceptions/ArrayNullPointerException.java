package davydova.files.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a class of my unchecked exception that wraps NullPointerException
 */

@Slf4j
public class ArrayNullPointerException extends NullPointerException {
    public ArrayNullPointerException(String message) {
        super(message);
    }
}