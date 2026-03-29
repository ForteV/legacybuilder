package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.enums.AptitudeGrade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AptitudeCalculatorServiceTest {

    private final AptitudeCalculatorService service = new AptitudeCalculatorService();

    @Test
    void testTurnOneAptitudeCap() {
        // Base E, with 10 stars (+4 steps). E -> D -> C -> B -> A.
        assertEquals(AptitudeGrade.A, service.calculateTurnOneAptitude(AptitudeGrade.E, 10));

        // Base C, with 10 stars (+4 steps). C -> B -> A -> S -> S. Should hard cap at A!
        assertEquals(AptitudeGrade.A, service.calculateTurnOneAptitude(AptitudeGrade.C, 10));

        // Base G, with 4 stars (+2 steps). G -> F -> E.
        assertEquals(AptitudeGrade.E, service.calculateTurnOneAptitude(AptitudeGrade.G, 4));

        // Base G, with 0 stars (no steps). G.
        assertEquals(AptitudeGrade.G, service.calculateTurnOneAptitude(AptitudeGrade.G, 0));
        
        // Edge Case: Base A, with 10 stars. Still A.
        assertEquals(AptitudeGrade.A, service.calculateTurnOneAptitude(AptitudeGrade.A, 10));
    }

    @Test
    void testAprilInspirationBoost() {
        // A rank triggering a standard spark should breach into S
        assertEquals(AptitudeGrade.S, service.calculateAprilInspirationBoost(AptitudeGrade.A, 1, false));

        // B rank triggering a high-roll spark (+2) should breach into S
        assertEquals(AptitudeGrade.S, service.calculateAprilInspirationBoost(AptitudeGrade.B, 3, true));

        // S rank triggering any spark stays S
        assertEquals(AptitudeGrade.S, service.calculateAprilInspirationBoost(AptitudeGrade.S, 3, false));
        
        // C rank standard spark -> B
        assertEquals(AptitudeGrade.B, service.calculateAprilInspirationBoost(AptitudeGrade.C, 1, false));
    }
}
