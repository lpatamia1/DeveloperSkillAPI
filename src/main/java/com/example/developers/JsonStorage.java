package com.example.developers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonStorage {
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("src/main/resources/developers.json");

    public List<Developer> readDevelopers() {
        try {
            if (!file.exists()) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Developer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveDevelopers(List<Developer> developers) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, developers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
