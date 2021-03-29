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
    //private static int i = 0;
    //private static boolean foundIt = false;

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
           Object [] temp = new Object[array.length];
            for (int i = 0; i < array.length; i++) {
                temp[i] = array[i];
            }
            storage = temp;
        }else{
            for (int i = 0; i < array.length; i++) {
                storage[i] = array[i];
            }
        }
    }

    /**
     * Добавить элемент Т в массив Object
     * @param element элемент, добавляемый в массив Object
     */
    public void add(T element) {
        if (getLast().equals(null)) {
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].equals((null))) {
                    storage[i] = element;
                }
            }
        }else{
            increaseArray(storage);
            for (int i = 0; i < storage.length; i++) {
                if (storage[i].equals((null))) {
                    storage[i] = element;
                }
            }
        }
    }

    /**
     * Увеличить размер передаваемого массива
     */
    public Object[] increaseArray (Object[] array) {
        Object [] temp = new Object[(int) (1.5 * array.length)];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        return temp;
    }

    /**
     * Удалить последний элемент массива Object
     */
    public void delete() {
        if (cache.isPresentElement(getLast())) {
            cache.delete(getLast());
            T lastElement = getLast();
            lastElement = null;
        }
    }

    /**
     * Очистить массив Object и массив CacheElement объекта Cache
     */
    public void clear() {
        cache.clear();
        Arrays.fill(storage, null);
        System.out.println("storage очисщен");
    }

    /**
     * Получить последний элементы массива Object
     * @return возвращает последний элемент массива Object
     */
    public T getLast() {
        int i = 0;
        while(storage[i] != null) {
            i++;
        }
        System.out.println((T)storage[i-1]);
        return (T)storage[i-1];
    }

    /**
     * Получить элемент массива CacheElement объекта Cache по индексу или записать в массив CacheElement из массива Object
     * @param index индекс по которому проверяется наличие элемента массива CacheElement объекта Cache
     * @return возвращает элемент массива Object
     */
    public T get(int index) {
        if (!cache.isPresentIndex(index)) {
            cache.add((T) storage[index], index);
        }
    return (T) storage[index];
    }

}
