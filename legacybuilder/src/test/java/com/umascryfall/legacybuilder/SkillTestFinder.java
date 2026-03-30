package com.umascryfall.legacybuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SkillTestFinder {
    public static void main(String[] args) {
        String dbPath = System.getProperty("user.home") + "/AppData/LocalLow/Cygames/umamusume/master/master.mdb";
        String url = "jdbc:sqlite:" + dbPath;

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Find Swinging Maestro in text_data
            ResultSet rs = stmt.executeQuery("SELECT category, \"index\" FROM text_data WHERE text = 'Swinging Maestro' OR text = 'Ramp Up'");
            while(rs.next()) {
                System.out.println("Skill Text Found -> Category: " + rs.getInt("category") + " | Index: " + rs.getInt("index"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
