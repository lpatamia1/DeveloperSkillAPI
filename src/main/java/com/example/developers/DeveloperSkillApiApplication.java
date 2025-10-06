package com.example.developers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeveloperSkillApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeveloperSkillApiApplication.class, args);
        System.out.println("🚀 Developer Skill API running at http://localhost:8080/api/developers");
    }
}
