package davydova.files;

/**
 * This is the class to load classes from other directories
 */

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

@Slf4j
public class CustomClassLoader extends ClassLoader {

    /**
     * Load class from another directory
     * @param path is a path of class
     * @param classname is a name of class
     * @return loaded class
     */
    public Class<?> loadClass(String path, String classname) {

        File file = new File(path);
        Class<?> clazz = findLoadedClass(classname);

        if (clazz == null) {
            try {
                URL classUrl = file.toURI().toURL();
                URL[] urls = new URL[]{classUrl};
                URLClassLoader ucl = new URLClassLoader(urls, getClass().getClassLoader());
                return ucl.loadClass(classname);
            } catch (Exception e) {
                log.error("Extension is: ", e);
                return null;
            }
        }
        return clazz;
    }
}
