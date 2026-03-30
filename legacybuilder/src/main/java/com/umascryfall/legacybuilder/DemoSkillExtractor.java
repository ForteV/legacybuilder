package com.umascryfall.legacybuilder;

import com.umascryfall.legacybuilder.service.SkillDictionaryService;
import com.umascryfall.legacybuilder.entity.SkillData;
import java.sql.Connection;
import java.sql.DriverManager;

public class DemoSkillExtractor {
    public static void main(String[] args) {
        String dbPath = System.getProperty("user.home") + "/AppData/LocalLow/Cygames/umamusume/master/master.mdb";
        String url = "jdbc:sqlite:" + dbPath;

        try (Connection conn = DriverManager.getConnection(url)) {
            SkillDictionaryService dict = new SkillDictionaryService();
            dict.loadSkillsFromDb(conn);

            System.out.println("\n==================================");
            System.out.println("PHASE 4: SKILL LIBRARY EXTRACTION TEST");
            System.out.println("==================================");

            SkillData maestro = dict.findSkillByName("Swinging Maestro");
            if(maestro != null) {
                System.out.println("\n[SKILL LOGIC PARSED]");
                System.out.println("Target: " + maestro.getNameEng() + " (ID: " + maestro.getId() + ")");
                System.out.println("Description: " + maestro.getDescriptionEng());
                System.out.println("Rarity: " + maestro.getRarity() + " (2 = Gold)");
                System.out.println("Category: " + maestro.getSkillCategory() + " (2 = Active/Recovery)");
                System.out.println("Condition String: " + maestro.getCondition1());
                System.out.println("Ability Type 1: " + maestro.getAbilityType1_1() + " (31 = Stamina Recovery Mode)");
                
                // Memory value in CyGames is integer based, 550 means 5.5% recovery 
                System.out.println("Ability Float Value: " + maestro.getFloatAbilityValue1_1() + " (Memory Format, 550 = 5.5%)");
            } else {
                System.out.println("\n[ERROR] Swinging Maestro not found via Exact English match.");
            }

            SkillData rampUp = dict.findSkillByName("Ramp Up");
            if(rampUp != null) {
                System.out.println("\n[SKILL LOGIC PARSED]");
                System.out.println("Target: " + rampUp.getNameEng() + " (ID: " + rampUp.getId() + ")");
                System.out.println("Description: " + rampUp.getDescriptionEng());
                System.out.println("Ability Type 1: " + rampUp.getAbilityType1_1() + " (21 = Speed)");
                System.out.println("Ability Float Value: " + rampUp.getFloatAbilityValue1_1() + " (Memory Format)");
            }

            SkillData holyNight = dict.findSkillByName("Holy Night's Miracle, with You");
            if(holyNight == null) holyNight = dict.findSkillByName("Holy Night's Miracle");
            if(holyNight != null) {
                System.out.println("\n[SKILL LOGIC PARSED]");
                System.out.println("Target: " + holyNight.getNameEng() + " (Christmas Oguri Unique)");
                System.out.println("Type 1_1: " + holyNight.getAbilityType1_1() + " | Val: " + holyNight.getFloatAbilityValue1_1());
                System.out.println("Type 1_2: " + holyNight.getAbilityType1_2() + " | Val: " + holyNight.getFloatAbilityValue1_2());
                System.out.println("Condition: " + holyNight.getCondition1());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
