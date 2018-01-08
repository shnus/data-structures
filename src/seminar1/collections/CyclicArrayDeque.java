package seminar1.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CyclicArrayDeque<Item> implements IDeque<Item> {

    private static final int DEFAULT_CAPACITY = 8;
    private Item[] elementData;
    //possible size == capacity - 2
    private int capacity = DEFAULT_CAPACITY;
    //pointer before first element
    private int front = 0;
    //pointer after last element
    private int back = 0;

    public CyclicArrayDeque() {
        elementData = (Item[]) new Object[capacity];
    }

    public CyclicArrayDeque(int initCapacity) {
        if (initCapacity < 0) {
            throw new IllegalArgumentException();
        } else {
            if (initCapacity > capacity) {
                capacity = initCapacity;
            }
            elementData = (Item[]) new Object[capacity];
        }
    }


    @Override
    public void pushFront(Item item) {
        elementData[front] = item;
        if (back == front) {
            back = (back + 1) % capacity;
        }
        front--;
        if (front < 0) {
            front = capacity - 1;
        }

        if (isCloseToFull()) {
            grow();
        }
    }

    @Override
    public void pushBack(Item item) {
        elementData[back] = item;
        if (back == front) {
            front--;
            if (front < 0) {
                front = capacity - 1;
            }
        }
        back = (back + 1) % capacity;

        if (isCloseToFull()) {
            grow();
        }
    }

    @Override
    public Item popFront() {
        if (size() < 1) {
            throw new NoSuchElementException();
        }
        front = (front + 1) % capacity;
        Item item = elementData[front];
        if (capacity / 4 > size() && size() > DEFAULT_CAPACITY) {
            shrink();
        }
        return item;
    }

    @Override
    public Item popBack() {
        if (size() < 1) {
            throw new NoSuchElementException();
        }
        back--;
        if (back < 0) {
            back = capacity - 1;
        }
        Item item = elementData[back];
        if (capacity / 4 > size() && size() > DEFAULT_CAPACITY) {
            shrink();
        }
        return item;
    }

    @Override
    public boolean isEmpty() {
        if ((back == front) || (back - front == 1) || (back == 0 && front == capacity - 1)) {
            return true;
        } else {
            return false;
        }
    }

    //Invoke only when u push element
    public boolean isCloseToFull() {
        int difference = back - front;
        if (difference == -1 || difference == capacity - 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        if (back >= front) {
            return back - front - 1;
        } else {
            return capacity - (front - back) - 1;
        }
    }

    private void grow() {
        int newCapacity = capacity * 3 / 2;
        Object[] newElementData = new Object[newCapacity];
        back = copyCyclic(elementData, newElementData, front, back);
        elementData = (Item[]) newElementData;
        capacity = newCapacity;
        front = capacity - 1;
    }

    private void shrink() {
        int newCapacity = capacity / 2;
        Object[] newElementData = new Object[newCapacity];
        back = copyCyclic(elementData, newElementData, front, back);
        elementData = (Item[]) newElementData;
        capacity = newCapacity;
        front = capacity - 1;
    }

    private int copyCyclic(Object[] from, Object[] to, Integer front, Integer back) {
        int j = 0;
        if (back > front) {
            for (int i = front + 1; i < back; i++) {
                to[j++] = from[i];
            }
        } else {
            for (int i = front + 1; i < capacity; i++) {
                to[j++] = from[i];
            }
            for (int i = 0; i < back; i++) {
                to[j++] = from[i];
            }
        }
        return j;

    }

    @Override
    public Iterator<Item> iterator() {
        /* TODO: implement it */
        return null;
    }
}
