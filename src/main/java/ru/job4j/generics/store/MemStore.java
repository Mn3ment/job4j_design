package ru.job4j.generics.store;

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
            storage.put(id, model);
        return (storage.containsKey(id));
    }

    @Override
    public boolean delete(String id) {
        T model = storage.get(id);
        storage.remove(id);
        return (storage.containsKey(id) && Objects.equals(storage.get(id), model));
    }

    @Override
    public T findById(String id) {
        T model = storage.get(id);
        return (storage.containsKey(id) && Objects.equals(storage.get(id), model)) ? model : null;
    }
}
