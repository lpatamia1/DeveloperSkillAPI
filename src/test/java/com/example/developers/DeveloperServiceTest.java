package com.example.developers;

import com.devbase.dto.DeveloperDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * ✅ DeveloperServiceTest — Unit tests for DeveloperService methods.
 */
public class DeveloperServiceTest {

    private DeveloperRepository repository;
    private DeveloperService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(DeveloperRepository.class);
        service = new DeveloperService(repository);
    }

    // ➕ Test saving a developer
    @Test
    void testSaveDeveloper() {
        Developer developer = new Developer();
        developer.setName("Lily");
        developer.setTitle("Engineer");

        // Mock repository save behavior — repository works with entities
        when(repository.save(Mockito.any(Developer.class))).thenReturn(developer);

        DeveloperDTO dto = new DeveloperDTO();
        dto.setName("Lily");
        dto.setTitle("Engineer");

        DeveloperDTO saved = service.saveDeveloper(dto);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("Lily");
        assertThat(saved.getTitle()).isEqualTo("Engineer");
    }

    // 🧠 Test retrieving all developers
    @Test
    void testGetAllDevelopers() {
        Developer dev = new Developer();
        dev.setName("Bob");
        dev.setTitle("Backend Developer");

        // Mock repository returning list of entities
        when(repository.findAll()).thenReturn(List.of(dev));

        List<DeveloperDTO> result = service.getAllDevelopers();

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getName()).isEqualTo("Bob");
    }
}
