import lombok.extern.slf4j.Slf4j;

/**
 * This is the class of MyCheckedException
 */
@Slf4j
public class MyCheckedException extends ClassCastException {
    public void MyCheckedException() {
        log.info("MyCheckedException has been thrown");
    }
}