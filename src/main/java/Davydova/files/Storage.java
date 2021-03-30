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
    public void add(T element) {
        if (storage[storage.length-1] != null){
            increaseArray(storage);
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] == null) {
                    storage[i] = element;
                    return;
                }
            }
        }else{
            for (int i = 0; i < storage.length; i++) {
                if (storage[i] != null) {
                    storage[i] = element;
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
        }
        return (T) storage[index];
    }
}
