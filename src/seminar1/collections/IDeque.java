package seminar1.collections;

/**
 * LIFO + FIFO = Last/First In First Out
 */
public interface IDeque<Item> extends Iterable<Item> {

    /**
     * Вставляет элемент в начало дека
     *
     * @param item - вставляемый элемент
     * @throws NullPointerException если item равен null
     */
    void pushFront(Item item);

    /**
     * Вставляет элемент в конец дека
     *
     * @param item - вставляемый элемент
     * @throws NullPointerException если item равен null
     */
    void pushBack(Item item);

    /**
     * Возвращает элемент из начала дека
     *
     * @return элемент находившийся в начале
     * @throws java.util.NoSuchElementException если дек пуст
     */
    Item popFront();

    /**
     * Возвращает элемент из конца дека
     *
     * @return элемент находившийся в конце
     * @throws java.util.NoSuchElementException если дек пуст
     */
    Item popBack();

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
