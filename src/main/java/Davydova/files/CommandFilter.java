package Davydova.files;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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

        Scanner scanner = new Scanner(System.in);
        Map<String, CommandHandler> map = new HashMap<>();
        map.put("add", new AddCommandHandler());
        map.put("delete", new DeleteCommandHandler());
        map.put("print", new PrintCommandHandler());

        try {
            CommandHandler handler = map.get(command);
            TreeMap<Integer, String> mapOfCommandHandler = handler.getMap();
            for (int i = 0; i < mapOfCommandHandler.size(); i++) {
                if (i == 0) {
                    System.out.print("Type file name: ");
                } else if (i == 1) {
                    System.out.print("Type line number: ");
                } else {
                    System.out.print("Type line: ");
                }
                mapOfCommandHandler.put(i, scanner.nextLine());
            }
            handler.handle(mapOfCommandHandler);
        }
        catch (IOException e) {
            log.error("There is no such command. Exception is: ", e);
        }
    }
}