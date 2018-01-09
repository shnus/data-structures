package seminar1.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {

    private int DEFAULT_CAPACITY = 8;
    private Key[] elementData;
    private Comparator<Key> comparator;
    private int capacity = DEFAULT_CAPACITY;
    private int size = 0;

    public ArrayPriorityQueue() {
        elementData = (Key[]) new Comparable[capacity];
    }

    public ArrayPriorityQueue(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        } else if (capacity > DEFAULT_CAPACITY) {
            this.capacity = capacity;
        }
        elementData = (Key[]) new Object[capacity];
    }

    public ArrayPriorityQueue(Comparator<Key> comparator) {
        elementData = (Key[]) new Object[capacity];
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        elementData[size] = key;
        size++;
        siftUp(size - 1);
        if (size == capacity) {
            grow();
        }
    }

    @Override
    public Key peek() {
        if (isEmpty()) {
            return null;
        } else {
            return elementData[0];
        }
    }

    @Override
    public Key extractMin() {
        if (isEmpty()) {
            return null;
        }
        Key result = elementData[0];
        elementData[0] = elementData[size - 1];
        size--;
        siftDown(0);
        if (capacity > size * 4 && size > DEFAULT_CAPACITY) {
            shrink();
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return size;
    }


    private void grow() {
        int newCapacity = capacity * 3 / 2;
        Key[] newElementData = (Key[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
        capacity = newCapacity;

    }

    private void shrink() {
        int newCapacity = capacity / 2;
        Key[] newElementData = (Key[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i++) {
            newElementData[i] = elementData[i];
        }
        elementData = newElementData;
        capacity = newCapacity;
    }


    private void siftUp(int i) {
        if (comparator == null) {
            siftUpWithoutComparator(i);
        } else {
            siftUpWithComparator(i);
        }
    }

    private void siftUpWithComparator(int i) {
        while (i > 0 && comparator.compare(elementData[parent(i)], elementData[i]) >= 0) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void siftUpWithoutComparator(int i) {
        while (i > 0 && elementData[parent(i)].compareTo(elementData[i]) >= 0) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private void siftDown(int i) {
        if (comparator == null) {
            siftDownWithoutComparator(i);
        } else {
            siftDownWithComparator(i);
        }
    }

    private void siftDownWithComparator(int i) {
        int maxIndex = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < size && (comparator.compare(elementData[l], elementData[maxIndex]) < 0)) {
            maxIndex = l;
        }

        if (r < size && (comparator.compare(elementData[r], elementData[maxIndex]) < 0)) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            siftDownWithComparator(maxIndex);
        }
    }

    private void siftDownWithoutComparator(int i) {
        int maxIndex = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < size && (elementData[l].compareTo(elementData[maxIndex]) < 0)) {
            maxIndex = l;
        }

        if (r < size && (elementData[r].compareTo(elementData[maxIndex]) < 0)) {
            maxIndex = r;
        }

        if (i != maxIndex) {
            swap(i, maxIndex);
            siftDownWithoutComparator(maxIndex);
        }
    }

    private void swap(int i, int j) {
        Key cur = elementData[i];
        elementData[i] = elementData[j];
        elementData[j] = cur;
    }

    private int parent(int i) {
        return (i + 1) / 2 - 1;
    }

    @Override
    public Iterator<Key> iterator() {
        return new PriorityQueueIterator();
    }

    private class PriorityQueueIterator implements Iterator {

        int cursor = 0;

        @Override
        public boolean hasNext() {
            return cursor < size;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Key result = elementData[cursor];
                cursor++;
                return result;
            }
        }
    }
}