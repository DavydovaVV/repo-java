package com.epam.davydova.task2;

import com.epam.davydova.task2.annotations.Value;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * This is a class to instantiate Sausage class with given values of fields
 */
@Slf4j
public class ObjectGenerator {

    /**
     * Generate instance of class with the details from file
     *
     * @param mapOfDetails is a map of the details from file
     * @param object       is an object of Class
     */
    public void generateObject(Map<String, List<String>> mapOfDetails, Class<?> object) {
        try {
            Field[] fields = object.getDeclaredFields();
            int i = 0;

            while (i < mapOfDetails.get("type").size()) {
                Object objectInstance = object.getConstructor().newInstance();
                for (Field field : fields) {
                    field.setAccessible(true);
                    String detail = mapOfDetails.get(field.getAnnotation(Value.class).path()).get(i);
                    String detailValue = detail.substring(detail.indexOf("=") + 1);

                    if (field.getType().equals(String.class)) {
                        field.set(objectInstance, detailValue);
                    } else {
                        try {
                            field.set(objectInstance, Integer.parseInt(detailValue));
                        } catch (NumberFormatException e) {
                            log.error("Value is not parsable! Default value is assigned");
                            field.set(objectInstance, Integer.parseInt(
                                    (String) Value.class.getMethod("value").getDefaultValue()));
                        }
                    }
                    log.info("Object [" + i + "] of class [" + object.getSimpleName() + "] " +
                            field.getName() + " = " + field.get(objectInstance));
                }
                System.out.println();
                i++;
            }
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            log.error("Exception is: ", e);
        }
    }
}
