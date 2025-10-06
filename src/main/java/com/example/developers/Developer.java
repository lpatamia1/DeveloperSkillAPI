package com.example.developers;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Developer {
    private int id;
    private String name;
    private String title;
    private List<String> skills = new ArrayList<>();
    private List<String> certifications = new ArrayList<>();
}
