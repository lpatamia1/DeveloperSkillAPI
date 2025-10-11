package com.devbase.dto;

import lombok.Data;
import java.util.List;

@Data
public class DeveloperDTO {
    private Long id;
    private String name;
    private String title;
    private List<String> skills;
    private List<String> certifications;
}
