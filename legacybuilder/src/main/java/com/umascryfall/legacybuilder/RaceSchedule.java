package com.umascryfall.legacybuilder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import java.util.HashSet;
import java.util.Set;
import com.umascryfall.legacybuilder.enums.Scenario;

@Entity
@Table(name = "race_schedule")
public class RaceSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Scenario targetScenario;

    @ManyToMany
    @JoinTable(name = "schedule_races", joinColumns = @JoinColumn(name = "schedule_id"), inverseJoinColumns = @JoinColumn(name = "race_id"))
    private List<Race> scheduledRaces = new ArrayList<>();

    public RaceSchedule() {
    }

    // Add standard Getters and Setters for all fields below
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Race> getScheduledRaces() {
        return scheduledRaces;
    }

    public void setScheduledRaces(List<Race> scheduledRaces) {
        this.scheduledRaces = scheduledRaces;
    }

    public Scenario getTargetScenario() {
        return targetScenario;
    }

    public void setTargetScenario(Scenario targetScenario) {
        this.targetScenario = targetScenario;
    }

    /**
     * Validates the schedule to ensure no two races occur on the exact same turn.
     * 
     * @return true if the schedule is valid, false if there is a time collision.
     */
    public boolean isValidSchedule() {
        if (scheduledRaces == null || scheduledRaces.isEmpty()) {
            return true; // An empty schedule has no conflicts
        }

        Set<String> occupiedTurns = new HashSet<>();

        for (Race race : scheduledRaces) {
            // Create a unique fingerprint for the exact game turn
            // Example output: "CLASSIC-5-SECOND" (The Derby)
            String turnFingerprint = race.getCareerYear().name() + "-"
                    + race.getRaceMonth() + "-"
                    + race.getHalf().name();

            // HashSet.add() returns false if the item already exists in the set
            if (!occupiedTurns.add(turnFingerprint)) {
                System.out.println("Collision detected on turn: " + turnFingerprint);
                return false;
            }
        }

        return true; // No collisions found
    }
}