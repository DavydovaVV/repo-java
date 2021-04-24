package com.epam.davydova;

import com.epam.davydova.annotations.Entity;
import com.epam.davydova.annotations.Value;
import com.epam.davydova.exceptions.NoValueAnnotationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This is a class of methods to operate with POJOs using annotations and Reflection
 */
public class CheckPojos {

    private static final Logger logger = LoggerFactory.getLogger(PropertyGetter.class);

    /**
     * Check the instance of Class object if it has annotation Entity
     *
     * @param object is an instance of class Class
     */
    public boolean checkEntityAnnotation(Class<?> object) {
        if (object.isAnnotationPresent(Entity.class)) {
            logger.info("Entity [" + object.getSimpleName() + "] has annotation Entity: true");
            checkValueHasEntityAnnotation(object);
            return true;
        } else {
            logger.info("Entity [" + object.getSimpleName() + "] has annotation Entity: false");
            checkValueAnnotation(object);
        }
        return false;
    }

    /**
     * Check fields and methods of the instance of Class object if they have annotation Value
     *
     * @param object is an instance of Class object
     */
    public boolean checkValueHasEntityAnnotation(Class<?> object) {
        Field[] fields = object.getDeclaredFields();
        Method[] methods = object.getMethods();
        PropertyGetter propertyGetter = new PropertyGetter();
        HashMap<String, ArrayList<String>> properties = propertyGetter.getPropertiesFromFile();
        int numberOfFieldSets = Integer.parseInt(properties.get("numberOfFieldSets").get(0));
        boolean foundAnnotation = false;

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                foundAnnotation = true;
                String fieldPath = field.getAnnotation(Value.class).path();
                if ((!fieldPath.equals(""))
                        && (numberOfFieldSets != 0)) {
                    try {
                        bindToValue(properties, fields, object);
                        break;
                    } catch (NoSuchFieldException | ClassNotFoundException | InvocationTargetException
                            | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                        logger.error("Exception is: ", e);
                    }
                } else {
                    try {
                        fillUpFields(object, fields);
                        break;
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                            | IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                        logger.error("Exception is: ", e);
                    }
                }
            }
        }

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                foundAnnotation = true;
                String methodPath = method.getAnnotation(Value.class).path();
                if ((!methodPath.equals(""))
                        && (numberOfFieldSets != 0)) {
                    try {
                        bindToValue(properties, methods, object);
                        break;
                    } catch (NoSuchFieldException | ClassNotFoundException | InvocationTargetException
                            | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                        logger.error("Exception is: ", e);
                    }
                } else {
                    try {
                        fillUpFields(object, methods);
                        break;
                    } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                            | IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
                        logger.error("Exception is: ", e);
                    }
                }
            }
        }

        if (!foundAnnotation) {
            try {
                throw new NoValueAnnotationException();
            } catch (NoValueAnnotationException e) {
                logger.error("Entity [" + object.getSimpleName() + "] has annotation Value: false. " +
                        "Class does not contain annotation Value");
                return false;
            }
        }
        return true;
    }

    /**
     * Fill up fields of instance of Class object with annotation values
     *
     * @param object is an object of Class
     * @param fields is an array of fields of Class
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws ClassNotFoundException
     */
    public void fillUpFields(Class<?> object, Field[] fields) throws NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException,
            ClassNotFoundException {
        Object objectInstance = Class.forName(object.getName()).getConstructor().newInstance();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                field.setAccessible(true);

                if (field.getType().equals(int.class)) {
                    try {
                        field.set(objectInstance, Integer.parseInt(field.getAnnotation(Value.class).value()));
                    } catch (NumberFormatException e) {
                        logger.error("Value is not parsable to int! Default value is assigned");
                        field.set(objectInstance, Integer.parseInt((String) Value.class.getMethod("value").getDefaultValue()));
                    }
                } else {
                    field.set(objectInstance, field.getAnnotation(Value.class).value());
                }
                logger.info("Entity [" + objectInstance.getClass().getSimpleName()
                        + "] field " + field.getName() + " = " + field.get(objectInstance));
            }
        }
    }

    /**
     * Fill up fields of instance of Class object with annotation values
     *
     * @param object  is an object of Class
     * @param methods is an array of methods of Class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public void fillUpFields(Class<?> object, Method[] methods) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        Object objectInstance = Class.forName(object.getName()).getConstructor().newInstance();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                String value = method.getAnnotation(Value.class).value();

                for (Class<?> parameter : method.getParameterTypes()) {
                    if (parameter.equals(int.class)) {
                        try {
                            method.invoke(objectInstance, Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            logger.error("Value is not parsable to int! Default value is assigned");
                            method.invoke(objectInstance, Integer.parseInt((String) Value.class.getMethod("value").getDefaultValue()));
                        }
                    } else {
                        method.invoke(objectInstance, value);
                    }
                }
            }
        }

        Field[] fields = objectInstance.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            logger.info("Entity [" + objectInstance.getClass().getSimpleName()
                    + "] field " + field.getName() + " = " + field.get(objectInstance));
        }
    }

    /**
     * Check Class object on presence of annotation Value
     *
     * @param object is an instance of Class class
     */
    public boolean checkValueAnnotation(Class<?> object) {
        Field[] fields = object.getDeclaredFields();
        Method[] methods = object.getMethods();

        for (Field field : fields) {
            for (Method method : methods) {
                if ((field.isAnnotationPresent(Value.class))
                        || (method.isAnnotationPresent(Value.class))) {
                    try {
                        throw new IllegalAccessException();
                    } catch (IllegalAccessException e) {
                        logger.error("Entity [" + object.getSimpleName() + "] has annotation Value: true, " +
                                "while annotation Entity is absent");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Bind field of Class to value of annotation
     *
     * @param properties is a HashMap of field binders and values from file
     * @param object     is an object of Class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public String bindToValue(HashMap<String, ArrayList<String>> properties, Field[] fields, Class<?> object)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            NoSuchFieldException, IllegalAccessException {
        Object objectInstance = Class.forName(object.getName()).getConstructor().newInstance();

        int numberOfFieldSets = Integer.parseInt(properties.get("numberOfFieldSets").get(0));

        for (Field field : fields) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(Value.class)) {
                String key = field.getAnnotation(Value.class).path();

                for (int i = 0; i <= numberOfFieldSets; i++) {
                    if (field.getType().equals(int.class)) {
                        try {
                            field.set(objectInstance, Integer.parseInt(properties.get(key).get(i)));
                        } catch (NumberFormatException e) {
                            logger.error("Value is not parsable to int! Default value is assigned");
                            field.set(objectInstance, Integer.parseInt((String) Value.class.getMethod("value")
                                    .getDefaultValue()));
                        }
                    } else {
                        field.set(objectInstance, properties.get(key).get(i));
                    }

                    logger.info("Entity [" + object.getSimpleName() + "] No [" + (i + 1) + "] field " + field.getName()
                            + " = " + field.get(objectInstance));
                }
            }
        }
        return object.getSimpleName();
    }

    /**
     * Bind field of Class to value of annotation
     *
     * @param properties is a HashMap of field binders and values from file
     * @param object     is an object of Class
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    public String bindToValue(HashMap<String, ArrayList<String>> properties, Method[] methods, Class<?> object)
            throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException,
            IllegalAccessException, NoSuchFieldException {
        Object objectInstance = Class.forName(object.getName()).getConstructor().newInstance();

        int numberOfFieldSets = Integer.parseInt(properties.get("numberOfFieldSets").get(0));

        for (Method method : methods) {
            if (method.isAnnotationPresent(Value.class)) {
                for (int i = 0; i <= numberOfFieldSets; i++) {
                    if (method.isAnnotationPresent(Value.class)) {
                        String key = method.getAnnotation(Value.class).path();

                        for (Class<?> parameter : method.getParameterTypes()) {
                            if (parameter.equals(int.class)) {
                                try {
                                    method.invoke(objectInstance, Integer.parseInt(properties.get(key).get(i)));
                                } catch (NumberFormatException e) {
                                    logger.error("Value is not parsable to int! Default value is assigned");
                                    method.invoke(objectInstance, Integer.parseInt((String) Value.class.getMethod("value")
                                            .getDefaultValue()));
                                }
                            } else {
                                method.invoke(objectInstance, properties.get(key).get(i));
                            }
                        }
                        logger.info("Entity [" + object.getSimpleName() + "] No [" + (i + 1) + "] field [" + key
                                + "] was set with value [" + properties.get(key).get(i) + "] by method " + method.getName());
                    }
                }
            }
        }
        return object.getSimpleName();
    }
}

