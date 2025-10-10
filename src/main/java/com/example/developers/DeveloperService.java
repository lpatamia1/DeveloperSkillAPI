package com.example.developers;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class DeveloperService {
    private final DeveloperRepository repository;

    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public List<Developer> getAll() {
        return repository.findAll();
    }

    public Developer getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found"));
    }

    public Developer addDeveloper(Developer dev) {
        return repository.save(dev);
    }

    public Developer updateDeveloper(int id, Developer updated) {
        Developer existing = getById(id);
        existing.setName(updated.getName());
        existing.setTitle(updated.getTitle());
        existing.setSkills(updated.getSkills());
        existing.setCertifications(updated.getCertifications());
        return repository.save(existing);
    }

    public void deleteDeveloper(int id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Developer not found");
        }
        repository.deleteById(id); // 
    }
}
