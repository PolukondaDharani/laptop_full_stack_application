package com.example.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/laptops")
@CrossOrigin(origins = "http://localhost:4200")
public class DatabaseController {

    @Autowired
    private LaptopRepository laptopRepository;

    @GetMapping("/all")
    public List<Lap> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lap> getLaptopById(@PathVariable Long id) {
        Optional<Lap> optionalLaptop = laptopRepository.findById(id);
        return optionalLaptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addLaptop(@RequestBody Lap lap) {
        try {
            Lap savedLaptop = laptopRepository.save(lap);
            return ResponseEntity.ok("Laptop added successfully with ID: " + savedLaptop.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding laptop: " + e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Lap> updateLaptopById(@PathVariable Long id, @RequestBody Lap laptop) {
        Optional<Lap> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            Lap existingLaptop = optionalLaptop.get();
            existingLaptop.setName(laptop.getName());
            existingLaptop.setPrice(laptop.getPrice());
            existingLaptop.setBrand(laptop.getBrand());
            existingLaptop.setStorage(laptop.getStorage());
            existingLaptop.setRam(laptop.getRam());
            existingLaptop.setProcessor(laptop.getProcessor());
            final Lap updatedLaptop = laptopRepository.save(existingLaptop);
            return ResponseEntity.ok(updatedLaptop);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLaptopById(@PathVariable Long id) {
        Optional<Lap> optionalLaptop = laptopRepository.findById(id);
        if (optionalLaptop.isPresent()) {
            laptopRepository.delete(optionalLaptop.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/byname/{name}")
    public Lap getLaptopByName(@PathVariable String name) {
        return laptopRepository.findByName(name);
    }
    
    @GetMapping("/byprice/{price}")
    public Lap getLaptopByPrice(@PathVariable int price) {
        return laptopRepository.findByPrice(price);
    }
    
    
    @GetMapping("/bybrand/{brand}")
    public List<Lap> getLaptopByBrand(@PathVariable String brand) {
        return laptopRepository.findBybrand(brand);
    }
    
    
}
