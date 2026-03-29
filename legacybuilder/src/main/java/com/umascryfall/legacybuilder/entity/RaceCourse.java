package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "race_course_set")
public class RaceCourse {

    @Id
    private int id;
    
    // Derived from master.mdb 'race_course_set'
    private int raceTrackId;
    private int distance; // Length in meters (e.g. 1600 for Mile)
    private int ground;   // Usually 1 for Turf, 2 for Dirt in Cygames data

    public RaceCourse() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getRaceTrackId() { return raceTrackId; }
    public void setRaceTrackId(int raceTrackId) { this.raceTrackId = raceTrackId; }
    
    public int getDistance() { return distance; }
    public void setDistance(int distance) { this.distance = distance; }
    
    public int getGround() { return ground; }
    public void setGround(int ground) { this.ground = ground; }
}
