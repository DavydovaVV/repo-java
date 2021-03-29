public class Main {
	
	public static void main (String[] args) {

		Storage<String> storage1 = new Storage<>(new String[]{"5"});
		storage1.clear();
		storage1.cache.add("3",2);
		storage1.cache.add("5", 5);
		storage1.cache.get(1);
		storage1.add("7");
		storage1.add("5");
		storage1.getLast();
		storage1.delete();
		storage1.getLast();
		storage1.get(0);
		storage1.get(1);







	}
	
}
