import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA. User: david Date: 01/09/13 Time: 23:06 To change this template use File | Settings | File Templates.
 */
public class RandomizedQueueTest {

    @Test
    public void should_add_element() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("hello");
        queue.enqueue("world");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void should_be_empty() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_npe_with_null() {
        new RandomizedQueue<String>().enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_nse_with_dequeue() {
        new RandomizedQueue<String>().dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_nse_with_sample() {
        new RandomizedQueue<String>().sample();
    }

    @Test
    public void should_enque_lot_of_stuff() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        queue.enqueue("4");
        queue.enqueue("5");
        queue.enqueue("6");
        queue.enqueue("7");
        queue.enqueue("8");
        queue.enqueue("9");
        queue.enqueue("10");
        assertEquals(10, queue.size());
    }

    @Test
    public void should_sample_item() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        assertEquals("1", queue.sample());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void should_dequeue() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        assertEquals("1", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void should_dequeue_random_item() {
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < 50; i++) {
            RandomizedQueue<String> queue = new RandomizedQueue<String>();
            queue.enqueue("1");
            queue.enqueue("2");
            queue.enqueue("3");

            queue.dequeue();

            for (int j = 0; j < 10; j++) {
                result.add(queue.sample());
            }
        }

        assertTrue(result.contains("3"));
    }

    @Test
    public void should_iterate() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        Iterator<String> iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
        assertFalse(iterator.hasNext());
    }


    @Test(expected = NoSuchElementException.class)
    public void should_throw_no_such_element() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("1");
        Iterator<String> iter = queue.iterator();
        iter.next();
        iter.next();
    }

}
