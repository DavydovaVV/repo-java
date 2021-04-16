package davydova.files;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a class to demonstrate throwing StackOverflowError
 */
@Slf4j
public class StackOverFlowDemo {

    /**
     * Demonstrate StackOverFlowError
     * @param string is a string to be printed in console
     */
    public static void printString(String string) {

        System.out.println("String is: " + string);
        try {
            printString(string);
        } catch (StackOverflowError e) {
            log.error("Stack overflow occurred");
        }
        return;
    }
}
