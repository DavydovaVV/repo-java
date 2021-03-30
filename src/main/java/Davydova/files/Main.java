public class Main {
	public static void main (String[] args) {
		Storage<String> storage1 = new Storage<>(new String[]{"5"});
		storage1.cache.add("3",2);
		storage1.cache.add("5", 5);
		storage1.cache.get(2);
		storage1.cache.delete("5");
		storage1.cache.isPresent(5);
		storage1.cache.isPresent("3");
		storage1.cache.clear();
		storage1.add("7");
		storage1.add("5");
		storage1.getLast();
		storage1.delete();
		storage1.getLast();
		storage1.get(0);
		storage1.get(1);
		storage1.clear();
	}
}
