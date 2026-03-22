package com.umascryfall.legacybuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceScheduleRepository extends JpaRepository<RaceSchedule, Long> {
}

// Tells the Spring Data JPA to generate the standard SQL entries for a schedule
// query.