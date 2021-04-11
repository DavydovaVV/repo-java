package Davydova.files;

import java.io.IOException;
import java.util.TreeMap;

/**
 * This is the abstract class for command handlers
 */
public abstract class CommandHandler {
    private String fileName;
    private String lineNo;

    /**
     * Get object map
     * @return map
     */
    public TreeMap<Integer, String> getMap() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(0, fileName);
        map.put(1, lineNo);
        return map;
    }

    /**
     * Handle filled map
     * @param map consist of fileName, line number fields
     * @throws IOException if there is no such file
     */
    public void handle(TreeMap<Integer, String> map) throws IOException {

    }
}
