/**
 *
 * This class Cache<T> includes an array of CacheElements<T> and methods operating with the array
 * author DavydovaVV
 * version 1.0 03/28/2021
 */

public class Cache<T> {
    private int capacity;
    private static int i;
    private CacheElement<T>[] cache;
    private static boolean foundIt = false;

    @SuppressWarnings("unchecked")

    /**
     * Конструктор, который создает массив элементов CacheElement
     */
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
        CacheElement<T> newElement = new CacheElement<>(element, index);
        boolean isPresent = isPresentElement(element);
        if ((i < capacity - 1) && (!isPresent)) {
            cache[i] = newElement;
        }
        if ((i == capacity - 1) && (!isPresent)) {
            cache[i] = newElement;
            }
            if ((i > capacity - 1) && (!isPresent)) {
                for (int j = 1; j < capacity; j++) {
                    cache[j - 1] = cache[j];
                }
                cache[capacity - 1] = newElement;
            }
            i++;
    }

    /**
     * Удалить элемент из массива CacheElement
     * @param element элемент, который нужно удалить
     */
    public void delete(T element) {
        for (int i = 0; i < cache.length; i++) {
            if ((cache[i] != null) && (cache[i].getElement().equals(element))) {
                for (int j = i + 1; j < cache.length - i; j++) {
                    cache[j - 1] = cache[j];
                }
                System.out.println("Элемент удален");
            }
        }
    }

    /**
     * Проверить наличие элемента в массиве по значению элемента
     * @param element элемент, который нужно проверить
     * @return возвращает true или false, есть ли элемент в массиве CacheElement
     */
    public boolean isPresentElement(T element) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if(cache[i].getElement().equals(element)) {
                    foundIt = true;
                }
            }
        }
        return foundIt;
    }

    /**
     * Проверить наличие элемента в массиве CacheElement по индексу
     * @param index индекс, который нужно проверить
     * @return возвращает true или false, есть ли элемент в массиве CacheElement
     */
    public boolean isPresentIndex(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                foundIt = (cache[i].getIndex() == index);
                }
            }
        System.out.println(foundIt);
        return foundIt;
        }

    /**
     * Найти элемент массива CacheElement и поместить в конец массива, сдвинув все элементы справа от него на одну позцию влево
     * @param index индекс, по которому нужно найти элемент
     * @return возвращает найденный элемент
     */
    public T get(int index) {
        int j = 0;
        for(; j < capacity; j++) {
            if ((cache[j].getIndex() == index) && (cache[j] != null)) {
                T foundElement = cache[j].getElement();
                int foundIndex = cache[j].getIndex();
                if ((j < capacity) && (cache[j] != null)) {
                    for (int i = j + 1; i < capacity; i++) {
                        cache[i - 1] = cache[i];
                    }
                }
                add(foundElement, foundIndex);
            }
        }
        return cache[i-1].getElement();
    }

    /**
     * Очистить массив CacheElement
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        i = 0;
        System.out.println("Кэш очисщен");
    }

    /*Вложенный класс CacheElement*/
    public class CacheElement<T> {
        T element;
        int index;

        /**
         * Конструктор CacheElement, в котором инициализируются поля element и index
         * @param element элемент массива CacheElement
         * @param index индекс элемента массива CacheElement
         */
        private CacheElement(T element, int index) {
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
         * @return возвращает индекс, относящийся к элемент массива
         */
        public int getIndex() {
            return index;
        }
    }
}
