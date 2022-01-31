package ru.job4j.list.dynamic;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class SimpleArrayList<T> implements List<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
        this.size = 0;
    }

    private boolean isFullArray() {
        return size == container.length;
    }

    @Override
    public void resize() {
        T[] oldContainer = this.container;
        this.container = (T[]) new Object[oldContainer.length * 2];
        for (int i = 0; i < size ; i++) {
            this.container[i] = oldContainer[i];
        }
    }

    @Override
    public void add(T value) {
        if (isFullArray()) {
            resize();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, container.length);
        T temp = container[index];
        container[index] = newValue;
        return temp;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, container.length);
        T temp = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size --;
/*        if (index >= 0 && index < size) {
            for (int i = index; i < size - 1; i++) {
                container[i] = container[i + 1];
            }
            size--;
        }*/
        modCount++;
        return temp;
    }



    @Override
    public T get(int index) {
        Objects.checkIndex(index, container.length);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;
            private final int expectedModCount = modCount;
            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[index++];
            }
        };
    }
}
