package Davydova.files;

import lombok.extern.slf4j.Slf4j;

/**
 * This is the class of MyCheckedException
 */
@Slf4j
public class CacheElementException extends ClassCastException {
    public void CacheElementException() {
        log.info("CacheElementException has been thrown");
    }
}