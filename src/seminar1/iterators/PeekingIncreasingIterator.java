package seminar1.iterators;

public class PeekingIncreasingIterator extends IncreasingIterator implements IPeekingIterator<Integer> {

    private boolean hasPeeked;
    private Integer peekedElement;

    public PeekingIncreasingIterator(int startValue, int maxValue, int stepLimit, int maxStepGrowth) {
        super(startValue, maxValue, stepLimit, maxStepGrowth);
    }

    @Override
    public boolean hasNext() {
        return hasPeeked || super.hasNext();
    }

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

    @Override
    public Integer peek() {
        if (!hasPeeked) {
            checkHasNext();
            peekedElement = super.next();
            hasPeeked = true;
        }
        return peekedElement;
    }

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
