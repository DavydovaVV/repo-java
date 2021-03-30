/**
 *
 * This class Cache<T> includes an array of CacheElements<T> and methods operating with the array
 * author DavydovaVV
 * version 1.0 03/28/2021
 */

public class Cache<T> {
    private int capacity;
    private CacheElement<T>[] cache;

    /**
     * Конструктор, который создает массив элементов CacheElement
     */
    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        this.capacity = capacity;
        cache = new CacheElement[capacity];
    }

    /**
     * Добавить элемент, имеющий аргумент типа Т и индекс, в массив CacheElement
     * @param element элемент массива
     * @param index индекс элемента массива
     */
    public void add(T element, int index) {
        if (!isPresent(element)) {
            if (!(cache[capacity-1] == null)) {
                System.arraycopy(cache, 1, cache, 0, capacity - 1);
                cache[capacity-1] = new CacheElement<>(element, index);
                return;
            }
            if (cache[capacity-1] == null) {
                for (int i = 0; i < capacity; i++) {
                    if (cache[i] == null) {
                        cache[i] = new CacheElement<>(element, index);
                        return;
                    }
                }
            }
        }
    }

    /**
     * Удалить элемент из массива CacheElement
     * @param element элемент, который нужно удалить
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if (cache[i].getElement().equals(element)) {
                    System.arraycopy(cache, i + 1, cache, i, capacity - (i + 1));
                    System.out.println("Элемент удален");
                    return;
                }
            }
        }
    }

    /**
     * Проверить наличие элемента в массиве по значению элемента
     * @param element элемент, который нужно проверить
     * @return возвращает true или false, есть ли элемент в массиве CacheElement
     */
    public boolean isPresent (T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if (cache[i].getElement().equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверить наличие элемента в массиве CacheElement по индексу
     * @param index индекс, который нужно проверить
     * @return возвращает true или false, есть ли элемент в массиве CacheElement
     */
    public boolean isPresent (int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if (cache[i].getIndex() == index) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Найти элемент массива CacheElement и поместить в конец массива, сдвинув все элементы справа от него на одну позцию влево
     * @param index индекс, по которому нужно найти элемент
     * @return возвращает найденный элемент
     */
    public CacheElement<T> get(int index) {
        CacheElement<T> cacheElement = null;
        if (isPresent(index)) {
            for (int i = 0; i < capacity; i++) {
                if (cache[i].getIndex() == index) {
                    cacheElement = cache[i];
                    T foundElement = cache[i].getElement();
                    System.arraycopy(cache, i + 1, cache, i , capacity - (i + 1));
                    add(foundElement, index);
                    break;
                }
            }
        }else{
            System.out.println("Элемент не найден");
        }
        return (cacheElement);
    }

    /**
     * Очистить массив CacheElement
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        System.out.println("Кэш очищен");
    }

    /*Вложенный класс CacheElement*/
    public static class CacheElement<T> {
        private T element;
        private int index;

        /**
         * Конструктор CacheElement, в котором инициализируются поля element и index
         * @param element элемент массива CacheElement
         * @param index   индекс элемента массива CacheElement
         */
        public CacheElement(T element, int index) {
            this.element = element;
            this.index = index;
        }

        /**
         * Получить элемент массива CacheElement
         * @return возвращает элемент массива CacheElement
         */
        public T getElement() {
            return element;
        }

        /**
         * Получить индекс, относящийся к элементу массива CacheElement
         * @return возвращает индекс, относящийся к элементу массива
         */
        public int getIndex() {
            return index;
        }

        /**
         * @param object это передаваемый объект для сравнения
         * @return возвращает результат сравнения двух объектов
         */
        @SuppressWarnings("unchecked")
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (object == null || object.getClass() != this.getClass()) {
                return false;
            }
            CacheElement<T> cacheElement = (CacheElement<T>) object;
            return element == cacheElement.element &&
                    index == cacheElement.index;
        }
    }
}
