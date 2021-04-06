import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 *
 * This is a class Storage<T>
 * author DavydovaVV
 * version 1.0 03/28/2021
 */

@SuppressWarnings("unchecked")
public class Storage <T> {
    Object [] storage;
    Cache<T> cache;
    private static final Logger loggerStorage  = LoggerFactory.getLogger(Storage.class);

    /**
     * Дефолтный конструктор, в котором создается массив Object и объект типа Cache
     */
    public Storage() {
        this.storage = new Object[10];
        this.cache = new Cache<>(10);
    }

    /**
     * Конструктор, который заполняет массив Object
     * @param array массив, который заполняет массив Object
     */
    public Storage(T[] array) {
        storage = new Object[10];
        cache = new Cache<>(10);
        if (array.length > storage.length) {
            increaseArray(storage);
        }else{
            System.arraycopy(array, 0, storage, 0, array.length);
        }
    }

    /**
     * Добавить элемент Т в массив Object
     * @param element элемент, добавляемый в массив Object
     */
    public void add(T element) throws MyUnCheckedException {
        loggerStorage.debug("Entering method add(T) in class Storage");
        if (storage[storage.length-1] != null){
            loggerStorage.debug("Element is not equal to null: {}", storage[storage.length-1] != null);
            increaseArray(storage);
            loggerStorage.debug("Storage capacity now is: {}", storage.length);
            for (int i = 0; i < storage.length; i++) {
                loggerStorage.debug("Checking storage in for loop");
                if (storage[i] == null) {
                    loggerStorage.debug("Element equals to null: {}", storage[i] == null);
                    storage[i] = element;
                    loggerStorage.debug("Element is assigned: {}", element);
                    return;
                }
            }
        } else {
            loggerStorage.debug("Element is equal to null: {}", storage[storage.length-1] != null);
            for (int i = 0; i < storage.length; i++) {
                loggerStorage.debug("Checking storage in for loop");
                if (storage[i] == null) {
                    loggerStorage.debug("Element equals to null: {}", storage[i] == null);
                    storage[i] = element;
                    loggerStorage.debug("Element is assigned: {}", element);
                    return;
                }
            }
        }
    }

    /**
     * Увеличить размер передаваемого массива
     */
    public Object[] increaseArray (Object[] array) {
        Object [] temp = new Object[(int) (1.5 * array.length)];
        System.arraycopy(array, 0, temp, 0, array.length);
        try {
            get((int) (1.5 * array.length));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Удалить последний элемент массива Object
     */
    public void delete() {
        T lastElement = getLast();
        if (cache.isPresent(lastElement)) {
            cache.delete(lastElement);
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i-1] = null;
                    return;
                }
            }
        }
    }

    /**
     * Очистить массив Object и массив CacheElement объекта Cache
     */
    public void clear() throws MyUnCheckedException {
        cache.clear();
        Arrays.fill(storage, null);
        System.out.println("storage очищен");
    }

    /**
     * Получить последний записанный элемент массива Object
     * @return возвращает последний элемент массива Object
     */
    public T getLast() {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return (T)storage[i-1];
            }
        }
        return null;
    }

    /**
     * Получить элемент массива CacheElement объекта Cache по индексу или записать в массив CacheElement из массива Object
     * @param index индекс по которому проверяется наличие элемента массива CacheElement объекта Cache
     * @return возвращает элемент массива Object
     */
    public T get(int index) {
        if (!cache.isPresent(index)) {
            cache.add((T) storage[index], index);
            String o = (String)storage[index];
        }
        return (T) storage[index];
    }
}