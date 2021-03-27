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

    @SuppressWarnings("unchecked")
    public Cache(int capacity) {
        this.capacity = capacity;
        cache = new CacheElement[capacity];
    }

    public void add(T element, int index) {
        CacheElement<T> newElement = new CacheElement<>(element, index);
        if (i < capacity - 1) {
            cache[i] = newElement;
        }
        if (i == capacity - 1) {
            cache[i] = newElement;
            }
            if (i > capacity - 1) {
                for (int j = 1; j < capacity; j++) {
                    cache[j - 1] = cache[j];
                }
                cache[capacity - 1] = newElement;
            }
            i++;
    }

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

    public boolean isPresentElement(T element) {
        System.out.print("Искомый элемент [" + element + "] находится в кэше: ");
        boolean foundIt = false;
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                boolean isPresent = (cache[i].getElement().equals(element));
                if (isPresent) {
                    foundIt = true;
                    break;
                }
            }
        }
        System.out.println(foundIt);
        return foundIt;
    }

    public boolean isPresentIndex(int index) {
        System.out.print("Искомый элемент с индексом [ " + index + " ] находится в кэше: ");
        boolean foundIt = false;
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null) {
                boolean isPresent = (cache[i].getIndex() == index);
                if (isPresent) {
                    foundIt = true;
                    break;
                }
            }
        }
        System.out.println(foundIt);
        return foundIt;
    }

    public void get(int index) {
        for (int i = 0; i < capacity; i++) {
            if (cache[i].getIndex() != index) {
                continue;
            }
            if (cache[i].getIndex() == index) {
                System.out.println("Искомый элемент с индексом [ " + index + " ]: " + cache[i].getElement());
                CacheElement<T> foundElement = cache[i];
                for (int j = i + 1; j < capacity; j++) {
                    cache[j - 1] = cache[j];
                }
                cache[capacity - 1] = foundElement;
                break;
            } else {
                System.out.println("Искомый элементы с индексом [ " + index + " ] не найден");
            }
        }
    }

    public void clear() {
        for (int i = 0; i < capacity; i++) {
            cache[i] = null;
        }
        i = 0;
        System.out.println("Кэш очисщен");
    }

    private class CacheElement<T> {
        T element;
        int index;

        private CacheElement(T element, int index) {
            this.element = element;
            this.index = index;
        }

        public T getElement() {
            return element;
        }

        public int getIndex() {
            return index;
        }
    }
}
