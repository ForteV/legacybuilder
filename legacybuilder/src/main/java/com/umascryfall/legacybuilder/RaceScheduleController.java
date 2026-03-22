package com.umascryfall.legacybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class RaceScheduleController {

    @Autowired
    private RaceScheduleRepository scheduleRepository;

    @PostMapping
    public ResponseEntity<?> createSchedule(@RequestBody RaceSchedule newSchedule) {

        // 1. The Bouncer: Run your custom validation logic
        if (!newSchedule.isValidSchedule()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Error: Schedule contains overlapping races on the same turn.");
        }

        // 2. The Vault: If it passes, save it to PostgreSQL
        RaceSchedule savedSchedule = scheduleRepository.save(newSchedule);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSchedule);
    }

    @GetMapping
    public List<RaceSchedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }
}