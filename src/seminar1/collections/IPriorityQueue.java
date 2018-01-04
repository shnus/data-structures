package seminar1.collections;

public interface IPriorityQueue<Key extends Comparable<Key>> extends Iterable<Key> {

    /**
     * Вставляет элемент в очередь с приоритетами
     *
     * @param key - вставляемый элемент
     * @throws NullPointerException если key равен null
     */
    void add(Key key);

    /**
     * Возвращает элемент на вершине очереди с приоритетами,
     * оставляя элемент в коллекции
     *
     * @return элемент сверху очереди
     * @throws java.util.NoSuchElementException если очередь пуста
     */
    Key peek();

    /**
     * Возвращает элемент на вершине очереди с приоритетами
     *
     * @return элемент сверху очереди
     * @throws java.util.NoSuchElementException если очередь пуста
     */
    Key extractMin();

    /**
     * Проверяет очередь на пустоту
     *
     * @return true если очередь пуста, false в противном случае
     */
    boolean isEmpty();

    /**
     * Возвращает количество элементов в очереди
     *
     * @return количество элементов в очереди
     */
    int size();
}
