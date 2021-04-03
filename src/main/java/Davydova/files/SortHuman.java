import java.util.*;

/**
 * This is the class with methods to implement operations on class Human
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class SortHuman {

    /**
     * Найти и вывести в консоль дубликаты элементов из списка
     *
     * @param list лист элементов класса Human
     */
    public static void findDupl(List<Human> list) {
        HashSet<Human> uniques = new HashSet<>();
        for (Human element : list) {
            if (Collections.frequency(list, element) > 1) {
                    uniques.add(element);
            }
        }
        System.out.println(uniques);
    }

    /**
     * Удалить дубликаты
     *
     * @param list лист элементов, из которого удаляются элементы
     * @return возвращает лист без дубликатов
     */
    public static List<Human> deleteDupl(List<Human> list) {
        LinkedHashSet<Human> singular = new LinkedHashSet<>(list);
        list.clear();
        list.addAll(singular);
        return list;
    }
    /**
     * Отсортировать по полю ФИО
     * @param list лист элементов, который подлежит сортировке
     */
    public void sortFIO(List<Human> list) {
        list.sort(new FioComparator());
    }

    /**
     * Отсортировать по возрасту
     * @param list лист элементов, который подлежит сортировке
     */
    public void sortAge(List<Human> list) {
        Collections.sort(list);
    }

    /**
     * Отсортировать по адресу
     * @param list лист элементов, который подлежит сортировке
     */
    public void sortAddress(List<Human> list) {
        list.sort(new AddressComparator());
    }

    class FioComparator implements Comparator<Human> {

        /**
         * Переопределение метода compare()
         *
         * @param o1 экземпляр объекта класса Human
         * @param o2 экземпляр объекта класса Human
         * @return возвращает результат сравнения двух объектов класса Human по полю FIO
         */
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getFio().compareTo(o2.getFio());
        }
    }

    class AddressComparator implements Comparator<Human> {

        /**
         * Переопределение метода compare()
         * @param o1 экземпляр объекта класса Human
         * @param o2 экземпляр объекта класса Human
         * @return возвращает результат сравнения двух объектов класса Human по полю Address
         */
        @Override
        public int compare(Human o1, Human o2) {
            return o1.getAddress().toString().compareTo(o2.getAddress().toString());
        }
    }
}

