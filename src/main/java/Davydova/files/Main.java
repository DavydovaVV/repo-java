public class Main {
	
	public static void main (String[] args) {
		Cache<Integer> cache = new Cache<>(3);
		cache.add(6,3);
		cache.add(5,2);
		cache.add(7,6);
		cache.isPresentElement(6);
		cache.delete(6);
		cache.isPresentIndex(3);
		cache.isPresentIndex(2);
		cache.get(7);
		cache.clear();
	}
}
