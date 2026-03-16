package com.umascryfall.legacybuilder;

import jakarta.persistence.*;

@Entity
@Table(name = "inheritance_factor")
public class InheritanceFactor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FactorColor color;

    private int starCount; // 1 to 3
    private String target;
    // this is a generic string that can be used to identify the factor, e.g.
    // "turf", "shortDistance", "frontRunner", etc. or a skill

    // constructors
    // generic constructor for deserialization
    public InheritanceFactor() {
    }

    // constructor for creating new factors
    public InheritanceFactor(FactorColor color, int starCount, String target) {
        this.color = color;
        this.starCount = starCount;
        this.target = target;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FactorColor getColor() {
        return color;
    }

    public void setColor(FactorColor color) {
        this.color = color;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

}
