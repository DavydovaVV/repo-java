import java.util.*;

/**
 *
 * @author DavydovaVV
 * version 1.0 04/01/2021
 */

public class HashMapSort {
    private Human human;
    private Integer key;
    private Map<Integer, Human> map;

    /**
     * Конструктор класса HashMapSort
     * @param map объект класса Map
     */
    public HashMapSort(Map<Integer, Human> map) {
        this.map = map;
    }

    /**
     * Отсортировать по Кеy
     */
    public void sortKeyMap () {
        List<Map.Entry<Integer, Human>> unsorted = new LinkedList<>(map.entrySet());
        unsorted.sort(new KeyComparator());
    }

    /**
     * Отсортировать по Value
     */
    public void sortValueMap () {
        List<Map.Entry<Integer, Human>> unsorted = new LinkedList<>(map.entrySet());
        unsorted.sort(new ValueComparator());
    }

    /**
     * Переопределение метода toString()
     * @return возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "HashMapSort = " + map;
    }

    /**
     * Сравнить равны ли два объекта
     * @param o экземпляр объекта
     * @return возвращает true, если объекты равны, false, если объекты не равны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashMapSort that = (HashMapSort) o;
        return human.equals(that.human) && key.equals(that.key);
    }

    /**
     * Переопределение метода hashCode()
     * @return возвращает хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(human, key);
    }
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
        return (o1.getValue().compareTo(o2.getValue()));
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
        return (o1.getKey().compareTo(o2.getKey()));
    }
}
