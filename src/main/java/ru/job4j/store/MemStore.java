package ru.job4j.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        if (storage.containsKey(id)) {
            storage.put(id, model);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        T model = storage.get(id);
        if (storage.containsKey(id) && Objects.equals(storage.get(id), model)) {
            storage.remove(id);
            return true;
        } else
            return false;
    }

    @Override
    public T findById(String id) {
        T model = storage.get(id);
        if (storage.containsKey(id) && Objects.equals(storage.get(id), model)) {
            return model;
        }
        return null;
    }
}
