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

        File file = new File("D:\\YandexDisk\\Programming\\myapplication");

        CustomClassLoader classLoader = new CustomClassLoader();

        Class<?> clazz = classLoader.loadClass(file.getPath(),"HelloWorld");

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
