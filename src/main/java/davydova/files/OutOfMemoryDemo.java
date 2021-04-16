package davydova.files;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a class to demonstrate throwing StackOverflowError
 */
@Slf4j
public class OutOfMemoryDemo {

    /**
     * Fill up array of long
     */
    public static void fillUpArrayOfLong() {

        int max = 5;
        Object[] array = new Object[max];

        try {
            for (int i = 0; i < max; i++) {
                array[i] = new long[1000*1000*100];
            }
        } catch (OutOfMemoryError e) {
            log.error("Size of heap is exceeded");
        }
    }
}