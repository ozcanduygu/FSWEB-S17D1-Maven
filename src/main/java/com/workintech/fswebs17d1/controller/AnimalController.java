package com.workintech.fswebs17d1.controller;

// Bu kısımlar "yardımcı aletlerin" adresleri, kırmızıları bunlar bitirir:
import com.workintech.fswebs17d1.entity.Animal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController // Bu sınıf artık bir garson (API)
@RequestMapping("/workintech/animal") // Giriş kapısı adresi
public class AnimalController {

    // Verilerin tutulduğu defter
    private Map<Integer, Animal> animals = new HashMap<>();

    // application.properties dosyasından gelen bilgiler
    @Value("${course.name}")
    private String courseName;

    @Value("${project.developer.fullname}")
    private String developerName;

    // [GET] Hepsini Listele
    @GetMapping
    public List<Animal> get() {
        return new ArrayList<>(animals.values());
    }

    // [GET] Tek bir tanesini getir
    @GetMapping("/{id}")
    public Animal getById(@PathVariable Integer id) {
        return animals.get(id);
    }

    // [POST] Yeni hayvan ekle
    @PostMapping
    public Animal save(@RequestBody Animal animal) {
        animals.put(animal.getId(), animal);
        return animal;
    }

    // [PUT] Güncelleme
    @PutMapping("/{id}")
    public Animal update(@PathVariable Integer id, @RequestBody Animal animal) {
        // Eski hayvanı silip yenisini o ID ile ekliyoruz
        animal.setId(id);
        animals.put(id, animal);
        return animal;
    }

    // [DELETE] Silme
    @DeleteMapping("/{id}")
    public Animal delete(@PathVariable Integer id) {
        return animals.remove(id);
    }
}