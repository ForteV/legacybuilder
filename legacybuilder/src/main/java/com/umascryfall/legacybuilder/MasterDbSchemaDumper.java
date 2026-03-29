package com.umascryfall.legacybuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

public class MasterDbSchemaDumper {

    public static void main(String[] args) {
        String dbPath = System.getProperty("user.home") + "/AppData/LocalLow/Cygames/umamusume/master/master.mdb";
        String url = "jdbc:sqlite:" + dbPath;
        File dbFile = new File(dbPath);
        
        System.out.println("Checking DB path: " + dbPath);
        if (!dbFile.exists()) {
             System.out.println("Could not find master.mdb at " + dbPath);
             return;
        }

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             PrintWriter out = new PrintWriter(new FileWriter("master_schema.txt"))) {

            System.out.println("Connected to Master DB! Extracting table definitions...");

            // Get all table schemas mentioning relevant keywords including succession/relation and race
            ResultSet rs = stmt.executeQuery("SELECT name, sql FROM sqlite_master WHERE type='table' " +
             "AND (name LIKE '%card%' OR name LIKE '%skill%' OR name LIKE '%text%' OR name LIKE '%succession%' OR name LIKE '%relation%' OR name LIKE '%affinity%' OR name LIKE '%chara%' OR name LIKE '%race%') ORDER BY name;");
            
            while (rs.next()) {
                String tableName = rs.getString("name");
                String sql = rs.getString("sql");
                out.println("---- TABLE: " + tableName + " ----");
                out.println(sql);
                out.println();
            }
            
            System.out.println("Schema dumped successfully to master_schema.txt in project root.");

        } catch (Exception e) {
            System.err.println("Error querying SQLite database:");
            e.printStackTrace();
        }
    }
}
