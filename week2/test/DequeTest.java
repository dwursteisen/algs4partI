import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 01/09/13
 * Time: 21:54
 * To change this template use File | Settings | File Templates.
 */
public class DequeTest {

    @Test
    public void should_be_empty() {
        assertTrue(new Deque<String>().isEmpty());
        assertTrue(new Deque<String>().size() == 0);
    }

    @Test
    public void should_add_first() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("hello world");
        assertFalse(deque.isEmpty());
        assertTrue(deque.size() == 1);
    }

    @Test
    public void should_add_first_and_remove_first() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("hello world");
        assertEquals("hello world", deque.removeFirst());

    }

    @Test
    public void should_add_and_remove_last() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("world hello");
        assertEquals("world hello", deque.removeLast());
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_npe_will_null_addFirst() {
        new Deque<String>().addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_npe_will_null_addLast() {
        new Deque<String>().addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_nse_with_empty_deck_removeFirst() {
        new Deque<String>().removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_nse_with_empty_deck_removeLast() {
        new Deque<String>().removeLast();
    }

    @Test(expected = NoSuchElementException.class)
    public void should_throw_nse_with_empty_deck_iterator() {
        new Deque<String>().iterator().next();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void should_throw_unsupported_operation_on_remove_iterator() {
        new Deque<String>().iterator().remove();
    }

    @Test
    public void should_iterate_over() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("hello");
        deque.addLast("world");
        deque.addLast(" ! ");

        Iterator<String> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("hello", iterator.next());
        assertEquals("world", iterator.next());
        assertEquals(" ! ", iterator.next());
        assertFalse(iterator.hasNext());
    }


    @Test
    public void should_add_first_and_remove_last() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("hello");
        deque.removeLast();
        assertEquals(0, deque.size());
        assertTrue(deque.isEmpty());

        deque.addFirst("hello");
        deque.addFirst("hello");
        deque.addFirst("hello");
        deque.addFirst("hello");
        deque.addFirst("hello");
        deque.addFirst("hello");
        assertFalse(deque.isEmpty());

        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        deque.removeLast();
        assertFalse(deque.isEmpty());
        deque.removeLast();
        assertTrue(deque.isEmpty());
    }

}
