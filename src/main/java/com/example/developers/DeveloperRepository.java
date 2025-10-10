package com.example.developers;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class DeveloperRepository {
    private final JsonStorage storage;
    private List<Developer> database;
    private int nextId = 1;

    public DeveloperRepository(JsonStorage storage) {
        this.storage = storage;
        this.database = new ArrayList<>(storage.readDevelopers());
        if (!database.isEmpty()) {
            nextId = database.stream().mapToInt(Developer::getId).max().orElse(0) + 1;
        }
    }

    public List<Developer> findAll() {
        return new ArrayList<>(database);
    }

    public Optional<Developer> findById(int id) {
        return database.stream().filter(d -> d.getId() == id).findFirst();
    }

    public Developer save(Developer dev) {
        if (dev.getId() == 0) {
            dev.setId(nextId++);
            database.add(dev);
        } else {
            database.removeIf(d -> d.getId() == dev.getId());
            database.add(dev);
        }
        storage.saveDevelopers(database);
        return dev;
    }

    public void delete(int id) {
        database.removeIf(d -> d.getId() == id);
        storage.saveDevelopers(database);
    }

    public boolean existsById(int id) {
        return database.stream().anyMatch(d -> d.getId() == id);
    }
}
