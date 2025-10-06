package com.example.developers;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DeveloperService {
    private Map<Integer, Developer> developers = new HashMap<>();
    private int nextId = 1;

    public List<Developer> getAll() {
        return new ArrayList<>(developers.values());
    }

    public Developer getById(int id) {
        return developers.get(id);
    }

    public Developer addDeveloper(Developer dev) {
        dev.setId(nextId++);
        developers.put(dev.getId(), dev);
        return dev;
    }

    public Developer updateDeveloper(int id, Developer updated) {
        Developer existing = developers.get(id);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setTitle(updated.getTitle());
            existing.setSkills(updated.getSkills());
            existing.setCertifications(updated.getCertifications());
        }
        return existing;
    }

    public void deleteDeveloper(int id) {
        developers.remove(id);
    }
}
