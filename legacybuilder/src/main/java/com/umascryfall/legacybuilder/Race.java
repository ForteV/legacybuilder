package com.umascryfall.legacybuilder;

import com.umascryfall.legacybuilder.enums.*;
import jakarta.persistence.*;

@Entity
@Table(name = "race_catalog")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Basic Info ---
    private String nameEng;
    private String nameJp;

    @Enumerated(EnumType.STRING)
    private RaceGrade grade;

    // --- The Chronology ---
    @Enumerated(EnumType.STRING)
    private CareerYear careerYear;

    private int raceMonth;

    @Enumerated(EnumType.STRING)
    private MonthHalf half;

    // --- Course Details ---
    private String venue;

    @Enumerated(EnumType.STRING)
    private Surface surface;

    @Enumerated(EnumType.STRING)
    private DistanceCategory distanceCategory;

    private int distanceMeters;

    @Enumerated(EnumType.STRING)
    private TurnDirection turnDirection;

    public Race() {
    }

    // Add standard Getters and Setters for all fields below
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameJp() {
        return nameJp;
    }

    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

    public RaceGrade getGrade() {
        return grade;
    }

    public void setGrade(RaceGrade grade) {
        this.grade = grade;
    }

    public CareerYear getCareerYear() {
        return careerYear;
    }

    public void setCareerYear(CareerYear careerYear) {
        this.careerYear = careerYear;
    }

    public int getRaceMonth() {
        return raceMonth;
    }

    public void setRaceMonth(int raceMonth) {
        this.raceMonth = raceMonth;
    }

    public MonthHalf getHalf() {
        return half;
    }

    public void setHalf(MonthHalf half) {
        this.half = half;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Surface getSurface() {
        return surface;
    }

    public void setSurface(Surface surface) {
        this.surface = surface;
    }

    public DistanceCategory getDistanceCategory() {
        return distanceCategory;
    }

    public void setDistanceCategory(DistanceCategory distanceCategory) {
        this.distanceCategory = distanceCategory;
    }

    public int getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(int distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    public TurnDirection getTurnDirection() {
        return turnDirection;
    }

    public void setTurnDirection(TurnDirection turnDirection) {
        this.turnDirection = turnDirection;
    }

    // ... (generate the rest using VS Code: Right Click -> Source Action ->
    // Generate Getters and Setters)
}