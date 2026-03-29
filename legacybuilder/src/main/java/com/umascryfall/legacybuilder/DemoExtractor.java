package com.umascryfall.legacybuilder;

import com.umascryfall.legacybuilder.entity.Race;
import com.umascryfall.legacybuilder.entity.RaceCourse;
import com.umascryfall.legacybuilder.entity.RaceTimeline;
import com.umascryfall.legacybuilder.enums.AptitudeGrade;
import com.umascryfall.legacybuilder.service.RaceRouteCalculatorService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DemoExtractor {

    public static void main(String[] args) {
        String dbPath = System.getProperty("user.home") + "/AppData/LocalLow/Cygames/umamusume/master/master.mdb";
        String url = "jdbc:sqlite:" + dbPath;

        UmaMusume demoUma = null;
        List<RaceTimeline> g1Timelines = new ArrayList<>();
        List<Race> races = new ArrayList<>();
        List<RaceCourse> courses = new ArrayList<>();

        System.out.println("Starting Database Live Data Extraction Component...");

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            ResultSet rsUma = stmt.executeQuery(
                "SELECT cr.proper_distance_short, cr.proper_distance_mile, " +
                "cr.proper_distance_middle, cr.proper_distance_long, cr.proper_ground_turf, cr.proper_ground_dirt " +
                "FROM card_rarity_data cr " +
                "WHERE cr.card_id = 100601 LIMIT 1"
            );

            if (rsUma.next()) {
                demoUma = new UmaMusume();
                demoUma.setId("100601");
                demoUma.setNameEng("Oguri Cap");
                
                demoUma.setShortDistance(parseGrade(rsUma.getInt("proper_distance_short")));
                demoUma.setMileDistance(parseGrade(rsUma.getInt("proper_distance_mile")));
                demoUma.setMediumDistance(parseGrade(rsUma.getInt("proper_distance_middle")));
                demoUma.setLongDistance(parseGrade(rsUma.getInt("proper_distance_long")));
                demoUma.setTurf(parseGrade(rsUma.getInt("proper_ground_turf")));
                demoUma.setDirt(parseGrade(rsUma.getInt("proper_ground_dirt")));

                System.out.println("Successfully Extracted Target Uma: " + demoUma.getNameEng());
                System.out.println("Turf: " + demoUma.getEffectiveTurf() + " | Dirt: " + demoUma.getEffectiveDirt());
                System.out.println("Short: " + demoUma.getEffectiveShortDistance() + " | Mile: " + demoUma.getEffectiveMileDistance() + 
                                   " | Med: " + demoUma.getEffectiveMediumDistance() + " | Long: " + demoUma.getEffectiveLongDistance());
            } else {
                System.out.println("WARNING: Could not locate Oguri Cap in local master.mdb (ID: 100601)");
                return;
            }

            // 2. Extract Race Courses
            ResultSet rsCourses = stmt.executeQuery("SELECT * FROM race_course_set");
            while (rsCourses.next()) {
                RaceCourse rc = new RaceCourse();
                rc.setId(rsCourses.getInt("id"));
                rc.setDistance(rsCourses.getInt("distance"));
                rc.setGround(rsCourses.getInt("ground"));
                courses.add(rc);
            }

            // 3. Extract G1 Races (usually grade=100 in master.mdb)
            ResultSet rsRaces = stmt.executeQuery("SELECT * FROM race WHERE grade=100");
            while (rsRaces.next()) {
                Race r = new Race();
                r.setId(rsRaces.getInt("id"));
                r.setGrade(rsRaces.getInt("grade"));
                r.setCourseSet(rsRaces.getInt("course_set"));
                races.add(r);
            }

            // Find Race Name category dynamically
            ResultSet rsRaceCat = stmt.executeQuery("SELECT category, \"index\" FROM text_data WHERE text = 'Japan Cup'");
            int raceTextCategory = 29; // fallback guess
            if (rsRaceCat.next()) {
                raceTextCategory = rsRaceCat.getInt("category");
                System.out.println("Discovered Race Name Text Category: " + raceTextCategory + " (Index: " + rsRaceCat.getInt("index") + ")");
            }
            
            // 4. Extract Race Timelines for G1s with Names
            ResultSet rsTimeline = stmt.executeQuery(
                "SELECT ri.*, COALESCE(t1.text, t2.text, 'Unknown Race') as race_name FROM race_instance ri " +
                "JOIN race r ON ri.race_id = r.id " +
                "LEFT JOIN text_data t1 ON t1.category = " + raceTextCategory + " AND t1.\"index\" = r.id " +
                "LEFT JOIN text_data t2 ON t2.category = " + raceTextCategory + " AND t2.\"index\" = r.\"group\" " +
                "WHERE r.grade=100");
            while (rsTimeline.next()) {
                RaceTimeline rt = new RaceTimeline();
                rt.setId(rsTimeline.getInt("id"));
                rt.setRaceId(rsTimeline.getInt("race_id"));
                // In game, year 1 = Junior, 2 = Classic, 3 = Senior. Time 1-24 = half months
                rt.setDate(rsTimeline.getInt("date"));
                rt.setTime(rsTimeline.getInt("time"));
                rt.setRaceName(rsTimeline.getString("race_name")); 
                g1Timelines.add(rt);
            }

            System.out.println("\nExtracted " + g1Timelines.size() + " total G1 instances from scenario calendar.");

            // 5. Run it through the logic!
            System.out.println("\nEngaging Phase 3: Trackblazer Route Calculator...");
            RaceRouteCalculatorService router = new RaceRouteCalculatorService();
            List<RaceTimeline> oguriSchedule = router.calculateOptimalRoute(demoUma, g1Timelines, races, courses);
            
            System.out.println("\nCALCULATED OPTIMAL G1 POOL FOR " + demoUma.getNameEng() + ":");
            System.out.println("Total Eligible G1s found before 35-Race Cap: " + oguriSchedule.size());

            int runCount = 0;
            for(RaceTimeline timeline : oguriSchedule) {
                runCount++;
                String year = (timeline.getDate() == 1) ? "Junior" : (timeline.getDate() == 2) ? "Classic" : "Senior";
                System.out.println("Entry #" + runCount + ": Year " + year + ", Turn " + timeline.getTime() + 
                                   " - " + timeline.getRaceName() + " (Race ID: " + timeline.getRaceId() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static AptitudeGrade parseGrade(int cygamesValue) {
        // Cygames 1=G, 2=F, 3=E, 4=D, 5=C, 6=B, 7=A, 8=S
        switch (cygamesValue) {
            case 1: return AptitudeGrade.G;
            case 2: return AptitudeGrade.F;
            case 3: return AptitudeGrade.E;
            case 4: return AptitudeGrade.D;
            case 5: return AptitudeGrade.C;
            case 6: return AptitudeGrade.B;
            case 7: return AptitudeGrade.A;
            case 8: return AptitudeGrade.S;
            default: return AptitudeGrade.G;
        }
    }
}
