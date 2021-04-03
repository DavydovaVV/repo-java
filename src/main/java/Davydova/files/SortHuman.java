import java.util.*;

/**
 * This is the class with methods to implement operations on class Human
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class SortHuman {

    /**
     * Получить коллекцию ArrayList класса Human
     * @return возвращает коллекцию ArrayList класса Human
     */
    public ArrayList<Human> getHumanList() {
        SortHuman address = new SortHuman();

        Human human1 = new Human("Иванов И.И.", 55, address.getAddressList().get(0));
        Human human2 = new Human("Петров П.П.", 14, address.getAddressList().get(1));
        Human human3 = new Human("Козлов К.К.", 42, address.getAddressList().get(3));
        Human human4 = new Human("Полевой П.П.", 23, address.getAddressList().get(4));
        Human human5 = new Human("Сидоров С.С.", 20, address.getAddressList().get(2));
        Human human6 = new Human("Пасечный П.П.", 25, address.getAddressList().get(6));
        Human human7 = new Human("Сухой С.С.", 56, address.getAddressList().get(5));
        Human human8 = new Human("Сидоров С.С.", 20, address.getAddressList().get(2));
        Human human9 = new Human("Полевой П.П.", 23, address.getAddressList().get(4));
        Human human10 = new Human("Сухой С.С.", 56, address.getAddressList().get(5));

        ArrayList<Human> people = new ArrayList<>();
        Collections.addAll(people, human1, human2, human3, human4, human5, human6, human7, human8, human9, human10);

        return people;
    }

    /**
     * Получить коллекцию ArrayList класса Address
     * @return возвращает коллекцию ArrayList класса Address
     */
    public ArrayList<Address> getAddressList() {
        ArrayList<Address> addressList = new ArrayList<>();

        addressList.add(new Address("Сочи", "Цветочная", 2, 11));
        addressList.add(new Address("Тюмень", "Театральная", 65, 85));
        addressList.add(new Address("Таганрог", "Ленина", 22, 51));
        addressList.add(new Address("Москва", "Ленина", 56, 72));
        addressList.add(new Address("Омск", "Цветочная", 5, 9));
        addressList.add(new Address("Тольятти", "Портовая", 8, 10));
        addressList.add(new Address("Оренбург", "Северная", 64, 2));

        return addressList;
    }

    /**
     * Найти и вывести в консоль дубликаты элементов из списка
     *
     * @param list лист элементов класса Human
     */
    public static void findDupl(List<Human> list) {
        HashSet<Human> uniques = new HashSet<>();
        HashSet<Human> duplicates = new HashSet<>();
        for (Human element : list) {
            if (!uniques.add(element)) {
                duplicates.add(element);
            }
        }
        System.out.println(duplicates);
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

    private class FioComparator implements Comparator<Human> {

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

    private class AddressComparator implements Comparator<Human> {

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

