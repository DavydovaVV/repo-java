package davydova.files;

/**
 * This is the class to test CustomClassLoader in action
 */

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class TestLoader {

    private static final String PATH_TO_CLASS_BE_LOADED = "D:\\YandexDisk\\Programming\\myapplication";

    public static void main(String[] args) {

        CustomClassLoader classLoader = new CustomClassLoader();

        Class<?> clazz = classLoader.loadClass(PATH_TO_CLASS_BE_LOADED,"TestClass");

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
