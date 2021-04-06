import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class Main {

	private static final Logger loggerCache  = LoggerFactory.getLogger(Cache.class);
	private static final Logger loggerStorage  = LoggerFactory.getLogger(Storage.class);

	public static void main (String[] args) {
		loggerCache.info("Entering application.");
		log.info("test main");
		loggerCache.info("Example log from {}", Cache.class.getSimpleName());
		loggerStorage.info("Example log from {}", Storage.class.getSimpleName());

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
