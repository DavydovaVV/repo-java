import java.util.ArrayList;

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
