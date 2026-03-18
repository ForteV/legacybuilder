package com.umascryfall.legacybuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataSeederService {

    @Autowired
    private UmaMusumeRepository repository;

    @PostConstruct
    public void seedDatabase() {
        // Only run this if the database is empty so we don't duplicate data every time
        // we restart
        if (repository.count() == 0) {
            try {
                // 1. Read the JSON file from the resources folder
                ObjectMapper mapper = new ObjectMapper();
                InputStream inputStream = new ClassPathResource("raw_aptitudes.json").getInputStream();

                // Read the JSON as a list of Maps (Key-Value pairs)
                List<Map<String, Integer>> rawAptitudes = mapper.readValue(inputStream,
                        new TypeReference<List<Map<String, Integer>>>() {
                        });

                List<UmaMusume> finalUmaList = new ArrayList<>();

                // 2. Loop through every character in the JSON
                for (Map<String, Integer> raw : rawAptitudes) {
                    UmaMusume uma = new UmaMusume();

                    // Set the ID (Converting the integer ID like 100101 to a String)
                    uma.setId(String.valueOf(raw.get("id")));

                    // Translate the numbers to letters using our helper method below
                    uma.setTurf(translate(raw.get("turf")));
                    uma.setDirt(translate(raw.get("dirt")));
                    uma.setShortDistance(translate(raw.get("shortDistance")));
                    uma.setMileDistance(translate(raw.get("mileDistance")));
                    uma.setMediumDistance(translate(raw.get("mediumDistance")));
                    uma.setLongDistance(translate(raw.get("longDistance")));
                    uma.setFrontRunner(translate(raw.get("frontRunner")));
                    uma.setPaceChaser(translate(raw.get("paceChaser")));
                    uma.setLateSurger(translate(raw.get("lateSurger")));
                    // Using Integer for endCloser in case the JSON key mapping returns null
                    // unexpectedly
                    uma.setEndCloser(translate(raw.get("endCloser")));

                    finalUmaList.add(uma);
                }

                // 3. Save the entire translated list to PostgreSQL
                repository.saveAll(finalUmaList);
                System.out.println("SUCCESS: Database seeded with " + finalUmaList.size() + " Uma Musume units!");

            } catch (Exception e) {
                System.out.println("ERROR seeding database: " + e.getMessage());
            }
        } else {
            System.out.println("Database already contains data. Seeding skipped.");
        }
    }

    // Cygames encodes aptitudes as integers 1-8. This converts them back to G-S.
    private AptitudeGrade translate(Integer value) {
        if (value == null)
            return AptitudeGrade.G;
        return switch (value) {
            case 1 -> AptitudeGrade.G;
            case 2 -> AptitudeGrade.F;
            case 3 -> AptitudeGrade.E;
            case 4 -> AptitudeGrade.D;
            case 5 -> AptitudeGrade.C;
            case 6 -> AptitudeGrade.B;
            case 7 -> AptitudeGrade.A;
            case 8 -> AptitudeGrade.S;
            default -> AptitudeGrade.G;
        };
    }
}