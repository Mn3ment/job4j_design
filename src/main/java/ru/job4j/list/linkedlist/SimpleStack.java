package ru.job4j.list.linkedlist;

public class SimpleStack<T> {

    private ForwardLinked<T> linked = new ForwardLinked<T>();

    public T pop() {
        return linked.deleteFirst();
    }
    public boolean isEmpty() {
        return linked.isEmpty();
    }

    public void push(T value) {
        linked.addFirst(value);
    }
}
