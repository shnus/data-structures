import java.util.NoSuchElementException;

import org.junit.Test;

import seminar1.iterators.PeekingIncreasingIterator;

/**
 * Класс тестирующий {@link seminar1.iterators.PeekingIncreasingIterator}
 */
public class TestPeekingIncreasingIterator {

    @Test(expected = NoSuchElementException.class)
    public void testEmptyIterator() {
        new PeekingIncreasingIterator(10, 10, 0, 1).next();
    }
}
