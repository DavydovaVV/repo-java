package com.epam.davydova;

import com.epam.davydova.annotations.Entity;
import com.epam.davydova.annotations.Value;
import com.epam.davydova.exceptions.NoValueAnnotationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a class of methods to operate with POJOs using annotations and Reflection
 */
public class CheckPojos {

    private static final Logger logger = Logger.getLogger(CheckPojos.class.getName());

    /**
     * Check the instance of Class object if it has annotation Entity
     *
     * @param object is an instance of class Class
     */
    public static void checkEntityAnnotation(Class<?> object) {
        if (object.isAnnotationPresent(Entity.class)) {
            logger.log(Level.INFO, object + " has annotation Entity: true.\nProgram continues execution...");
            checkValueHasEntityAnnotation(object);
        } else {
            logger.log(Level.INFO, object + " has annotation Entity: false.\nProgram continues execution...");
            checkValueAnnotation(object);
        }
    }

    /**
     * Check fields and methods of the instance of Class object if they have annotation Value
     *
     * @param object is an instance of Class object
     */
    private static void checkValueHasEntityAnnotation(Class<?> object) {
        Field[] fields = object.getDeclaredFields();
        Method[] methods = object.getMethods();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                if (!field.getAnnotation(Value.class).destination().isEmpty()) {
                    try {
                        getPropertiesFromFile(object.getAnnotation(Value.class).destination(), object);
                    } catch (NoSuchMethodException e) {
                        logger.log(Level.SEVERE, "There is no such method.\nException is: " + e);
                    }
                } else {
                    try {
                        fillUpFields(object.getName(), field);
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                            | IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                        logger.log(Level.SEVERE, "Exception is: " + e);
                    }
                }
            }
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                if (!method.getAnnotation(Value.class).destination().isEmpty()) {
                    try {
                        getPropertiesFromFile(method.getAnnotation(Value.class).destination(), object);
                    } catch (NoSuchMethodException e) {
                        logger.log(Level.SEVERE, "There is no such method.\nException is: " + e);
                    }
                } else {
                    try {
                        fillUpFields(object.getName(), method);
                    } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                            | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                        logger.log(Level.SEVERE, "Exception is: " + e);
                    }
                }
            } else {
                try {
                    throw new NoValueAnnotationException();
                } catch (NoValueAnnotationException e) {
                    logger.log(Level.FINE, object + " has annotation Value: false. Exception is:\n" + e
                            + "Program continues execution...");
                }
            }
        }
    }

    /**
     * Fill up fields of instance of Class object with annotation values
     *
     * @param className is a name of Class
     * @param field     is a field of Class
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    private static void fillUpFields(String className, Field field) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException,
            ClassNotFoundException {
        Object object = Class.forName(className).getConstructor().newInstance();

        field.setAccessible(true);

        if (field.getName().equals("age")) {
            int age = field.getAnnotation(Value.class).age();
            field.set(object, age);
        } else {
            String name = field.getAnnotation(Value.class).name();
            field.set(object, name);
        }
        logger.log(Level.INFO, "field " + field.getName() + " = " + field.get(object));
    }

    /**
     * Fill up fields of instance of Class object with annotation values
     *
     * @param className is a name of Class
     * @param method    is a method of Class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void fillUpFields(String className, Method method) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        Object object = Class.forName(className).getConstructor().newInstance();

        if (method.getName().equals("setAge")) {
            int age = method.getAnnotation(Value.class).age();
            method.invoke(object, age);
            Field field = object.getClass().getDeclaredField("age");
            field.setAccessible(true);
            logger.log(Level.INFO, "Object " + className + " " + "field " + field.getName() + " = " + field.get(object));
        } else {
            String name = method.getAnnotation(Value.class).name();
            method.invoke(object, name);
            Field field = object.getClass().getDeclaredField("name");
            field.setAccessible(true);
            logger.log(Level.INFO, "Object " + className + " " + "field " + field.getName() + " = " + field.get(object));
        }
    }

    /**
     * Check Class object on presence of annotation Value
     *
     * @param object is an instance of Class class
     */
    private static void checkValueAnnotation(Class<?> object) {
        Field[] fields = object.getDeclaredFields();
        Method[] methods = object.getMethods();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                try {
                    throw new IllegalAccessException();
                } catch (IllegalAccessException e) {
                    logger.log(Level.FINE, object + " has annotation Value: true. Exception is:\n" + e
                            + "Program continues execution...");
                }
            }
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                try {
                    throw new IllegalAccessException();
                } catch (IllegalAccessException e) {
                    logger.log(Level.FINE, object + " has annotation Value: true. Exception is:\n" + e
                            + "Program continues execution...");
                }
            }
        }
    }

    /**
     * Get properties from given file
     *
     * @param pathName is an absolute path to file
     * @param object   is an object of Class
     * @throws NoSuchMethodException
     */
    public static void getPropertiesFromFile(String pathName, Class<?> object) throws NoSuchMethodException {
        File file = new File(pathName);

        ArrayList<Integer> age = new ArrayList<>();
        ArrayList<String> name = new ArrayList<>();

        try {
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    if (line.substring(0, line.lastIndexOf('=')).equals("age")) {
                        age.add(Integer.parseInt(line.substring(line.lastIndexOf('=') + 1)));
                    } else if (line.substring(0, line.lastIndexOf('=')).equals("name")) {
                        name.add(line.substring(line.lastIndexOf('=') + 1));
                    } else {
                        logger.log(Level.SEVERE, "There is no such field");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "There is no such file.\nException is: " + e);
        }

        try {
            int i = 0;
            while (i < age.size()) {
                bindToValue(age, name, i, object);
                i++;
            }
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException
                | IllegalAccessException | NoSuchFieldException e) {
            logger.log(Level.SEVERE, "Exception is: " + e);
        }
    }

    /**
     * Bind field of Class to value of annotation
     *
     * @param age    is a field age of Class
     * @param name   is a field name of Class
     * @param i      is a number of field sets in file
     * @param object is an object of Class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    private static void bindToValue(ArrayList<Integer> age, ArrayList<String> name, int i, Class<?> object)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchFieldException {
        Method[] methods = object.getMethods();
        Field[] fields = object.getDeclaredFields();
        Object objectInstance = Class.forName(object.getName()).getConstructor().newInstance();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                field.setAccessible(true);
                if (field.getName().equals("age")) {
                    if (!age.isEmpty()) {
                        field.set(objectInstance, age.get(i));
                        logger.log(Level.INFO, "field " + field.getName() + " = " + field.get(objectInstance));
                    } else {
                        logger.log(Level.SEVERE, "Fields are empty");
                    }
                } else {
                    if (!name.isEmpty()) {
                        field.set(objectInstance, name.get(i));
                        logger.log(Level.INFO, "field " + field.getName() + " = " + field.get(objectInstance));
                    } else {
                        logger.log(Level.SEVERE, "Fields are empty");
                    }
                }
            }
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                if (method.getName().equals("setName")) {
                    method.invoke(objectInstance, name.get(i));
                    Field field = objectInstance.getClass().getDeclaredField("name");
                    field.setAccessible(true);
                    logger.log(Level.INFO, "Object " + object.getName() + " " + "field " + field.getName()
                            + " = " + field.get(objectInstance));
                } else {
                    method.invoke(objectInstance, age.get(i));
                    Field field = objectInstance.getClass().getDeclaredField("age");
                    field.setAccessible(true);
                    logger.log(Level.INFO, "Object " + object.getName() + " " + "field " + field.getName()
                            + " = " + field.get(objectInstance));
                }
            }
        }
    }
}
