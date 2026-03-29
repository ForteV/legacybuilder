package com.umascryfall.legacybuilder.entity;

import com.umascryfall.legacybuilder.enums.AptitudeGrade;

/**
 * A working model applied to Umas to store their final flex-aptitudes.
 * Used during Phase 3 to grant overriding capabilities to Grandparents.
 */
public class AptitudeProfile {

    private boolean isManualOverride = false;

    // Distances
    private AptitudeGrade shortDistance = AptitudeGrade.G;
    private AptitudeGrade mileDistance = AptitudeGrade.G;
    private AptitudeGrade mediumDistance = AptitudeGrade.G;
    private AptitudeGrade longDistance = AptitudeGrade.G;

    // Surfaces
    private AptitudeGrade turf = AptitudeGrade.G;
    private AptitudeGrade dirt = AptitudeGrade.G;

    // Running Styles
    private AptitudeGrade frontRunner = AptitudeGrade.G;
    private AptitudeGrade paceChaser = AptitudeGrade.G;
    private AptitudeGrade lateSurger = AptitudeGrade.G;
    private AptitudeGrade endCloser = AptitudeGrade.G;

    public AptitudeProfile() {}

    public boolean isManualOverride() {
        return isManualOverride;
    }

    public void setManualOverride(boolean manualOverride) {
        isManualOverride = manualOverride;
    }

    public AptitudeGrade getShortDistance() { return shortDistance; }
    public void setShortDistance(AptitudeGrade shortDistance) { this.shortDistance = shortDistance; }

    public AptitudeGrade getMileDistance() { return mileDistance; }
    public void setMileDistance(AptitudeGrade mileDistance) { this.mileDistance = mileDistance; }

    public AptitudeGrade getMediumDistance() { return mediumDistance; }
    public void setMediumDistance(AptitudeGrade mediumDistance) { this.mediumDistance = mediumDistance; }

    public AptitudeGrade getLongDistance() { return longDistance; }
    public void setLongDistance(AptitudeGrade longDistance) { this.longDistance = longDistance; }

    public AptitudeGrade getTurf() { return turf; }
    public void setTurf(AptitudeGrade turf) { this.turf = turf; }

    public AptitudeGrade getDirt() { return dirt; }
    public void setDirt(AptitudeGrade dirt) { this.dirt = dirt; }

    public AptitudeGrade getFrontRunner() { return frontRunner; }
    public void setFrontRunner(AptitudeGrade frontRunner) { this.frontRunner = frontRunner; }

    public AptitudeGrade getPaceChaser() { return paceChaser; }
    public void setPaceChaser(AptitudeGrade paceChaser) { this.paceChaser = paceChaser; }

    public AptitudeGrade getLateSurger() { return lateSurger; }
    public void setLateSurger(AptitudeGrade lateSurger) { this.lateSurger = lateSurger; }

    public AptitudeGrade getEndCloser() { return endCloser; }
    public void setEndCloser(AptitudeGrade endCloser) { this.endCloser = endCloser; }
}
