import java.util.*;

/**
 * This is the class with methods to implement operations on Collestions
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class Human implements Comparable<Human> {
    String fio;
    int age;
    Address address;

    /**
     * Конструктор класса Human, в котором производится инициализация полей
     * @param fio переменная ФИО
     * @param age переменная возраст
     * @param address переменная адрес
     */
    public Human(String fio, int age, Address address) {
        this.fio = fio;
        this.age = age;
        this.address = address;
    }

    /**
     * Найти и вывести в консаоль дубликаты элементов из списка
     * @param list лист элементов класса Human
     */
    public static void findDupl (List<Human> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    System.out.println(list.get(j));
                }
            }
        }
    }

    /**
     * Удалить дубликаты
     * @param list лист элементов, из которого удаляются элементы
     * @return возвращает лист без дубликатов
     */
   public static List<Human> deleteDupl (List<Human> list) {
       LinkedHashSet<Human> singular = new LinkedHashSet<>(list);
       list.clear();
       list.addAll(singular);
       return list;
   }

    /**
     * Отсортировать по полю ФИО
     * @param list лист элементов, который подлежит сортировке
     */
   public static void sortFIO(List<Human> list) {
        list.sort(new FioComparator());
   }

    /**
     * Отсортировать по возрасту
     * @param list лист элементов, который подлежит сортировке
     */
   public static void sortAge(List<Human> list) {
        Collections.sort(list);
   }

    /**
     * Отсортировать по адресу
     * @param list лист элементов, который подлежит сортировке
     */
   public static void sortAddress(List<Human> list) {
        list.sort(new AddressComparator());
   }

    /**
     * Получить значение поля ФИО
     * @return возвращает значение ФИО
     */
    public String getFio() {
        return fio;
    }

    /**
     * Получить значение поля Адрес
     * @return возвращает значение Адрес
     */
    public Address getAddress() {
        return address;
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
        Human human = (Human) o;
        return age == human.age && Objects.equals(fio, human.fio) && Objects.equals(address, human.address);
    }

    /**
     * Переопределение метода hashCode()
     * @return возвращает хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(fio, age, address);
    }

    /**
     * Переопределение метода toString()
     * @return возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "\nФИО {" + fio +
                "}\nВозраст {" + age +
                "}" + address;
    }

    /**
     * Переопределение метода compareTo()
     * @param o экземпляр объекта Human
     * @return возвращает резальтат сравнения объектов по полю age
     */
    @Override
    public int compareTo(Human o) {
        return Integer.compare(this.age, o.age);
    }
}

class Address {
    private String city;
    private String street;
    private int building;
    private int apartment;

    /**
     * Конструктор класса Address в котором инициализируются поля city, street, building, apartment
     * @param city
     * @param street
     * @param building
     * @param apartment
     */
    public Address(String city, String street, int building, int apartment) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    /**
     * Переопределение метода toString()
     * @return возвращает объект в виде строки
     */
    @Override
    public String toString() {
        return "\nАдрес {" +
                city + " , " +
                street + " , " +
                building + " , " +
                apartment + "}\n";
    }
}

class FioComparator implements Comparator<Human> {

    /**
     * Переопределение метода compare()
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
