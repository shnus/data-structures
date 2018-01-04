package seminar1.iterators;

/**
 * Класс реализующий итератор и позволяющий проверить значение следующего элемента без перехода к нему
 */
public class PeekingIncreasingIterator extends IncreasingIterator implements IPeekingIterator<Integer> {

    private boolean hasPeeked;
    private Integer peekedElement;

    /**
     * {@inheritDoc}
     */
    public PeekingIncreasingIterator(int startValue, int maxValue, int stepLimit, int maxStepGrowth) {
        super(startValue, maxValue, stepLimit, maxStepGrowth);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return hasPeeked || super.hasNext();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer next() {
        checkHasNext();
        if (hasPeeked) {
            hasPeeked = false;
            return peekedElement;
        } else {
            return super.next();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer peek() {
        if (!hasPeeked) {
            checkHasNext();
            peekedElement = super.next();
            hasPeeked = true;
        }
        return peekedElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(IPeekingIterator<Integer> other) {
        if (this.hasNext() && other.hasNext()) {
            return Integer.compare(this.peek(), other.peek());
        } else if (!this.hasNext() && !other.hasNext()) {
            return 0;
        } else if (this.hasNext()) {
            return 1;
        } else {
            return -1;
        }
    }
}
