package com.example.developers;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/developers")
@CrossOrigin(origins = "*")
public class DeveloperController {

    private final DeveloperService service;

    public DeveloperController(DeveloperService service) {
        this.service = service;
    }

    @GetMapping
    public List<Developer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Developer getById(@PathVariable int id) {
        return service.getById(id);
    }

    @PostMapping
    public Developer addDeveloper(@RequestBody Developer dev) {
        return service.addDeveloper(dev);
    }

    @PutMapping("/{id}")
    public Developer updateDeveloper(@PathVariable int id, @RequestBody Developer updated) {
        return service.updateDeveloper(id, updated);
    }

    @DeleteMapping("/{id}")
    public void deleteDeveloper(@PathVariable int id) {
        service.deleteDeveloper(id);
    }

    @GetMapping("/")
    public String home() {
        return "👋 Developer Skill API is running!";
    }
}
