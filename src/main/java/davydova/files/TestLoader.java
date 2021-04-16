package davydova.files;

/**
 * This is the class to test CustomClassLoader in action
 */

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

@Slf4j
public class TestLoader {

    public static void main(String[] args) {

        final String PATH = "D:\\YandexDisk\\Programming\\myapplication";

        File file = new File(PATH);

        CustomClassLoader classLoader = new CustomClassLoader();

        Class<?> clazz = classLoader.loadClass(file.getPath(),"TestClass");

        Object obj = null;

        try {
            obj = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException |
                InvocationTargetException | NoSuchMethodException | NullPointerException e) {
            log.error("Exception is: ", e);
        }

        if (obj == null) {
            log.error("Incorrect Class Name");
        } else {
            System.out.println(obj);
        }
    }
}
