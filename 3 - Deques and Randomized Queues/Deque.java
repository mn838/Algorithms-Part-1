/* *****************************************************************************
 *  Name:           Mahi Nuthanapati
 *  Date:           12/27/2024
 *  Description:    implementation of Deque data structure
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("item can't be null");
        Node ins = new Node();
        ins.item = item;
        ins.next = null;
        ins.prev = null;
        if (this.size == 0) {
            this.first = ins;
            this.last = ins;
        }
        else {
            Node oldFirst = this.first;
            oldFirst.prev = ins;
            ins.next = oldFirst;
            this.first = ins;
        }
        this.size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException("item can't be null");
        Node ins = new Node();
        ins.item = item;
        ins.next = null;
        ins.prev = null;
        if (this.size == 0) {
            this.first = ins;
            this.last = ins;
        }
        else {
            this.last.next = ins;
            ins.prev = this.last;
            this.last = ins;
        }
        this.size++;
    }

    public Item removeFirst() {
        if (this.size == 0) throw new NoSuchElementException("Empty Deque");
        Node res = this.first;
        this.first = this.first.next;
        if (this.first != null) {
            this.first.prev = null;
        }
        else {
            this.last = null;
        }
        this.size--;
        return res.item;
    }

    public Item removeLast() {
        if (this.size == 0) throw new NoSuchElementException("Empty Deque");
        Node res = this.last;
        this.last = this.last.prev;
        if (this.last != null) {
            this.last.next = null;
        }
        else {
            this.first = null;
        }
        this.size--;
        return res.item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item ret = current.item;
            current = current.next;
            return ret;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {

    }
}
