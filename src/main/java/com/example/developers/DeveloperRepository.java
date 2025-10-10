package com.example.developers;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DeveloperRepository {
    private final Map<Integer, Developer> database = new HashMap<>();
    private int nextId = 1;

    public List<Developer> findAll() {
        return new ArrayList<>(database.values());
    }

    public Optional<Developer> findById(int id) {
        return Optional.ofNullable(database.get(id));
    }

    public Developer save(Developer dev) {
        if (dev.getId() == 0) dev.setId(nextId++);
        database.put(dev.getId(), dev);
        return dev;
    }

    public void delete(int id) {
        database.remove(id);
    }

    public boolean existsById(int id) {
        return database.containsKey(id);
    }
}
