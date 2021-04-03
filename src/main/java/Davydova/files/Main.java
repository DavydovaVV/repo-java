import java.util.ArrayList;
import java.util.List;

/**
 * This is the class for testing Collections
 * @author DavydovaVV
 * version 1.0 04/02/2021
 */
public class Main {

	public static void main (String[] args) {

		Human human = new Human();
		HashMapSort hashMapSort = new HashMapSort();

		SortHuman sortHuman = new SortHuman();
		SortHuman.findDupl(human.getHumanList());
		SortHuman.deleteDupl(human.getHumanList());
		sortHuman.sortFIO(human.getHumanList());
		sortHuman.sortAge(human.getHumanList());
		sortHuman.sortAddress(human.getHumanList());

		User user = new User("Тульский Т.Т.", Role.ADMIN);
		user.greetUser(user);

		hashMapSort.sortKeyMap(hashMapSort.getMap());
		hashMapSort.sortValueMap(hashMapSort.getMap());
	}
}
