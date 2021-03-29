public class Main {
	
	public static void main (String[] args) {

		Storage<String> storage1 = new Storage<>(new String[]{"3", "5", "6", "9"});
		//storage1.clear();
		storage1.add("7");
		storage1.getLast();
		storage1.delete();
		storage1.getLast();
		storage1.cache.add("3", 0);
		storage1.get(0);
		storage1.get(1);







	}
	
}
