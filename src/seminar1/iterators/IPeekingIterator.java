package seminar1.iterators;

import java.util.Iterator;

/**
 * Created by Nechaev Mikhail
 * Since 18/10/16.
 */
public interface IPeekingIterator<E> extends Iterator<E>, Comparable<IPeekingIterator<E>> {

    /**
     * Показывает следующий элемент содержащийся в итераторе оставляя указатель итератора на текущем
     *
     * @return значение следующего элемента в итераторе
     * @throws java.util.NoSuchElementException если метод {@link Iterator#hasNext()} возвращает false
     */
    E peek();
}
