package ru.job4j.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }
    }

    public int size() {
        return size;
    }

    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> element = first;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        return element.value;

    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            Node<E> newNode = first;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return newNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> rsl = newNode;
                newNode = newNode.next;
                return rsl.value;
            }
        };
    }
}
