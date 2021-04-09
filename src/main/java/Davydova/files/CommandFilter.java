import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * This is the class that accepts commands and pass them to a specific command handler
 */
@Slf4j
public class CommandFilter {

    /**
     * Handle string received from scanner to get to the respective method
     * @param command string received from scanner
     */
    public void getCommand(String command) {

        LineRecorder lineRecorder = new LineRecorder();
        //LineRemover lineRemover = new LineRemover();

        switch (command) {
            case "add":
                try {
                    lineRecorder.addLine();
                } catch (IOException e) {
                    log.error("Exception is: ", e);
                }
                break;

            case "delete":
                try {
                    lineRecorder.deleteLine();
                } catch (IOException e) {
                    log.error("Exception is: ", e);
                }
                break;

            case "print":
                try {
                    lineRecorder.printLine();
                } catch (IOException e) {
                    log.error("Exception is: ", e);
                }
                break;
            case "exit":


            default:
                System.out.println("Command: " + command + " is not found");
                break;
        }
    }
}


