package Davydova.files;

import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

/**
 *
 * This is a class Storage<T>
 * author DavydovaVV
 * version 1.0 03/28/2021
 */
@Slf4j
@SuppressWarnings("unchecked")
public class Storage <T> {
    Object [] storage;
    Cache<T> cache;

    /**
     * Дефолтный конструктор, в котором создается массив Object и объект типа Cache
     */
    public Storage() {
        this.storage = new Object[10];
        this.cache = new Cache<>(10);
    }

    /**
     * Getter for array of Storage class
     * @return array of Storage class
     */
    public Object [] getStorage() {
        return storage;
    }

    /**
     * Getter for array of Cache class
     * @return array of Cache class
     */
    public Cache<T> getCache() {
        return cache;
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
    public void add(T element) throws ArrayNullPointerException {
        log.debug("Log from add(T) method of class Storage");
        if (storage[storage.length-1] != null){
            storage = increaseArray(getStorage());
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                    return;
                }
            }
        } else {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                    return;
                }
            }
        }
    }

    /**
     * Увеличить размер передаваемого массива
     * @param storage
     */
    public Object[] increaseArray(Object[] storage) {
        log.debug("Log from increaseArray(Object[]) method of class Storage");
        Object [] temp = new Object[(int) (1.5 * this.storage.length)];
        System.arraycopy(storage, 0, temp, 0, this.storage.length);
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
    public void clear() {
        log.debug("Log from clear() method of class Storage");
        cache.clear();
        Arrays.fill(storage, null);
        try {
            getLast();
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error ("Exception is: ", e);
        }
        System.out.println("storage очищен");
    }

    /**
     * Получить последний записанный элемент массива Object
     * @return возвращает последний элемент массива Object
     */
    public T getLast() {
        for (int i = 0; i < storage.length; i++) {
            if ((storage[i] == null) && (i == 0)) {
                return null;
            } else if ((storage[i] == null) && (i != 0)) {
                return (T) storage[i - 1];
            } else if (storage[i] != null) {
                if (i == storage.length - 1) {
                    return (T) storage[storage.length - 1];
                }
                continue;
            }
        }
        return null;
    }


    /**
     * Получить элемент массива CacheElement объекта Cache по индексу или записать в массив CacheElement из массива Object
     * @param index индекс в массиве класса Storage, по которому проверяется наличие элемента массива CacheElement
     * объекта Cache
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