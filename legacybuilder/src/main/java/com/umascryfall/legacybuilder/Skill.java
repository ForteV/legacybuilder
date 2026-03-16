package com.umascryfall.legacybuilder;

public class Skill {

    // basic info
    private String id;
    private String nameEng;
    private String nameJp;

    // constructors // generic constructor for deserialization
    public Skill() {
    }

    // constructor for creating new skills
    public Skill(String id, String nameEng, String nameJp) {
        this.id = id;
        this.nameEng = nameEng;
        this.nameJp = nameJp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameJp() {
        return nameJp;
    }

    public void setNameJp(String nameJp) {
        this.nameJp = nameJp;
    }

}
