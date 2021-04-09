import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * This is the class that reads commands from CONSOLE and pass them to class CommandFilter
 */
@Slf4j
public class Main {

    public static void main(String[] args) {

        CommandFilter filter = new CommandFilter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type the command name and press Enter:\n" +
                "add\n" + "delete\n" + "print");

        try {

            while (true) {
                filter.getCommand(scanner.nextLine());
                System.out.println("If you ready to exit, type \"exit\" or press Enter to continue");
                if (scanner.nextLine().equals("exit")) {
                    scanner.close();
                    return;
                }
                System.out.println("Type the command name and press Enter:\n" +
                        "add\n" + "delete\n" + "print");
            }
        } finally {
            scanner.close();
        }
    }
}
