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
                // 1. Read the NEW JSON file from the resources folder
                ObjectMapper mapper = new ObjectMapper();
                TypeReference<List<Map<String, Object>>> typeReference = new TypeReference<>() {};
                
                // Point this to your new file
                InputStream inputStream = new ClassPathResource("uma_master_data.json").getInputStream();

                // Read the JSON using the updated type reference
                List<Map<String, Object>> rawData = mapper.readValue(inputStream, typeReference);

                List<UmaMusume> finalUmaList = new ArrayList<>();

                // 2. Loop through every character in the JSON
                for (Map<String, Object> raw : rawData) {
                    UmaMusume uma = new UmaMusume();

                    // 1. Set the Base Info (Casting the generic Objects to their specific types)
                    uma.setId(String.valueOf(raw.get("id")));
                    uma.setNameEng((String) raw.get("nameEng"));
                    uma.setDefaultRarity((Integer) raw.get("defaultRarity"));

                    // Note: Since you are using the English patch, the Japanese names were
                    // overwritten in the database. We will leave nameJp null for now.
                    uma.setNameJp(null);

                    // 2. Set the Aptitudes using your existing translate method
                    // We cast the generic Object to an Integer before passing it to translate()
                    uma.setTurf(translate((Integer) raw.get("turf")));
                    uma.setDirt(translate((Integer) raw.get("dirt")));
                    uma.setShortDistance(translate((Integer) raw.get("shortDistance")));
                    uma.setMileDistance(translate((Integer) raw.get("mileDistance")));
                    uma.setMediumDistance(translate((Integer) raw.get("mediumDistance")));
                    uma.setLongDistance(translate((Integer) raw.get("longDistance")));
                    uma.setFrontRunner(translate((Integer) raw.get("frontRunner")));
                    uma.setPaceChaser(translate((Integer) raw.get("paceChaser")));
                    uma.setLateSurger(translate((Integer) raw.get("lateSurger")));
                    uma.setEndCloser(translate((Integer) raw.get("endCloser")));

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