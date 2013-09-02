import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: david
 * Date: 01/09/13
 * Time: 21:50
 * To change this template use File | Settings | File Templates.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head = null;
    private Node<Item> tail = null;
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

    private void checkIfNull(Item item) {
        if (item == null) {
            throw new NullPointerException("Item added can't be null");
        }
    }

    // insert the item at the front
    public void addFirst(Item item) {
        checkIfNull(item);
        head = new Node<Item>(item).switchWith(null, head);
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        checkIfNull(item);
        tail = new Node<Item>(item).switchWith(tail, null);
        if (head == null) {
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
    public Item removeFirst() {
        checkIfDeckIsEmpty();
        size--;
        Node<Item> previousHead = head;
        head = head.next;
        if(head == null) {
            tail = null;
        }
        return previousHead.item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        checkIfDeckIsEmpty();
        size--;
        Node<Item> previousTail = tail;
        tail = tail.prev;
        if(tail == null) {
            head = null;
        }
        return previousTail.item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(head);
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        private Node(E element) {
            this.item = element;
        }

        private Node<E> switchWith(Node<E> p, Node<E> n) {
            prev = p;
            if (p != null) {
                p.next = this;
            }
            next = n;
            if (n != null) {
                n.prev = this;
            }
            return this;
        }
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> head) {
            current = head;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new NoSuchElementException("No more item");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove not supported");
        }
    }

}
