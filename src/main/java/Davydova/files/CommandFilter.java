package Davydova.files;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * This is the class that accepts commands and pass them to a specific command handler
 */
@Slf4j
public class CommandFilter {

    /**
     * Handle string received from scanner to pass to a specific command handler
     * @param command string received from scanner
     */
    public void getCommand(String command) {

        try {
            Command.valueOf(Command.class, command).goToCommandHandler();
        }
        catch (IllegalArgumentException e) {
            log.error("There is no such command. Exception is: ", e);
        }
    }
}

@Slf4j
enum Command {

    ADD {
        public void goToCommandHandler () {
            AddCommandHandler adder = new AddCommandHandler();
            try {
                adder.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    },

    DELETE {
        public void goToCommandHandler () {
            DeleteCommandHandler deleter = new DeleteCommandHandler();
            try {
                deleter.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    },
    PRINT {
        public void goToCommandHandler () {
            PrintCommandHandler printer = new PrintCommandHandler();
            try {
                printer.handle();
            } catch (IOException e) {
                log.error("Exception is: ", e);
            }
        }
    };

    public abstract void goToCommandHandler();
}

enum FileToRecord {
    FILE;
    private String value;

    /**
     * Getter for field value of enum File
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Setter for field value of enum File
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}

enum Add {
    NUMBER, LINE;
    private String value;
    private String number;

    /**
     * Getter for field value of enum Add
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     * Getter for field number of enum Add
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter for field value of enum Add
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Setter for field number of enum Add
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}

enum DeleteAndPrint {
    NUMBER;
    private String number;

    /**
     * Getter for field number of enum DeleteAndPrint
     * @return
     */
    public String getNumber() {
        return number;
    }

    /**
     * Setter for field number of enum DeleteAndPrint
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}