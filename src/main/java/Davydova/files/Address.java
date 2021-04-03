import java.util.ArrayList;
import java.util.List;

/**
 * This is the class for creating Collections
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class Address {

    private String city;
    private String street;
    private int building;
    private int apartment;
    private ArrayList<Address> addressList;

    /**
     * Дефолтный конструктор класса Address
     */
    public Address() {
    }

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
