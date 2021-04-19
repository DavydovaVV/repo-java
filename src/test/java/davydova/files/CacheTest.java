package davydova.files;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
public class CacheTest<T> {

    private Cache<String> cache = new Cache<>(3);

    @Test
    public void testGetCache() {
        String[] checkArray = new String[3];

        assertArrayEquals(checkArray, cache.getCache());
    }

    @Test
    public void addNewElementToFullCacheElementArray() {
        cache.add("один", 0);
        cache.add("два", 1);
        cache.add("три", 2);
        cache.add("newElement", 3);

        assertEquals("newElement", cache.getCache()[2].getElement());
    }

    @Test
    public void addNewElementToEmptyCacheElementArray() {
        cache.add("newElement", 0);

        assertEquals("newElement", cache.getCache()[0].getElement());
    }

    @Test
    public void addNewElementToNotEmptyCacheElementArray() {
        cache.add("один", 0);
        cache.add("newElement", 1);

        assertEquals("newElement", cache.getCache()[1].getElement());
    }

    @Test
    public void deleteElementFromNotEmptyCacheElementArray() {
        cache.add("один", 0);
        cache.add("два", 1);
        cache.add("три", 2);
        cache.delete("два");

        String expected = "два";
        String actual = cache.getCache()[1].getElement();

        assertNotEquals(expected, actual);
    }

    @Test
    public void isPresentElementInNotEmptyCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);

        boolean actual = cache.isPresent("presentElement");

        assertTrue(actual);
    }

    @Test
    public void isPresentElementInNullCacheElementArray() {
        assertThrows(NullPointerException.class, () ->
                assertNull(cache.getCache()[0].getElement()));
    }

    @Test
    public void isPresentIndexInNotEmptyCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);
        cache.isPresent(5);

        assertTrue(cache.isPresent(5));
    }

    @Test
    public void getElementAndPlaceAtTheEndOfNotFullCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);

        String expectedElement = "presentElement";
        String actualResultElement = cache.get(5).getElement();

        int expectedIndex = 5;
        int actualIndex = cache.getCache()[1].getIndex();

        assertEquals(expectedElement, actualResultElement);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void getElementAndPlaceAtTheEndOfFullCacheElementArray() {
        cache.add("oneElement", 5);
        cache.add("otherElement", 1);
        cache.add("presentElement", 10);

        String expectedElement = "presentElement";
        String actualResultElement = cache.get(10).getElement();

        int expectedIndex = 10;
        int actualIndex = cache.getCache()[2].getIndex();

        assertEquals(expectedElement, actualResultElement);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void getNotPresentElementAndPlaceAtTheEndOfCacheElementArray() {
        cache.add("oneElement", 5);
        cache.add("otherElement", 1);

        assertThrows(NullPointerException.class, () ->
                assertNull(cache.get(6).getElement()));

    }

    @Test
    public void getNotPresentElementAndPlaceAtTheEndOfEmptyCacheElementArray() {
        assertThrows(NullPointerException.class, () ->
                assertNull(cache.get(0).getElement()));
    }

    @Test
    public void checkAllElementsOfCacheElementArrayEqualsNull() {
        Cache<String> checkCache = new Cache<>(3);
        cache.clear();

        assertArrayEquals(checkCache.getCache(), cache.getCache());
    }
}