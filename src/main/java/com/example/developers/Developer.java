package com.example.developers;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "developer")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String title;

    @ElementCollection
    @CollectionTable(name = "developer_skills", joinColumns = @JoinColumn(name = "developer_id"))
    private List<String> skills;

    @ElementCollection
    @CollectionTable(name = "developer_certifications", joinColumns = @JoinColumn(name = "developer_id"))
    private List<String> certifications;
}
