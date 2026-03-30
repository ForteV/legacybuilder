package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "skill_data")
public class SkillData {

    @Id
    private int id;

    private int rarity;            // 1=White, 2=Gold, 3=Unique, etc.
    private int group_id;          // Links lower tier skills to upper tiers (e.g. Corner Heal -> Arc Maestro)
    private int skill_category;    // Passive vs Active vs Healing
    
    // Conditions and Durations
    private String condition_1;
    private int float_ability_time_1;
    private int float_cooldown_time_1;

    // Primary Ability Memory Values
    private int ability_type_1_1;
    private int float_ability_value_1_1;
    private int target_type_1_1;

    // Secondary Ability Memory Values (hybrid skills like Speed+Accel)
    private int ability_type_1_2;
    private int float_ability_value_1_2;
    private int target_type_1_2;

    @Transient
    private String nameEng;

    @Transient
    private String descriptionEng;

    public SkillData() {}

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getRarity() { return rarity; }
    public void setRarity(int rarity) { this.rarity = rarity; }

    public int getGroupId() { return group_id; }
    public void setGroupId(int groupId) { this.group_id = groupId; }

    public int getSkillCategory() { return skill_category; }
    public void setSkillCategory(int category) { this.skill_category = category; }

    public String getCondition1() { return condition_1; }
    public void setCondition1(String cond) { this.condition_1 = cond; }

    public int getFloatAbilityTime1() { return float_ability_time_1; }
    public void setFloatAbilityTime1(int time) { this.float_ability_time_1 = time; }

    public int getFloatCooldownTime1() { return float_cooldown_time_1; }
    public void setFloatCooldownTime1(int cooldown) { this.float_cooldown_time_1 = cooldown; }

    public int getAbilityType1_1() { return ability_type_1_1; }
    public void setAbilityType1_1(int type) { this.ability_type_1_1 = type; }

    public int getFloatAbilityValue1_1() { return float_ability_value_1_1; }
    public void setFloatAbilityValue1_1(int val) { this.float_ability_value_1_1 = val; }

    public int getTargetType1_1() { return target_type_1_1; }
    public void setTargetType1_1(int target) { this.target_type_1_1 = target; }

    public int getAbilityType1_2() { return ability_type_1_2; }
    public void setAbilityType1_2(int type) { this.ability_type_1_2 = type; }

    public int getFloatAbilityValue1_2() { return float_ability_value_1_2; }
    public void setFloatAbilityValue1_2(int val) { this.float_ability_value_1_2 = val; }

    public int getTargetType1_2() { return target_type_1_2; }
    public void setTargetType1_2(int target) { this.target_type_1_2 = target; }

    public String getNameEng() { return nameEng; }
    public void setNameEng(String name) { this.nameEng = name; }

    public String getDescriptionEng() { return descriptionEng; }
    public void setDescriptionEng(String desc) { this.descriptionEng = desc; }
}
