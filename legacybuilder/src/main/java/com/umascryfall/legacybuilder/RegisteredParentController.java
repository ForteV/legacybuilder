package com.umascryfall.legacybuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parents")
public class RegisteredParentController {

    @Autowired
    private RegisteredParentRepository repository;

    @GetMapping
    public List<RegisteredParent> getAllParents() {
        return repository.findAll();
    }

    @PostMapping
    public RegisteredParent createParent(@RequestBody RegisteredParent newParent) {
        return repository.save(newParent);
    }
}