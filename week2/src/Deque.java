import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 01/09/13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class Deque<ITEM_TYPE> implements Iterable<ITEM_TYPE> {

    private Node<ITEM_TYPE> head = null;
    private Node<ITEM_TYPE> tail = null;
    private int size = 0;

    // construct an empty deque
    public Deque() {

    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    private void checkIfNull(ITEM_TYPE item) {
        if (item == null) {
            throw new NullPointerException("Item added can't be null");
        }
    }

    // insert the item at the front
    public void addFirst(ITEM_TYPE item) {
        checkIfNull(item);
        head = new Node<ITEM_TYPE>(item).switchWith(null, head);
        if(tail == null) {
            tail = head;
        }
        size++;
    }

    // insert the item at the end
    public void addLast(ITEM_TYPE item) {
        checkIfNull(item);
        tail = new Node<ITEM_TYPE>(item).switchWith(tail, null);
        if(head == null) {
            head = tail;
        }
        size++;
    }

    private void checkIfDeckIsEmpty() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deck is empty");
        }
    }

    // delete and return the item at the front
    public ITEM_TYPE removeFirst() {
        checkIfDeckIsEmpty();
        size--;
        Node<ITEM_TYPE> previousHead = head;
        head = head.next;
        return previousHead.item;
    }

    // delete and return the item at the end
    public ITEM_TYPE removeLast() {
        checkIfDeckIsEmpty();
        size--;
        Node<ITEM_TYPE> previousTail = tail;
        tail = tail.prev;
        return previousTail.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<ITEM_TYPE> iterator() {
        return new DequeIterator();
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(E element) {
            this.item = element;
        }

        Node<E> switchWith(Node<E> prev, Node<E> next) {
            if (prev != null) {
                prev.next = this;
            }
            if (next != null) {
                next.prev = this;
            }
            return this;
        }
    }

    private class DequeIterator implements Iterator<ITEM_TYPE> {

        @Override
        public boolean hasNext() {
            return false;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public ITEM_TYPE next() {
            throw new NoSuchElementException("No more item");
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

}
