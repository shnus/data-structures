import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

/**
 * Класс тестирующий интерфейс IPriorityQueue<Integer> на основе ArrayPriorityQueue
 */
public class TestPriorityQueue {

    private IPriorityQueue<Integer> queue;

    @Before
    public void init() {
        queue = new ArrayPriorityQueue<>();
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(queue.size(), 0);
    }

    @Test
    public void isNotEmpty() {
        queue.add(0);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(queue.size(), 1);
    }
}
