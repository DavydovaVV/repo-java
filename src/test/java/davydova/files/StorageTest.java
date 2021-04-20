package davydova.files;

import davydova.files.exceptions.ArrayNullPointerException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class StorageTest {

    private String[] smallArray = {"1", "2", "3"};
    private String[] defaultSizeArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private Storage<String> smallStorage = new Storage<>(smallArray);
    private Storage<String> defaultSizeStorage = new Storage<>(defaultSizeArray);

    @Test
    public void testGetStorageArray() {
        smallStorage.getStorage();

        assertArrayEquals(smallArray,
                Arrays.copyOfRange(smallStorage.getStorage(), 0, 3));
    }


    @Test
    public void addElementToNotFullStorageArray() {
        smallStorage.add("4");

        String expected = "4";
        String actual = smallStorage.getStorage()[3].toString();

        assertEquals(expected, actual);
    }

    @Test
    public void addElementToFullStorageArray() {
        defaultSizeStorage.add("10");

        String expected = "10";
        String actual = defaultSizeStorage.getStorage()[10].toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testDeleteLastElementFromStorageArray() {
        smallStorage.getCache().add("3", 11);
        smallStorage.delete();

        assertNull(smallStorage.getStorage()[2]);
    }

    @Test
    public void checkAllElementsOfCacheAndStorageElementArraysEqualNull() {
        Storage<String> checkStorage = new Storage<>();
        Cache<String> checkCache = new Cache<>(10);

        defaultSizeStorage.clear();

        assertArrayEquals(checkStorage.getStorage(),
                defaultSizeStorage.getStorage());

        assertArrayEquals(checkCache.getCache(),
                defaultSizeStorage.getCache().getCache());
    }

    @Test
    public void getLastNullElementFromEmptyStorageArray() {
        Storage<String> emptyStorage = new Storage<>();

        assertThrows(ArrayNullPointerException.class,
                emptyStorage::getLast, "Array is empty");
    }

    @Test
    public void getLastNotNullElementFromStorageArray() {
        String expected = "9";
        String actual = defaultSizeStorage.getLast();

        assertEquals(expected, actual);
    }

    @Test
    public void getPresentElementFromStorageArray() {
        smallStorage.getCache().add("3", 11);

        String expected = "3";
        String actual = smallStorage.get(2);

        assertEquals(expected, actual);
    }

    @Test
    public void getNotPresentElementInCacheFromStorageArray() {
        String expected = "2";
        String actualElementInStorage = smallStorage.get(1);
        String actualElementAddedToCache = smallStorage.getCache().getCache()[0].getElement();

        assertEquals(expected, actualElementInStorage);
        assertEquals(expected, actualElementAddedToCache);
    }
}