package davydova.files;

import Davydova.files.Cache;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;

@Slf4j
public class CacheTest<T> {

    private Cache<String> cache = new Cache<>(3);

    @Test
    public void addNewElementToFullCacheElementArray() {
        cache.add("один", 0);
        cache.add("два", 1);
        cache.add("три", 2);
        cache.add("newElement", 3);

        Assert.assertEquals("newElement", cache.getCache()[2].getElement());
    }

    @Test
    public void addNewElementToEmptyCacheElementArray() {
        cache.add("newElement", 0);

        Assert.assertEquals("newElement", cache.getCache()[0].getElement());
    }

    @Test
    public void addNewElementToNotEmptyCacheElementArray() {
        cache.add("один", 0);
        cache.add("newElement", 1);

        Assert.assertEquals("newElement", cache.getCache()[1].getElement());
    }

    @Test
    public void deleteElementFromNotEmptyCacheElementArray() {
        cache.add("один", 0);
        cache.add("два", 1);
        cache.add("три", 2);
        cache.delete("два");

        String expected = "два";
        String actual = cache.getCache()[1].getElement();

        Assert.assertNotEquals(expected, actual);
    }

    @Test
    public void isPresentElementInNotEmptyCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);

        boolean expected = true;
        boolean actual = cache.isPresent("presentElement");

        Assert.assertEquals(expected, actual);
    }

    @Test (expected = NullPointerException.class)
    public void isPresentElementInNullCacheElementArray() {

        Assert.assertTrue(null == cache.getCache()[0].getElement());
    }

    @Test
    public void isPresentIndexInNotEmptyCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);
        cache.isPresent(5);

        boolean expected = true;
        boolean actual = cache.isPresent(5);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getElementAndPlaceAtTheEndOfNotFullCacheElementArray() {
        cache.add("presentElement", 5);
        cache.add("otherElement", 1);

        String expectedElement = "presentElement";
        String actualResultElement = cache.get(5).getElement();

        int expectedIndex = 5;
        int actualIndex = cache.getCache()[1].getIndex();

        Assert.assertEquals(expectedElement, actualResultElement);
        Assert.assertEquals(expectedIndex, actualIndex);
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

        Assert.assertEquals(expectedElement, actualResultElement);
        Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test(expected = NullPointerException.class)
    public void getNotPresentElementAndPlaceAtTheEndOfCacheElementArray() {
        cache.add("oneElement", 5);
        cache.add("otherElement", 1);

        cache.get(6).getElement();
    }

    @Test(expected = NullPointerException.class)
    public void getNotPresentElementAndPlaceAtTheEndOfEmptyCacheElementArray() {
        cache.get(0).getElement();
    }



    @Test
    public void testClear() {
        Cache<String> checkCache = new Cache<>(3);
        cache.clear();

        Assert.assertEquals(Arrays.toString(checkCache.getCache()), Arrays.toString(cache.getCache()));
    }
}