import java.util.*;

public class Main {

	public static void main (String[] args) {

		List<Address> addressList = new ArrayList<>();
		addressList.add(new Address("Сочи", "Цветочная", 2, 11));
		addressList.add(new Address("Тюмень", "Театральная", 65, 85));
		addressList.add(new Address("Таганрог", "Ленина", 22, 51));
		addressList.add(new Address("Москва", "Ленина", 56, 72));
		addressList.add(new Address("Омск", "Цветочная", 5, 9));
		addressList.add(new Address("Тольятти", "Портовая", 8, 10));
		addressList.add(new Address("Оренбург", "Северная", 64, 2));

		Human human1 = new Human("Иванов И.И.", 55, addressList.get(0));
		Human human2 = new Human("Петров П.П.", 14, addressList.get(1));
		Human human3 = new Human("Козлов К.К.", 42, addressList.get(3));
		Human human4 = new Human("Полевой П.П.", 23, addressList.get(4));
		Human human5 = new Human("Сидоров С.С.", 20, addressList.get(2));
		Human human6 = new Human("Пасечный П.П.", 25, addressList.get(6));
		Human human7 = new Human("Сухой С.С.", 56, addressList.get(5));
		Human human8 = new Human("Сидоров С.С.", 20, addressList.get(2));
		Human human9 = new Human("Полевой П.П.", 23, addressList.get(4));
		Human human10 = new Human("Сухой С.С.", 56, addressList.get(5));

		List<Human> people = new ArrayList<>();
		Collections.addAll(people, human1, human2, human3, human4, human5, human6, human7, human8, human9, human10);
		people.remove(1);
		Human.findDupl(people);
		Human.deleteDupl(people);
		people.remove(0);
		Human.sortFIO(people);
		Human.sortAge(people);
		Human.sortAddress(people);

		User user = new User("Тульский Т.Т.", Role.ADMIN);
		user.greetUser(user);

		Map<Integer, Human> map = new HashMap<>();
		map.put(3, human2);
		map.put(65, human3);
		map.put(1, human4);
		map.put(2, human5);

		HashMapSort newMap = new HashMapSort(map);
		newMap.sortValueMap();
		newMap.sortKeyMap();
	}
}
