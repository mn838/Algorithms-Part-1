/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private Item[] items;

    public RandomizedQueue() {
        this.size = 0;
        this.items = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    private void resize(int capacity) {
        Item[] newItems = (Item[]) new Object[capacity];
        for (int i = 0; i < this.size; i++) {
            newItems[i] = this.items[i];
        }
        this.items = newItems;
    }

    public void enqueue(Item item) {
        if (this.size == this.items.length) resize(items.length * 2);
        if (item == null) throw new IllegalArgumentException();
        this.items[this.size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.size == 0) throw new NoSuchElementException();
        int randIdx = StdRandom.uniformInt(0, this.size);
        Item res = this.items[randIdx];

        this.items[randIdx] = this.items[this.size - 1];
        this.items[this.size - 1] = null;

        if (this.size > 0 && this.size == items.length / 4) {
            resize(items.length / 2);
        }

        this.size--;
        return res;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.size == 0) throw new NoSuchElementException();
        int randIdx = StdRandom.uniformInt(0, this.size);
        Item res = this.items[randIdx];
        return res;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] shuffleItems;
        private int cap;

        public RandomIterator() {
            shuffleItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                shuffleItems[i] = items[i];
            }
            StdRandom.shuffle(shuffleItems);
            cap = 0;
        }

        public boolean hasNext() {
            return cap < shuffleItems.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return shuffleItems[cap++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
