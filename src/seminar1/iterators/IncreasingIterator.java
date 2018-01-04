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

    /**
     * Диапазон значений которые может вернуть итератор
     */
    protected final ValueRange iteratorRange;

    /**
     * Количество элементов которые вернул итератор
     */
    protected int currentStep;
    /**
     * Значение которое вернёт итератор при следующем вызове метода next
     */
    protected int currentValue;

    /**
     * Конструктор итератора возвращающего возрастающую последовательность
     *
     * @param startValue    — первое значение в итераторе.
     *                      Должно быть неотрицательным числом.
     * @param maxValue      — максимальное значение которое может вернуть итератор.
     *                      Должно быть положительным числом большим startValue
     * @param stepLimit     — максимальное количество элементов которое может вернуть итератор.
     *                      Должно быть неотрицательным числом.
     * @param maxStepGrowth — максимальное значение на которое может увеличиться текущее значение итератора.
     *                      Должно быть положительным числом.
     * @throws IllegalArgumentException если значения передаваемые в конструктор некорректны
     */
    public IncreasingIterator(int startValue, int maxValue, int stepLimit, int maxStepGrowth) {
        if (startValue < 0) {
            throw new IllegalArgumentException("startValue should be greater than or equal to zero");
        }
        if (maxValue < startValue) {
            throw new IllegalArgumentException("maxValue should be greater then start = " + startValue);
        }
        if (stepLimit < 0) {
            throw new IllegalArgumentException("stepLimit should be greater than or equal to zero");
        }
        if (maxStepGrowth < 0) {
            throw new IllegalArgumentException("maxStepGrowth should be positive");
        }
        this.maxStepGrowth = maxStepGrowth;
        this.stepLimit = stepLimit;
        this.random = new Random();
        this.iteratorRange = ValueRange.of(startValue, maxValue);

        this.currentStep = 0;
        this.currentValue = startValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return iteratorRange.isValidValue(currentValue) && currentStep < stepLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer next() {
        checkHasNext();
        int curr = currentValue;
        currentValue += random.nextInt(maxStepGrowth);
        currentStep++;
        return curr;
    }

    /**
     * Проверяет наличие следующего элемента в итераторе и кидает исключение если его нет
     *
     * @throws NoSuchElementException если в итераторе закончились элементы
     */
    protected void checkHasNext() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("currentValue = " + currentValue + ", currentStep = " + currentStep);
        }
    }
}
