package com.umascryfall.legacybuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredParentRepository extends JpaRepository<RegisteredParent, Long> {
}