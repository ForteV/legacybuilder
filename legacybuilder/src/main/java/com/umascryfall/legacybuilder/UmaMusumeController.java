package com.umascryfall.legacybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/umas")
public class UmaMusumeController {

    @Autowired
    private UmaMusumeRepository repository;

    // GET Request: View all Umas in the database
    @GetMapping
    public List<UmaMusume> getAllUmas() {
        return repository.findAll();
    }

    // POST Request: Add a new Uma to the database
    @PostMapping
    public UmaMusume createUma(@RequestBody UmaMusume newUma) {
        return repository.save(newUma);
    }
}