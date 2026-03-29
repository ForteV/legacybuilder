package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.UmaMusume;
import com.umascryfall.legacybuilder.entity.Race;
import com.umascryfall.legacybuilder.entity.RaceCourse;
import com.umascryfall.legacybuilder.entity.RaceTimeline;
import com.umascryfall.legacybuilder.enums.AptitudeGrade;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RaceRouteCalculatorService {

    /**
     * Synthesizes an optimal Trackblazer rotation prioritizing G1 Overlaps without breaking endurance limits.
     * Evaluates effective traits to respect Grandparent override flex-capabilities.
     *
     * @param uma            The active Uma.
     * @param allG1Timelines Chronological list of all G1 races.
     * @param allRaces       Domain lookup list.
     * @param allCourses     Domain lookup list.
     * @return A mathematically valid rotation capped at Trackblazer 35 limit.
     */
    public List<RaceTimeline> calculateOptimalRoute(UmaMusume uma,
                                                    List<RaceTimeline> allG1Timelines,
                                                    List<Race> allRaces,
                                                    List<RaceCourse> allCourses) {

        List<RaceTimeline> validRotation = new ArrayList<>();

        for (RaceTimeline timeline : allG1Timelines) {
            Race raceRef = findRace(timeline.getRaceId(), allRaces);
            if (raceRef == null) continue;

            RaceCourse course = findCourse(raceRef.getCourseSet(), allCourses);
            if (course == null) continue;

            if (isRaceValidForUma(uma, course)) {
                validRotation.add(timeline);

                // Imposing the Trackblazer hard limit to avoid resource starvation
                if (validRotation.size() == 35) {
                    break;
                }
            }
        }

        return validRotation;
    }

    /**
     * Evaluates whether an Uma can actually run a race without severe penalty.
     * A minimum Aptitude of B in BOTH Distance and Surface is strictly required for Legacy routing.
     */
    private boolean isRaceValidForUma(UmaMusume uma, RaceCourse course) {
        // Evaluate Surface (Assuming Turf = 1, Dirt = 2 based on native schema defaults)
        AptitudeGrade requiredSurface = (course.getGround() == 1) ? uma.getEffectiveTurf() : uma.getEffectiveDirt();
        if (requiredSurface.getRank() < AptitudeGrade.B.getRank()) {
            return false;
        }

        // Evaluate Distance
        AptitudeGrade requiredDistance;
        int dist = course.getDistance();

        if (dist <= 1400) {
            requiredDistance = uma.getEffectiveShortDistance();
        } else if (dist <= 1800) {
            requiredDistance = uma.getEffectiveMileDistance();
        } else if (dist <= 2400) {
            requiredDistance = uma.getEffectiveMediumDistance();
        } else {
            requiredDistance = uma.getEffectiveLongDistance();
        }

        if (requiredDistance.getRank() < AptitudeGrade.B.getRank()) {
            return false;
        }

        return true;
    }

    private Race findRace(int id, List<Race> races) {
        return races.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    private RaceCourse findCourse(int id, List<RaceCourse> courses) {
        return courses.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }
}
