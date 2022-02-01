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
        T old = storage.get(id);
        return storage.replace(id, old, model);

    }

    @Override
    public boolean delete(String id) {
        T model = storage.get(id);
        return  storage.remove(id,model);
    }

    @Override
    public T findById(String id) {
        T model = storage.get(id);
        return model;
    }
}
