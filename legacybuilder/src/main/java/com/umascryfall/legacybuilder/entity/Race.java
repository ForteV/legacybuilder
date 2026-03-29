package com.umascryfall.legacybuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "race")
public class Race {

    @Id
    private int id;
    
    private int grade;     // e.g., 100 for G1, 200 for G2, 300 for G3
    private int courseSet; // Maps to race_course_set.id

    public Race() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getGrade() { return grade; }
    public void setGrade(int grade) { this.grade = grade; }
    
    public int getCourseSet() { return courseSet; }
    public void setCourseSet(int courseSet) { this.courseSet = courseSet; }
}
