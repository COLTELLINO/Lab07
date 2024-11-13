package it.unibo.inner;

import java.util.Iterator;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {

    private T[] array = null;
    private int current = 0;
    private Predicate<T> predicate = null;            
    
        public IterableWithPolicyImpl(T[] elements) {
            this(elements, new Predicate<T>() {
                @Override
                public boolean test (T elem) {
                    return true;
                }
            });
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> predicate) {
        this.array = elements;
        this.predicate = predicate;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }

    @Override
    public String toString() {
    return java.util.Arrays.toString(array);
    }


    public class inner implements Iterator<T>{

        @Override
        public boolean hasNext() {
            while (current < array.length && !predicate.test(array[current])) {
                current++;
            }
            if (current < array.length) {
                return true;
            }
            else {
                return false;
            }
        }

        @Override
        public T next() {
            if (hasNext()) {
            return array[current++];
            }
            throw new  java.util.NoSuchElementException();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new inner();
    }
}
