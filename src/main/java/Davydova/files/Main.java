public class Main {
	
	public static void main (String[] args) {
		Cache<String> cache1 = new Cache<>(10);
		Storage<String> storage1 = new Storage(new String[]{"3", "5", "6"});
		storage1.add("5");
		cache1.add("10", 2);
		cache1.add("5",1);

	}
	
}
