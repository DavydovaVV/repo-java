package com.epam.davydova.task2;

import lombok.extern.slf4j.Slf4j;

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
     * @return a number of created objects
     */
    public int generateObject(Map<String, List<String>> mapOfDetails, Class<?> object) {
        int i = 0;
        while (i < mapOfDetails.get("type").size()) {
            String detailType = mapOfDetails.get("type").get(i);
            String type = detailType.substring(detailType.indexOf("=") + 1);

            String detailWeight = mapOfDetails.get("weight").get(i);
            int weight = Integer.parseInt(detailWeight.substring(detailWeight.indexOf("=") + 1));

            String detailCost = mapOfDetails.get("cost").get(i);
            long cost = Integer.parseInt(detailCost.substring(detailCost.indexOf("=") + 1));

            Sausage sausage = new Sausage(type, weight, cost);

            log.info("Object [" + (i + 1) + "] of class [" + sausage.getClass().getSimpleName() + "] " +
                    ", type = " + type +
                    ", weight = " + weight +
                    ", cost = " + cost);
            i++;
        }
        return i;
    }
}
