package com.example.developers;

import com.devbase.dto.DeveloperDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    // ✅ Get all developers
    @GetMapping
    public ResponseEntity<List<DeveloperDTO>> getAllDevelopers() {
        List<DeveloperDTO> developers = service.getAllDevelopers();
        return ResponseEntity.ok(developers);
    }

    // ✅ Get developer by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeveloperDTO> getById(@PathVariable Long id) {
        DeveloperDTO dev = service.getDeveloperById(id);
        return ResponseEntity.ok(dev);
    }

    // ✅ Add a new developer
    @PostMapping
    public ResponseEntity<DeveloperDTO> addDeveloper(@Valid @RequestBody DeveloperDTO dto) {
        DeveloperDTO created = service.saveDeveloper(dto);
        return ResponseEntity.status(201).body(created);
    }

    // ✅ Update developer info
    @PutMapping("/{id}")
    public ResponseEntity<DeveloperDTO> updateDeveloper(@PathVariable Long id, @RequestBody DeveloperDTO updated) {
        DeveloperDTO updatedDev = service.updateDeveloper(id, updated);
        return ResponseEntity.ok(updatedDev);
    }

    // ✅ Delete developer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        service.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Optional health-check endpoint
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("👋 DevBase API is running and connected to PostgreSQL!");
    }
}
