package com.umascryfall.legacybuilder;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "uma_musume")
public class UmaMusume {

    // Basic Info
    @Id
    private String id;

    private String nameEng;
    private String nameJp;
    private int defaultRarity;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade turf;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade dirt;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade shortDistance;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade mileDistance;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade mediumDistance;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade longDistance;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade frontRunner;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade paceChaser;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade lateSurger;

    @Enumerated(EnumType.STRING)
    private AptitudeGrade endCloser;

    @Transient
    private Skill uniqueSkill;

    @Transient
    private List<Skill> initialSkills;

    public UmaMusume() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getDefaultRarity() {
        return defaultRarity;
    }

    public void setDefaultRarity(int defaultRarity) {
        this.defaultRarity = defaultRarity;
    }

    public AptitudeGrade getTurf() {
        return turf;
    }

    public void setTurf(AptitudeGrade turf) {
        this.turf = turf;
    }

    public AptitudeGrade getDirt() {
        return dirt;
    }

    public void setDirt(AptitudeGrade dirt) {
        this.dirt = dirt;
    }

    public AptitudeGrade getShortDistance() {
        return shortDistance;
    }

    public void setShortDistance(AptitudeGrade shortDistance) {
        this.shortDistance = shortDistance;
    }

    public AptitudeGrade getMileDistance() {
        return mileDistance;
    }

    public void setMileDistance(AptitudeGrade mileDistance) {
        this.mileDistance = mileDistance;
    }

    public AptitudeGrade getMediumDistance() {
        return mediumDistance;
    }

    public void setMediumDistance(AptitudeGrade mediumDistance) {
        this.mediumDistance = mediumDistance;
    }

    public AptitudeGrade getLongDistance() {
        return longDistance;
    }

    public void setLongDistance(AptitudeGrade longDistance) {
        this.longDistance = longDistance;
    }

    public AptitudeGrade getFrontRunner() {
        return frontRunner;
    }

    public void setFrontRunner(AptitudeGrade frontRunner) {
        this.frontRunner = frontRunner;
    }

    public AptitudeGrade getPaceChaser() {
        return paceChaser;
    }

    public void setPaceChaser(AptitudeGrade paceChaser) {
        this.paceChaser = paceChaser;
    }

    public AptitudeGrade getLateSurger() {
        return lateSurger;
    }

    public void setLateSurger(AptitudeGrade lateSurger) {
        this.lateSurger = lateSurger;
    }

    public AptitudeGrade getEndCloser() {
        return endCloser;
    }

    public void setEndCloser(AptitudeGrade endCloser) {
        this.endCloser = endCloser;
    }

    public Skill getUniqueSkill() {
        return uniqueSkill;
    }

    public void setUniqueSkill(Skill uniqueSkill) {
        this.uniqueSkill = uniqueSkill;
    }

    public List<Skill> getInitialSkills() {
        return initialSkills;
    }

    public void setInitialSkills(List<Skill> initialSkills) {
        this.initialSkills = initialSkills;
    }

}