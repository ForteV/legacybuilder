package com.umascryfall.legacybuilder.service;

import com.umascryfall.legacybuilder.entity.SkillData;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class SkillDictionaryService {
    private Map<Integer, SkillData> skillRegistry = new HashMap<>();

    public void loadSkillsFromDb(Connection conn) throws Exception {
        System.out.println("Executing Phase 4: Fetching Memory Parameters and Localizing Skill Library...");
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                "SELECT s.*, t.text as name_eng, t2.text as desc_eng " +
                "FROM skill_data s " +
                "LEFT JOIN text_data t ON t.category = 47 AND t.\"index\" = s.id " +
                "LEFT JOIN text_data t2 ON t2.category = 147 AND t2.\"index\" = s.id"
            );
            int count = 0;
            while (rs.next()) {
                SkillData skill = new SkillData();
                skill.setId(rs.getInt("id"));
                skill.setRarity(rs.getInt("rarity"));
                skill.setGroupId(rs.getInt("group_id"));
                skill.setSkillCategory(rs.getInt("skill_category"));
                
                skill.setCondition1(rs.getString("condition_1"));
                skill.setFloatAbilityTime1(rs.getInt("float_ability_time_1"));
                skill.setFloatCooldownTime1(rs.getInt("float_cooldown_time_1"));
                
                skill.setAbilityType1_1(rs.getInt("ability_type_1_1"));
                skill.setFloatAbilityValue1_1(rs.getInt("float_ability_value_1_1"));
                skill.setTargetType1_1(rs.getInt("target_type_1_1"));
                
                skill.setAbilityType1_2(rs.getInt("ability_type_1_2"));
                skill.setFloatAbilityValue1_2(rs.getInt("float_ability_value_1_2"));
                skill.setTargetType1_2(rs.getInt("target_type_1_2"));
                
                String nameEng = rs.getString("name_eng");
                if(nameEng == null || nameEng.isEmpty()) {
                    nameEng = "Unknown_Skill_" + skill.getId();
                }
                skill.setNameEng(nameEng);
                skill.setDescriptionEng(rs.getString("desc_eng"));
                
                skillRegistry.put(skill.getId(), skill);
                count++;
            }
            System.out.println("Success! Fully mapped and localized " + count + " CYGAMES Skill memory rows.");
        }
    }

    public SkillData getSkill(int id) {
        return skillRegistry.get(id);
    }
    
    public SkillData findSkillByName(String name) {
        return skillRegistry.values().stream()
            .filter(s -> name.equalsIgnoreCase(s.getNameEng()))
            .findFirst()
            .orElse(null);
    }

    public Map<Integer, SkillData> getAllSkills() {
        return skillRegistry;
    }
}
