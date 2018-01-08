import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;
import seminar1.collections.LinkedDeque;

import javax.swing.text.html.HTMLDocument;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Класс тестирующий интерфейс {@link IDeque<Integer>} в двух реализациях:
 * 1) на массиве {@link CyclicArrayDeque<Integer>}
 * 2) на списке {@link LinkedDeque<Integer>}
 */
@RunWith(value = Parameterized.class)
public class TestDeque {

    @Parameterized.Parameter()
    public Class<?> testClass;

    private IDeque<Integer> deque;
    private LinkedList<Integer> list;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Class<?>> data() {
        return Arrays.asList(
                CyclicArrayDeque.class,
                LinkedDeque.class
        );
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            deque = (IDeque<Integer>) testClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(deque.size(), 0);
    }

    /*
    Checking that pushing back works right. And it grows correctly,
     */
    @Test
    public void pushBackTest() {
        int count = 300;
        for (int i = 0; i < count; i++) {
            deque.pushBack(i);
        }
        Assert.assertEquals(deque.size(), count);
    }

    /*
    Checking that pushing front works right. And it grows correctly,
     */
    @Test
    public void pushFrontTest() {
        int count = 300;
        for (int i = 0; i < count; i++) {
            deque.pushFront(i);
        }
        Assert.assertEquals(deque.size(), count);
    }

    /*
    Randomly pushing to the front and back
     */
    @Test
    public void pushBackAndFrontTest() {
        int count = 500;
        randomDequePushing(deque, 500);
        Assert.assertEquals(deque.size(), count);
    }


    /*
    Pop front checking
     */
    @Test
    public void popFrontTest() {
        int countPush = 300;
        int countPop = 300;
        randomDequePushing(deque, countPush);
        for (int i = 0; i < countPop; i++) {
            deque.popFront();
            Assert.assertEquals(deque.size(), countPush - i - 1);
        }
        Assert.assertEquals(deque.isEmpty(), true);
    }

    /*
    Checking that deque throws exception when we try pop front when it is empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void popFrontExceptionTest() {
        int countPush = 300;
        int countPop = 301;
        randomDequePushing(deque, countPush);
        for (int i = 0; i < countPop; i++) {
            deque.popFront();
        }

    }

    /*
    Pop back checking
     */
    @Test
    public void popBackTest() {
        int countPush = 300;
        int countPop = 300;
        randomDequePushing(deque, countPush);
        for (int i = 0; i < countPop; i++) {
            deque.popBack();
            Assert.assertEquals(deque.size(), countPush - i - 1);
        }
        Assert.assertEquals(deque.isEmpty(), true);
    }

    /*
    Checking that deque throws exception when we try pop back when it is empty.
     */
    @Test(expected = NoSuchElementException.class)
    public void popBackExceptionTest() {
        int countPush = 300;
        int countPop = 301;
        RandomDequePoping(deque, countPush);
        for (int i = 0; i < countPop; i++) {
            deque.popBack();
        }

    }

    /*
    Imitate deque real using.
    In the first part frequently pushing to deque.
    In the second frequently poping from deque.
    Here is very low chance to catch NoSuchElementException.
    */
    @Test
    public void dequeImitation() {
        try {
            list = new LinkedList<>();
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                list.addLast(i);
                deque.pushBack(i);
            }
            int count = 500000;
            for (int i = 0; i < count; i++) {
                int action = random.nextInt(10);
                int value = random.nextInt(1000);

                if (action < 3) {
                    deque.pushBack(value);
                    list.addLast(value);
                    Assert.assertEquals(list.size(), deque.size());
                } else if (action < 6) {
                    deque.pushFront(value);
                    list.addFirst(value);
                    Assert.assertEquals(list.size(), deque.size());
                } else if (action < 8) {
                    Assert.assertEquals(list.pollFirst(), deque.popFront());
                } else if (action < 10) {
                    Assert.assertEquals(list.pollLast(), deque.popBack());
                }
            }

            count = 400000;
            for (int i = 0; i < count; i++) {
                int action = random.nextInt(10);
                int value = random.nextInt(1000);

                if (action > 7) {
                    deque.pushBack(value);
                    list.addLast(value);
                    Assert.assertEquals(list.size(), deque.size());
                } else if (action > 5) {
                    deque.pushFront(value);
                    list.addFirst(value);
                    Assert.assertEquals(list.size(), deque.size());
                } else if (action > 2) {
                    Assert.assertEquals(list.pollFirst(), deque.popFront());
                } else if (action > -1) {
                    Assert.assertEquals(list.pollLast(), deque.popBack());
                }
            }

        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    /*
    Check that empty deques iterator give false hasNext.
     */
    @Test
    public void iteratorHasNextEmpty(){
        Assert.assertEquals(false, deque.iterator().hasNext());
    }

    /*
    Check that non empty deques iterator give true hasNext.
     */
    @Test
    public void iteratorHasNextNonEmpty(){
        deque.pushBack(1);
        Assert.assertEquals(true, deque.iterator().hasNext());
    }

    /*
    Check that empty deques iterator throw exception on next method.
     */
    @Test(expected = NoSuchElementException.class)
    public void iteratorNextEmpty(){
        deque.iterator().next();
    }


    @Test
    public void iteratorNextNonEmpty(){
        deque.pushBack(1);
        Iterator iterator = deque.iterator();
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(true, iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(1), deque.popBack());
        Assert.assertEquals(false, iterator.hasNext());
        Assert.assertEquals(false, iterator.hasNext());

    }

    /*
    Check that deque size == iterator elements count.
     */
    @Test
    public void iteratorNextNonEmpty2(){
        int count = 1000;
        randomDequePushing(deque,count);
        Iterator iterator = deque.iterator();
        for(int i = 0; i < count; i++){
            Assert.assertEquals(true, iterator.hasNext());
            iterator.next();
        }
        Assert.assertEquals(false, iterator.hasNext());
        Assert.assertEquals(false, iterator.hasNext());
    }

    /*
    Simple real using iterator test. Checking iterator 'size' and next returning elements.
     */
    @Test
    public void iteratorImitation(){
        list = new LinkedList<>();
        int count = 10000;
        for (int i = 0; i < count; i++) {
            list.addLast(i);
            deque.pushBack(i);
        }
        Iterator iterator = deque.iterator();
        Iterator testIterator = list.iterator();
        for(int i = 0; i < count; i++){
            Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
            Assert.assertEquals(testIterator.next(), iterator.next());
        }
        Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
        Assert.assertEquals(testIterator.hasNext(), iterator.hasNext());
    }

    

    private void randomDequePushing(IDeque deque, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextInt(2) == 0) {
                deque.pushFront(i);
            } else {
                deque.pushBack(i);
            }
        }
    }

    private void RandomDequePoping(IDeque deque, int count) {
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            if (random.nextInt(2) == 0) {
                deque.popFront();
            } else {
                deque.popBack();
            }
        }
    }

}
