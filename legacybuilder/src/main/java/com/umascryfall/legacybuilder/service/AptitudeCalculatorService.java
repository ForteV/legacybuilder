package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.enums.AptitudeGrade;
import org.springframework.stereotype.Service;

@Service
public class AptitudeCalculatorService {

    /**
     * Calculates the Turn 1 aptitude boost based on total inherited Pink Stars.
     * Note: Turn 1 inheritance is hard-capped at rank A. It is impossible to achieve S rank Turn 1.
     *
     * @param baseGrade The innate starting grade of the Uma.
     * @param pinkStars The total count of pink stars for this specific aptitude.
     * @return The resulting upgraded AptitudeGrade.
     */
    public AptitudeGrade calculateTurnOneAptitude(AptitudeGrade baseGrade, int pinkStars) {
        int steps = calculateStarSteps(pinkStars);
        if (steps == 0) {
            return baseGrade;
        }

        int newRank = baseGrade.getRank() + steps;

        // Turn 1 Inheritance cannot exceed 'A' rank (int value 6)
        if (newRank > AptitudeGrade.A.getRank()) {
            newRank = AptitudeGrade.A.getRank();
        }

        return AptitudeGrade.fromRank(newRank);
    }

    /**
     * Calculates the aptitude upgrade if a pink spark successfully fires during an April Inspiration event.
     * April Inspiration is the ONLY way to breach the 'A' rank cap and reach 'S'.
     *
     * @param currentGrade The current grade prior to the Inspiration event.
     * @param sparkStarLevel The star power of the specific pink spark triggering.
     * @param highRoll Whether the spark triggered its "hidden innate high roll" to grant 2 steps instead of 1.
     * @return The resulting upgraded AptitudeGrade (capped at S).
     */
    public AptitudeGrade calculateAprilInspirationBoost(AptitudeGrade currentGrade, int sparkStarLevel, boolean highRoll) {
        if (currentGrade == AptitudeGrade.S) {
            return AptitudeGrade.S; // Already maxed
        }

        // Standard spark fires grant 1 step. Hidden values can grant 2 steps.
        int steps = highRoll ? 2 : 1;

        int newRank = currentGrade.getRank() + steps;

        // Absolute hard cap at S
        if (newRank > AptitudeGrade.S.getRank()) {
            newRank = AptitudeGrade.S.getRank();
        }

        return AptitudeGrade.fromRank(newRank);
    }

    /**
     * Maps the star count to linear step jumps according to JP inheritance rules.
     */
    private int calculateStarSteps(int pinkStars) {
        if (pinkStars >= 10) return 4;
        if (pinkStars >= 7) return 3;
        if (pinkStars >= 4) return 2;
        if (pinkStars >= 1) return 1;
        return 0;
    }
}
