import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

import java.security.Key;
import java.util.*;

/**
 * Класс тестирующий интерфейс IPriorityQueue<Integer> на основе ArrayPriorityQueue
 */
public class TestPriorityQueue {

    private IPriorityQueue<Integer> queue;
    private PriorityQueue<Integer> test;

    @Before
    public void init() {
        queue = new ArrayPriorityQueue<>();
    }

    /*
    Should throw exception if capacity < 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorParameterValidation() {
        queue = new ArrayPriorityQueue<>(-1);
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(queue.size(), 0);
    }

    @Test
    public void isNotEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.add(0);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertEquals(queue.size(), 1);
    }

    @Test
    public void sizeTest() {
        int count = 10;
        for (int i = 0; i < count; i++) {
            Assert.assertEquals(queue.size(), i);
            queue.add(i);
        }
        Assert.assertEquals(queue.size(), count);
        for (int i = 0; i < count; i++) {
            queue.extractMin();
            Assert.assertEquals(queue.size(), count - i - 1);
        }
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void peekEmptyTest() {
        Assert.assertEquals(null, queue.peek());
    }

    @Test
    public void peekSingleElementTest() {
        queue.add(0);
        Assert.assertEquals(Integer.valueOf(0), queue.peek());
    }

    @Test
    public void peekMultiElementTest() {
        queue.add(1);
        queue.add(0);
        queue.add(2);
        Assert.assertEquals(Integer.valueOf(0), queue.peek());
        Assert.assertEquals(Integer.valueOf(0), queue.peek());
    }

    @Test
    public void extractEmptyTest() {
        Assert.assertEquals(null, queue.extractMin());
    }

    @Test
    public void extractSingleTest() {
        queue.add(0);
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(Integer.valueOf(0), queue.extractMin());
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(null, queue.extractMin());
    }

    @Test
    public void extractMultiTest() {
        queue.add(1);
        queue.add(0);
        queue.add(2);
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals(Integer.valueOf(0), queue.extractMin());
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.extractMin());
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.extractMin());
        Assert.assertEquals(0, queue.size());
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(null, queue.extractMin());
    }

    /*
    Imitate queue real using.
    In the first part frequently adding to queue.
    In the second frequently extracting and peeking from queue.
    In the second part queue size frequently equal to zero and happens null==null checking.
     */
    @Test
    public void imitationTest() {
        test = new PriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int value = random.nextInt(1000);
            test.add(value);
            queue.add(value);
        }

        int count = 50000;
        for (int i = 0; i < count; i++) {
            int action = random.nextInt(10);
            int value = random.nextInt(1000);

            if (action < 6) {
                queue.add(value);
                test.add(value);
                Assert.assertEquals(test.size(), queue.size());
            } else if (action < 8) {
                Assert.assertEquals(test.peek(), queue.peek());
            } else if (action < 10) {
                Assert.assertEquals(test.poll(), queue.extractMin());
            }
        }

        count = 1000000;
        for (int i = 0; i < count; i++) {
            int action = random.nextInt(10);
            int value = random.nextInt(1000);

            if (action < 4) {
                Assert.assertEquals(test.poll(), queue.extractMin());
            } else if (action < 8) {
                Assert.assertEquals(test.peek(), queue.peek());
            } else if (action < 10) {
                queue.add(value);
                test.add(value);
                Assert.assertEquals(test.size(), queue.size());
            }
        }
    }

    /*
   Check that empty queues iterator give false hasNext.
    */
    @Test
    public void iteratorHasNextEmpty(){
        Assert.assertFalse(queue.iterator().hasNext());
    }

    /*
    Check that non empty queues iterator give true hasNext.
     */
    @Test
    public void iteratorHasNextNonEmpty(){
        queue.add(0);
        Assert.assertTrue(queue.iterator().hasNext());
    }

    /*
    Check that empty queues iterator throw exception on next method.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextEmpty(){
        queue.iterator().next();
    }


    @Test
    public void iteratorNextNonEmpty(){
        queue.add(0);
        Iterator iterator = queue.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(0), queue.extractMin());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(iterator.hasNext());

    }

    /*
    Check that queue size == iterator elements count.
     */
    @Test
    public void iteratorNextNonEmpty2(){
        int count = 1000;
        for(int i = 0; i < count; i++){
            queue.add(i);
        }
        Iterator iterator = queue.iterator();
        for(int i = 0; i < count; i++){
            Assert.assertTrue(iterator.hasNext());
            iterator.next();
        }
        Assert.assertFalse(iterator.hasNext());
        Assert.assertFalse(iterator.hasNext());
    }

    /*
    Foreach testing and size testing.
     */
    @Test
    public void iteratorNextNonEmpty3(){
        int count = 1000;
        for(int i = 0; i < count; i++){
            queue.add(i);
        }
        int i = 0;
        for(Integer value: queue){
            i++;
        }
        Assert.assertEquals(i,count);
    }

    /*
    Simple real using iterator test. Checking iterator 'size' and next returning elements.
     */
    @Test
    public void iteratorImitation(){
        test = new PriorityQueue<>();
        Random random = new Random();
        int count = 100000;
        for (int i = 0; i < count; i++) {
            int value = random.nextInt(1000);
            test.add(value);
            queue.add(value);
        }
        Iterator iterator = queue.iterator();
        Iterator testIterator = test.iterator();
        for(int i = 0; i < count; i++){
            Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
            Assert.assertEquals(testIterator.next(), iterator.next());
        }
        Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
        Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
    }

}
