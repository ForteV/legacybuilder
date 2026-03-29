package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.UmaMusume;
import com.umascryfall.legacybuilder.entity.Race;
import com.umascryfall.legacybuilder.entity.RaceCourse;
import com.umascryfall.legacybuilder.entity.RaceTimeline;
import com.umascryfall.legacybuilder.enums.AptitudeGrade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RaceRouteCalculatorServiceTest {

    private final RaceRouteCalculatorService service = new RaceRouteCalculatorService();
    private List<RaceTimeline> mockTimelines;
    private List<Race> mockRaces;
    private List<RaceCourse> mockCourses;

    @BeforeEach
    void setUp() {
        mockTimelines = new ArrayList<>();
        mockRaces = new ArrayList<>();
        mockCourses = new ArrayList<>();

        // 1. Create a Turf Mile Race
        RaceCourse courseTurfMile = new RaceCourse();
        courseTurfMile.setId(10);
        courseTurfMile.setDistance(1600);
        courseTurfMile.setGround(1); // 1 = Turf
        mockCourses.add(courseTurfMile);

        Race raceTurfMile = new Race();
        raceTurfMile.setId(1);
        raceTurfMile.setCourseSet(10);
        mockRaces.add(raceTurfMile);

        RaceTimeline timelineTurfMile = new RaceTimeline();
        timelineTurfMile.setRaceId(1);
        mockTimelines.add(timelineTurfMile);

        // 2. Create a Dirt Short Race
        RaceCourse courseDirtShort = new RaceCourse();
        courseDirtShort.setId(20);
        courseDirtShort.setDistance(1200);
        courseDirtShort.setGround(2); // 2 = Dirt
        mockCourses.add(courseDirtShort);

        Race raceDirtShort = new Race();
        raceDirtShort.setId(2);
        raceDirtShort.setCourseSet(20);
        mockRaces.add(raceDirtShort);

        RaceTimeline timelineDirtShort = new RaceTimeline();
        timelineDirtShort.setRaceId(2);
        mockTimelines.add(timelineDirtShort);
    }

    @Test
    void testOguriCapFlexibility() {
        // Oguri Cap naturally dominates multiple surfaces and distances
        UmaMusume oguri = new UmaMusume();
        oguri.setDirt(AptitudeGrade.A);
        oguri.setTurf(AptitudeGrade.A);
        oguri.setMileDistance(AptitudeGrade.A);
        oguri.setShortDistance(AptitudeGrade.B); // B is technically sufficient to run without penalty
        
        List<RaceTimeline> validRaces = service.calculateOptimalRoute(oguri, mockTimelines, mockRaces, mockCourses);
        // Route Engine should validate BOTH races successfully.
        assertEquals(2, validRaces.size());
    }

    @Test
    void testHaruUraraRestriction() {
        // Haru Urara is strictly Dirt/Short innate.
        UmaMusume urara = new UmaMusume();
        urara.setDirt(AptitudeGrade.A);
        urara.setShortDistance(AptitudeGrade.A);
        urara.setTurf(AptitudeGrade.G);
        urara.setMileDistance(AptitudeGrade.G);

        List<RaceTimeline> validRaces = service.calculateOptimalRoute(urara, mockTimelines, mockRaces, mockCourses);
        
        // Urara must gracefully fail the Turf Mile requirement, returning only the Dirt Short.
        assertEquals(1, validRaces.size());
        assertEquals(2, validRaces.get(0).getRaceId()); // Validates exactly which race got approved
    }

    @Test
    void testGrandparentManualOverride() {
        // Setup an Uma with Urara's strict innate limitations
        UmaMusume grandparent = new UmaMusume();
        grandparent.setDirt(AptitudeGrade.A);
        grandparent.setShortDistance(AptitudeGrade.A);
        grandparent.setTurf(AptitudeGrade.G);
        grandparent.setMileDistance(AptitudeGrade.G);

        // FLAG ACTIVATED: User manually overrides the system logic specifically to buff this Grandparent
        grandparent.getAptitudeProfile().setManualOverride(true);
        grandparent.getAptitudeProfile().setDirt(AptitudeGrade.A);
        grandparent.getAptitudeProfile().setShortDistance(AptitudeGrade.A);
        
        // Emulating the Parent sparks buffing Turf/Mile to A-Rank artificially before scheduling
        grandparent.getAptitudeProfile().setTurf(AptitudeGrade.A);
        grandparent.getAptitudeProfile().setMileDistance(AptitudeGrade.A);

        List<RaceTimeline> validRaces = service.calculateOptimalRoute(grandparent, mockTimelines, mockRaces, mockCourses);
        
        // The Engine perfectly respects the Profile overrides and unlocks both races.
        assertEquals(2, validRaces.size());
    }
}
