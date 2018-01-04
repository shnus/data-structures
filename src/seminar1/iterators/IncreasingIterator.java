package seminar1.iterators;

import java.time.temporal.ValueRange;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Итератор возвращающий возрастающую последовательность
 */
public class IncreasingIterator implements Iterator<Integer> {

    protected final int maxStepGrowth;
    protected final int stepLimit;
    protected final Random random;
    protected final ValueRange iteratorRange;

    protected int currentStep;
    protected int currentValue;
    protected int nextValue;

    public IncreasingIterator(int startValue, int maxValue, int stepLimit, int maxStepGrowth) {
        if (startValue < 0) {
            throw new IllegalArgumentException("startValue should greater than or equal to zero");
        }
        if (maxStepGrowth < 0) {
            throw new IllegalArgumentException("maxStepGrowth should be positive");
        }
        if (maxValue < startValue) {
            throw new IllegalArgumentException("maxValue should be greater then start = " + startValue);
        }
        if (stepLimit < 1) {
            throw new IllegalArgumentException("stepLimit should be positive");
        }
        this.maxStepGrowth = maxStepGrowth + 1; //because in random.nextInt(upperBound) — upperBound is exclusive
        this.stepLimit = stepLimit;
        this.random = new Random();
        this.iteratorRange = ValueRange.of(startValue, maxValue);

        this.currentStep = 0;
        this.currentValue = startValue;
        this.nextValue = generateNextValue();
    }

    @Override
    public boolean hasNext() {
        return iteratorRange.isValidValue(nextValue) && currentStep < stepLimit;
    }

    @Override
    public Integer next() {
        checkHasNext();
        int curr = currentValue;
        currentValue = nextValue;
        nextValue = generateNextValue();
        currentStep++;
        return curr;
    }

    private int generateNextValue() {
        return currentValue + random.nextInt(maxStepGrowth);
    }

    protected void checkHasNext() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("currentValue = " + currentValue + ", currentStep = " + currentStep);
        }
    }
}
