package com.example.developers;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String title;

    @ElementCollection
    private List<String> skills;

    @ElementCollection
    private List<String> certifications;
}
