package com.example.developers;

import com.devbase.dto.DeveloperDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeveloperService {
    private final DeveloperRepository repository;
    public DeveloperService(DeveloperRepository repository) {
        this.repository = repository;
    }

    public List<DeveloperDTO> getAllDevelopers() {
        log.info("Fetching all developers...");
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Developer addDeveloper(Developer dev) {
        return repository.save(dev);
    }

    public DeveloperDTO getDeveloperById(Long id) {
        Developer dev = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found with ID " + id));
        return toDTO(dev);
    }

    public DeveloperDTO saveDeveloper(DeveloperDTO dto) {
        Developer dev = toEntity(dto);
        Developer saved = repository.save(dev);
        log.info("Added developer: {}", saved.getName());
        return toDTO(saved);
    }

    public DeveloperDTO updateDeveloper(Long id, DeveloperDTO updated) {
        Developer existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Developer not found with ID " + id));

        existing.setName(updated.getName());
        existing.setTitle(updated.getTitle());
        existing.setSkills(updated.getSkills());
        existing.setCertifications(updated.getCertifications());

        Developer saved = repository.save(existing);
        log.info("Updated developer: {}", saved.getName());
        return toDTO(saved);
    }

    public void deleteDeveloper(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Developer not found with ID " + id);
        }
        repository.deleteById(id);
        log.warn("Deleted developer with ID {}", id);
    }

    private DeveloperDTO toDTO(Developer dev) {
        DeveloperDTO dto = new DeveloperDTO();
        dto.setId(dev.getId());
        dto.setName(dev.getName());
        dto.setTitle(dev.getTitle());
        dto.setSkills(dev.getSkills());
        dto.setCertifications(dev.getCertifications());
        return dto;
    }

    private Developer toEntity(DeveloperDTO dto) {
        Developer dev = new Developer();
        dev.setName(dto.getName());
        dev.setTitle(dto.getTitle());
        dev.setSkills(dto.getSkills());
        dev.setCertifications(dto.getCertifications());
        return dev;
    }
}
