package com.umascryfall.legacybuilder.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class InheritanceProbabilityService {

    private final Random random = new Random();

    /**
     * Calculates the true mathematical chance of an inherited Spark triggering based on its base rate and Affinity.
     *
     * @param baseRate      The internal percentage chance (e.g., 0.15 for 15%) of the spark natively triggering.
     * @param totalAffinity The total raw Affinity score traversing the parent/grandparent tree.
     * @return The final percentage chance (0.0 to 1.0) for probability RNG.
     */
    public double calculateSparkTriggerChance(double baseRate, int totalAffinity) {
        // Spark Inheritance Formula: Base Rate * (1 + (Affinity / 100))
        double multiplier = 1.0 + ((double) totalAffinity / 100.0);
        return baseRate * multiplier;
    }

    /**
     * Simulates the random variable output of stat gains whenever a Blue Spark fires during April Inspiration.
     * (Turn 1 Inheritance grants a flat number depending on rank, but April is purely RNG-capped ranges).
     *
     * @param starRating The 1, 2, or 3-star value of the inherited Spark.
     * @return A numeric stat bonus mimicking the actual game boundaries.
     */
    public int rollAprilInspirationBlueStatBoost(int starRating) {
        return switch (starRating) {
            case 3 -> random.nextInt(28) + 1; // [1 to 28] Stats
            case 2 -> random.nextInt(16) + 1; // [1 to 16] Stats
            default -> random.nextInt(10) + 1; // [1 to 10] Stats
        };
    }
}
