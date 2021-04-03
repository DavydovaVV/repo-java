import java.util.*;

/**
 *
 * @author DavydovaVV
 * version 1.0 04/01/2021
 */
public class HashMapSort {

    /**
     * Получить объект Map класса HashMapSort
     * @return возвращает объект Map
     */
    public HashMap<Integer, Human> getMap() {
        HashMap<Integer, Human> map = new HashMap<>();
        Human human = new Human();

        map.put(3, human.getHumanList().get(2));
        map.put(65, human.getHumanList().get(3));
        map.put(1, human.getHumanList().get(4));
        map.put(2, human.getHumanList().get(5));

        return map;
    }

      /**
     * Отсортировать по Кеy
     */
    public void sortKeyMap (Map<Integer, Human> map) {
        List<Map.Entry<Integer, Human>> sorted = new LinkedList<>(map.entrySet());
        sorted.sort(new KeyComparator());
        System.out.println(sorted);
    }

    /**
     * Отсортировать по Value
     */
    public void sortValueMap (Map<Integer, Human> map) {
        List<Map.Entry<Integer, Human>> sorted = new LinkedList<>(map.entrySet());
        sorted.sort(new ValueComparator());
        System.out.println(sorted);
    }
    class ValueComparator implements Comparator<Map.Entry<Integer, Human>> {

        /**
         * Переопределение метода compare()
         * @param o1 экземпляр объекта класса Map.Entry<Integer, Human>
         * @param o2 экземпляр объекта класса Map.Entry<Integer, Human>
         * @return возвращает результат сравнения двух объектов класса Human по значение Value
         */
        @Override
        public int compare(Map.Entry<Integer, Human> o1, Map.Entry<Integer, Human> o2) {
            return o1.getValue().compareTo(o2.getValue());
        }
    }

    class KeyComparator implements Comparator<Map.Entry<Integer, Human>> {

        /**
         * Переопределение метода compare()
         * @param o1 экземпляр объекта класса Map.Entry<Integer, Human>
         * @param o2 экземпляр объекта класса Map.Entry<Integer, Human>
         * @return возвращает результат сравнения двух объектов класса Human по значение Key
         */
        @Override
        public int compare(Map.Entry<Integer, Human> o1, Map.Entry<Integer, Human> o2) {
            return o1.getKey().compareTo(o2.getKey());
        }
    }
}


