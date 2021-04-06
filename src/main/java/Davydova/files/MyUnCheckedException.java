import lombok.extern.slf4j.Slf4j;

/**
 * This is the class of MyUncheckedException
 */

@Slf4j
public class MyUnCheckedException extends NullPointerException {
    public MyUnCheckedException(String message) {
        super(message);
        log.info("an exception has been thrown");
    }
}
