package davydova.files;

import lombok.extern.slf4j.Slf4j;

/**
 * This is a class to operate with an array of instances of CacheElement class
 */

@Slf4j
public class Cache<T> {

    private int capacity;
    private CacheElement<T>[] cache;

    /**
     * Instantiates Cache class
     * @param capacity is an array capacity of instances of CacheElement class
     */
    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        this.capacity = capacity;
        cache = new CacheElement[capacity];
    }

    /**
     * Get an array of CacheElement class
     * @return array of instances of CacheElement class
     */
    public CacheElement<T>[] getCache() {
        return cache;
    }

    /**
     * Add an instance of CacheElement class of type T and index to the array of instances of CacheElement class
     * @param element is a field of instance of CacheElement class
     * @param index is a field of instance of CacheElement class
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
     * Delete the element from the array of instances of CacheElement class
     * @param element is a field of instance of CacheElement class
     */
    public void delete(T element) {
        log.debug("Log from delete(T) method of class Cache");
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                if (cache[i].getElement().equals(element)) {
                    System.arraycopy(cache, i + 1, cache, i, capacity - (i + 1));
                    return;
                }
            }
        }
    }

    /**
     * Check the presence of the instance of CacheElement class in array by the value of element
     * @param element is a field of instance of CacheElement class
     * @return true if CacheElement instance is present or false if it is absent
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
     * Check the presence of the instance of CacheElement class in array by the value of index
     * @param index is an index of CacheElement instance
     * @return true if CacheElement instance is present or false if it is absent
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
     * Shift the array element to the end of the array
     * @param index index of CacheElement instance
     * @return found array element
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
                    return cacheElement;
                }
            }
        }
        throw new ArrayNullPointerException("There is no element with such an index in the array");
    }

    /**
     * Clear an array of CacheElement instances
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        log.info("Cache is cleared up");
    }

    public static class CacheElement<T> {

        private int index;
        private T element;

        /**
         * Instantiates CacheElement class
         * @param element is a variable of CacheElement instance
         * @param index is a variable of CacheElement instance
         */
        public CacheElement(T element, int index) {
            this.element = element;
            this.index = index;
        }

        /**
         * Get field element of CacheElement class
         * @return field element
         */
        public T getElement() {
            return element;
        }

        /**
         * Get field index of CacheElement class
         * @return field index
         */
        public int getIndex() {
            return index;
        }

        /**
         * Compare instances if they are equal
         * @param object is a comparable instance
         * @return true if they are equal or false is they are not
         */
        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object object) {
            if (object == this) {
                return true;
            }
            if (!(object instanceof CacheElement)) {
                return false;
            }
            CacheElement<T> cacheElement = (CacheElement<T>) object;
            return element == cacheElement.element &&
                    index == cacheElement.index;
        }
    }
}
