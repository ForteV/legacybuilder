package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.UmaMusume;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AffinityMathService {

    /**
     * Traverses the direct ascending nodes of an inheritance tree and calculates total raw Affinity.
     * Note: Actively excludes Parent 1 -> Parent 2 overlapping wins as per JP formula.
     * 
     * @param trainee The active Uma acting as the target of the inheritance.
     * @param parent1 The Mother.
     * @param parent2 The Father.
     * @param p1Gp1 Mother's Grandparent 1.
     * @param p1Gp2 Mother's Grandparent 2.
     * @param p2Gp1 Father's Grandparent 1.
     * @param p2Gp2 Father's Grandparent 2.
     * @return The absolute total Affinity numerical rating.
     */
    public int calculateTotalAffinity(UmaMusume trainee,
                                      UmaMusume parent1, UmaMusume parent2,
                                      UmaMusume p1Gp1, UmaMusume p1Gp2,
                                      UmaMusume p2Gp1, UmaMusume p2Gp2) {
        
        int totalAffinity = 0;

        // --- 1. Base Affiliate (Native JP Database Points from Succession Tables) ---
        totalAffinity += getBaseAffinity(trainee, parent1);
        totalAffinity += getBaseAffinity(trainee, parent2);
        
        totalAffinity += getBaseAffinity(parent1, p1Gp1);
        totalAffinity += getBaseAffinity(parent1, p1Gp2);
        
        totalAffinity += getBaseAffinity(parent2, p2Gp1);
        totalAffinity += getBaseAffinity(parent2, p2Gp2);


        // --- 2. G1 Overlap (+3 per shared G1 race across active ascending nodes) ---
        totalAffinity += getSharedG1WinsScore(trainee, parent1);
        totalAffinity += getSharedG1WinsScore(trainee, parent2);
        
        totalAffinity += getSharedG1WinsScore(parent1, p1Gp1);
        totalAffinity += getSharedG1WinsScore(parent1, p1Gp2);
        
        totalAffinity += getSharedG1WinsScore(parent2, p2Gp1);
        totalAffinity += getSharedG1WinsScore(parent2, p2Gp2);

        return totalAffinity;
    }

    /**
     * Finds the intersection of G1 races won between two Umas, and grants +3 Affinity per shared win.
     * (Currently stubbed to 0 until `RaceHistory` JPA entities are defined and populated).
     */
    public int getSharedG1WinsScore(UmaMusume nodeA, UmaMusume nodeB) {
        if (nodeA == null || nodeB == null) return 0;

        // TODO: Replace with real relations once Race Logging is fully mapped later in Phase 2
        // Set<String> aG1s = nodeA.getWonG1RaceIds();
        // Set<String> bG1s = nodeB.getWonG1RaceIds();
        // aG1s.retainAll(bG1s);
        // return aG1s.size() * 3;
        
        return 0; // Stub 
    }

    /**
     * Pulls the absolute base stringency of relationship between two Umas based on Cygames code points.
     * (Currently stubbed to 50 until `succession_relation` table mappings are fully built).
     */
    public int getBaseAffinity(UmaMusume nodeA, UmaMusume nodeB) {
        if (nodeA == null || nodeB == null) return 0;
        
        // TODO: Map query hitting `succession_relation` via `succession_relation_member`
        // return successionRelationRepository.getNativeAffinityPoints(nodeA.getId(), nodeB.getId());
        
        return 50; // Stub returning neutral 50
    }
}
