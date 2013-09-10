import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA. User: david Date: 01/09/13 Time: 23:01 To change this template use File | Settings | File Templates.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Object[] items = new Object[5];
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        checkIsNull(item);
        items[size++] = item;
        if (items.length != size) {
            return;
        }
        items = Arrays.copyOf(items, size * 2);
    }

    private void checkIsNull(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
    }

    // delete and return a random item
    public Item dequeue() {
        checkIsEmpty();
        if (items.length >= size * 4) {
            items = Arrays.copyOf(items, size * 2);
        }
        int index = StdRandom.uniform(size--);
        Item toReturn = (Item) items[index];
        items[index] = items[size]; // swap & remove value
        return toReturn;
    }

    // return (but do not delete) a random item
    public Item sample() {
        checkIsEmpty();
        return (Item) items[StdRandom.uniform(size)];
    }

    private void checkIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator(items);
    }

    private class RandomIterator implements Iterator<Item> {

        private Object[] items;
        private int current = 0;

        private RandomIterator(Object[] items) {
            this.items = Arrays.copyOf(items, size);
            StdRandom.shuffle(this.items);
        }

        @Override
        public boolean hasNext() {
            return current < this.items.length;
        }

        @Override
        public Item next() {
            return (Item) items[current++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
