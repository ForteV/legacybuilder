package com.umascryfall.legacybuilder.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InheritanceProbabilityServiceTest {

    private final InheritanceProbabilityService service = new InheritanceProbabilityService();

    @Test
    void testSparkTriggerChanceMath() {
        // Base rate 15% (0.15) with 200 Affinity: 0.15 * (1 + 2) = 45% (0.45)
        double maxChance = service.calculateSparkTriggerChance(0.15, 200);
        assertEquals(0.45, maxChance, 0.001);

        // Base rate 10% (0.10) with 0 Affinity: 0.10 * 1 = 10% (0.10)
        double minChance = service.calculateSparkTriggerChance(0.10, 0);
        assertEquals(0.10, minChance, 0.001);

        // Base rate 20% (0.20) with 150 Affinity: 0.20 * (1 + 1.5) = 50% (0.50)
        double midChance = service.calculateSparkTriggerChance(0.20, 150);
        assertEquals(0.50, midChance, 0.001);
    }

    @Test
    void testAprilInspirationStatBoundaries() {
        // Roll 1,000 times to ensure RNG boundaries are strictly enforced.
        for (int i = 0; i < 1000; i++) {
            int roll1 = service.rollAprilInspirationBlueStatBoost(1);
            assertTrue(roll1 >= 1 && roll1 <= 10, "1-Star Blue Spark roll breached bounds: " + roll1);

            int roll2 = service.rollAprilInspirationBlueStatBoost(2);
            assertTrue(roll2 >= 1 && roll2 <= 16, "2-Star Blue Spark roll breached bounds: " + roll2);

            int roll3 = service.rollAprilInspirationBlueStatBoost(3);
            assertTrue(roll3 >= 1 && roll3 <= 28, "3-Star Blue Spark roll breached bounds: " + roll3);
        }
    }
}
