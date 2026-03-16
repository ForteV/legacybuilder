package com.umascryfall.legacybuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UmaMusumeRepository extends JpaRepository<UmaMusume, String> {
    // By extending JpaRepository, Spring automatically provides basic CRUD
    // operations.
    // "UmaMusume" is the entity it manages, and "String" is the type of its @Id
    // variable.
}