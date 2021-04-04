import java.util.*;

/**
 * This is the class for creating Collections
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class Human implements Comparable<Human> {
    private String fio;
    private int age;
    private Address address;
    private ArrayList<Human> people;

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
     * Получить значение поля fio
     * @return возвращает значение fio
     */
    public String getFio() {
        return fio;
    }

    /**
     * Получить значение поля age
     * @return возвращает значение age
     */
    public int getAge() {
        return age;
    }

    /**
     * Получить значение поля address
     * @return возвращает значение address
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
        return age == human.age && Objects.equals(fio, human.fio) && Objects.equals(address.toString(), human.address.toString());
    }

    /**
     * Переопределение метода hashCode()
     * @return возвращает хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(people);
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
