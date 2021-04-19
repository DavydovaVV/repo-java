package java.davydova.files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main (String[] args) {

        Storage<String> storage1 = new Storage<>(new String[]{"5", "1", "2", "3", "4", "6", "7", "8", "9" });
        storage1.getCache().add("3",2);
        storage1.getCache().add("5", 5);
        storage1.getCache().get(2);
        storage1.getCache().delete("5");
        storage1.getCache().isPresent(5);
        storage1.getCache().isPresent("3");
        storage1.getCache().clear();
        storage1.add("7");
        storage1.add("5");
        storage1.getLast();
        storage1.delete();
        storage1.getLast();
        storage1.get(0);
        storage1.get(1);
        storage1.clear();
    }
}