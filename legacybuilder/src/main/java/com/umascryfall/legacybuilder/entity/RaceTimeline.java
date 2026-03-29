package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "race_instance")
public class RaceTimeline {

    @Id
    private int id; // Matches race.id

    private int raceId; // ID of the mapped race
    private int date;   // Year (e.g., Junior = 1, Classic = 2, Senior = 3)
    private int time;   // Half-month (e.g., 1 to 24 in a year)

    @jakarta.persistence.Transient
    private String raceName;

    public RaceTimeline() {}

    public String getRaceName() { return raceName; }
    public void setRaceName(String raceName) { this.raceName = raceName; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getRaceId() { return raceId; }
    public void setRaceId(int raceId) { this.raceId = raceId; }
    
    public int getDate() { return date; }
    public void setDate(int date) { this.date = date; }
    
    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }
}
