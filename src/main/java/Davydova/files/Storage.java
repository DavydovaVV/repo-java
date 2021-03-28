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
    private static int i = 0;
    private static boolean foundIt = false;

    /**
     * Дефолтный конструктор, в котором создается массив Object и объект типа Cache
     */
    public Storage() {
        storage = new Object[10];
        cache = new Cache(10);
    }

    /**
     * Конструктор, который заполняет массив Object
     * @param array массив, который заполняет массив Object
     */
    public Storage(T[] array) {
        storage = new Object[10];
        cache = new Cache<T>(10);
        if (array.length >= storage.length) {
           Object [] temp = new Object[array.length];
            for (; i < array.length; i++) {
                temp[i] = array[i];
            }
            storage = temp;
        }else{
            for (; i < array.length; i++) {
                storage[i] = array[i];
            }
        }
        i = array.length - 1;
    }

    /**
     * Добавить элемент Т в массив Object
     * @param element элемент, добавляемый в массив Object
     */
    public void add(T element) {
        if (i < storage.length-1) {
            storage[i] = element;
        }else{
            Object [] temp = new Object[(int) (1.5*storage.length)];
            for (int j = 0; j < storage.length; j++) {
                temp[j] = storage[j];
            }
            temp[i+1] = element;
            storage = temp;
        }
        i++;
    }

    /**
     * Удалить последний элемент массива Object
     */
    public void delete() {
        if (cache.isPresentElement(getLast())) {
            cache.delete(getLast());
            storage[i] = null;
            i = i - 1;
        }
    }

    /**
     * Очистить массив Object и массив CacheElement объекта Cache
     */
    public void clear() {
        cache.clear();
        Arrays.fill(storage, null);
        i = 0;
        System.out.println("storage очисщен");
    }

    /**
     * Получить последний элементы массива Object
     * @return возвращает последний элемент массива Object
     */
    public T getLast() {
        int j = 0;
        while(storage[j] != null) {
            j++;
        }
        return (T)storage[j-1];
    }

    /**
     * Получить элемент массива CacheElement объекта Cache по индексу или записать в массив CacheElement из массива Object
     * @param index индекс по которому проверяется наличие элемента массива CacheElement объекта Cache
     * @return возвращает элемент массива Object
     */
    public T get(int index) {
        if (cache.isPresentIndex(index)) {
            foundIt = true;
        } else {
            cache.add((T) storage[index], index);
        }
    return cache.get(index);
    }

}
