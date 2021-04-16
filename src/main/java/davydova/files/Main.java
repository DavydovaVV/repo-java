package davydova.files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main (String[] args) {
        OutOfMemoryDemo.fillUpArrayOfLong();
        StackOverFlowDemo.printString("StackOverflow");
    }
}
