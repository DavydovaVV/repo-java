package java.davydova.files;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * This is a class to operate with instances of Storage and Cache class
 */
@Slf4j
@Data
@SuppressWarnings("unchecked")
public class Storage<T> {

    private Object[] storage;
    private Cache<T> cache;

    /**
     * Instantiates arrays of Object and Cache class
     */
    public Storage() {
        this.storage = new Object[10];
        this.cache = new Cache<>(10);
    }

    /**
     * Get the array of Storage class
     *
     * @return array of instances of Object class
     */
    public Object[] getStorage() {
        return storage;
    }

    /**
     * Get the array of Cache class
     *
     * @return array of instances of Cache class
     */
    public Cache<T> getCache() {
        return cache;
    }

    /**
     * Instantiates and fills up the array of Object class
     *
     * @param array is the array of Object class
     */
    public Storage(T[] array) {
        storage = new Object[10];
        cache = new Cache<>(10);
        if (array.length > storage.length) {
            increaseArray(storage);
        } else {
            System.arraycopy(array, 0, storage, 0, array.length);
        }
    }

    /**
     * Add element T to the array of Object class
     *
     * @param element is the element of the array
     */
    public void add(T element) {
        log.info("Method add(T) of class Storage");
        log.info("Array storage before adding element: {}", Arrays.toString(storage));
        if (storage[storage.length - 1] != null) {
            storage = increaseArray(getStorage());
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                    log.info("Array storage after adding element: {}", Arrays.toString(storage));
                    return;
                }
            }
        } else {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                    log.info("Array storage after adding element: {}", Arrays.toString(storage));
                    return;
                }
            }
        }
    }

    /**
     * Increase the array by 1.5
     *
     * @param storage is the array to be increased
     */
    private Object[] increaseArray(Object[] storage) {
        log.info("Method increaseArray() of class Storage");
        Object[] temp = new Object[(int) (1.5 * this.storage.length)];
        System.arraycopy(storage, 0, temp, 0, this.storage.length);
        return temp;
    }

    /**
     * Delete the last element of the array of class Object
     */
    public void delete() {
        log.info("Method delete() of class Storage");
        log.info("Array storage before deleting element: {}", Arrays.toString(storage));
        T lastElement = getLast();
        if (cache.isPresent(lastElement)) {
            cache.delete(lastElement);
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i - 1] = null;
                    log.info("Array storage after deleting element: {}", Arrays.toString(storage));
                    return;
                }
            }
        }
    }

    /**
     * Clear up the array of class Object and the array of class Cache
     */
    public void clear() {
        log.info("Method clear() of class Storage");
        log.info("Array storage before clearing up: {}", Arrays.toString(storage));
        cache.clear();
        Arrays.fill(storage, null);
        log.info("Array storage after clearing up: {}", Arrays.toString(storage));
        log.info("Storage is cleared up");
    }

    /**
     * Get the last recorded element of the array of class Object
     *
     * @return last element of the array
     */
    public T getLast() {
        log.info("Method getLast() of class Storage");
        for (int i = 0; i < storage.length; i++) {
            if ((storage[i] == null) && (i == 0)) {
                throw new ArrayNullPointerException("Array is empty");
            } else if ((storage[i] == null) && (i != 0)) {
                log.info("Last recorded element of array storage is: {}", (T) storage[i - 1]);
                return (T) storage[i - 1];
            } else if (storage[i] != null) {
                if (i == storage.length - 1) {
                    log.info("Last recorded element of array storage is: {}", (T) storage[storage.length - 1]);
                    return (T) storage[storage.length - 1];
                }
                continue;
            }
        }
        throw new ArrayNullPointerException("Array is empty");
    }


    /**
     * Get the array element of class Cache by index of the element from the array of class Object
     * or record it in the array of class Cache
     *
     * @param index is the index of the element from array of class Object
     * @return the value of the element of class Object array
     */
    public T get(int index) {
        log.info("Method get(int) of class Storage");
        if (!cache.isPresent(index)) {
            cache.add((T) storage[index], index);
            log.info("Element is absent in array cache so it is added to array cache");
        }
        log.info("Element of storage with index {} is: {}", index, (T) storage[index]);
        return (T) storage[index];
    }
}
