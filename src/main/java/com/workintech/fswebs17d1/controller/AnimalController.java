package com.workintech.fswebs17d1.controller;


import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/workintech/animal") // Giriş kapısı adresi
public class AnimalController {

    private Map<Integer, Animal> animals = new HashMap<>();

    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    @GetMapping
    public List<Animal> get() {
        return new ArrayList<>(animals.values());
    }

    @GetMapping("/{id}")
    public Animal getById(@PathVariable Integer id) {
        return animals.get(id);
    }

    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    @PutMapping("/{id}")
    public Animal update(@PathVariable Integer id, @RequestBody Animal animal) {
        // Eski hayvanı silip yenisini o ID ile ekliyoruz
        animal.setId(id);
        animals.put(id, animal);
        return animal;
    }

    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable Integer id) {
        return animals.remove(id);
    }
}