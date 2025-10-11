package com.example.developers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class DeveloperSkillApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeveloperSkillApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(DeveloperService service) {
        return args -> {
            System.out.println("--------------------------------------------------");
            System.out.println("🚀 DevBase API is running!");
            System.out.println("🌍 Endpoint: http://localhost:8080/api/developers");
            System.out.println("--------------------------------------------------");
            System.out.println("🧩 Type 'add' to add a new developer or 'exit' to quit:");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("👋 Exiting DevBase CLI...");
                    break;
                }

                if (input.equalsIgnoreCase("add")) {
                    System.out.println("Enter developer name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter title: ");
                    String title = scanner.nextLine();

                    System.out.println("Enter skills (comma separated): ");
                    String skillsInput = scanner.nextLine();
                    var skills = Arrays.asList(skillsInput.split(",\\s*"));

                    System.out.println("Enter certifications (comma separated): ");
                    String certsInput = scanner.nextLine();
                    var certifications = Arrays.asList(certsInput.split(",\\s*"));

                    Developer dev = new Developer();
                    dev.setName(name);
                    dev.setTitle(title);
                    dev.setSkills(skills);
                    dev.setCertifications(certifications);

                    service.addDeveloper(dev);
                    System.out.println("✅ Developer added: " + name);
                    System.out.println("--------------------------------------------------");
                } else {
                    System.out.println("❌ Unknown command. Type 'add' or 'exit'.");
                }
            }
        };
    }
}
