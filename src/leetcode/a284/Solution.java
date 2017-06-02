package leetcode.a284;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator<T> implements Iterator<T> {
    T nextElement = null;
    Iterator<T> iterator = null;

    public PeekingIterator(Iterator<T> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        if(nextElement == null) {
            nextElement = iterator.next();
        }
        return nextElement;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        if(nextElement != null) {
            try {
                return nextElement;
            }
            finally {
                nextElement = null;
            }
        }
        else {
            return iterator.next();
        }
    }

    @Override
    public boolean hasNext() {
        return nextElement != null || iterator.hasNext();
    }
}

public class Solution {


    public static void main(String[] args) {
        PeekingIterator pit = new PeekingIterator(Arrays.asList(1,2,3,4).iterator());
        pit.hasNext();
        pit.peek();
        pit.peek();
        pit.next();
        pit.next();
        pit.peek();
        pit.peek();
        pit.next();
        pit.hasNext();
        pit.peek();
        pit.hasNext();
        pit.next();
        pit.hasNext();
    }
}