package com.umascryfall.legacybuilder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "registered_parent")
public class RegisteredParent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The legacy uma
    // Links to the base catalog entry (Many instances can point to one base Uma)
    @ManyToOne
    @JoinColumn(name = "base_uma_id")
    private UmaMusume baseUma;

    // an arraylist of factors that this parent has, e.g. "turf", "shortDistance",
    // "frontRunner", etc. or a skill
    // Links to the factors. CascadeType.ALL means if you delete this parent, its
    // factors are deleted too.
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id") // foreign key in InheritanceFactor table
    private List<InheritanceFactor> factors = new ArrayList<>();

    // The sub-legacy for this parent
    // Links to the parents in the tree (Self-referencing foreign keys)
    @ManyToOne
    @JoinColumn(name = "parent1_id")
    private RegisteredParent parent1;

    @ManyToOne
    @JoinColumn(name = "parent2_id")
    private RegisteredParent parent2;

    // constructors
    public RegisteredParent() {
    }

    public RegisteredParent(UmaMusume baseUma) {
        this.baseUma = baseUma;
    }

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UmaMusume getBaseUma() {
        return baseUma;
    }

    public void setBaseUma(UmaMusume baseUma) {
        this.baseUma = baseUma;
    }

    public List<InheritanceFactor> getFactors() {
        return factors;
    }

    public void setFactors(List<InheritanceFactor> factors) {
        this.factors = factors;
    }

    public RegisteredParent getParent1() {
        return parent1;
    }

    public void setParent1(RegisteredParent parent1) {
        this.parent1 = parent1;
    }

    public RegisteredParent getParent2() {
        return parent2;
    }

    public void setParent2(RegisteredParent parent2) {
        this.parent2 = parent2;
    }

}
