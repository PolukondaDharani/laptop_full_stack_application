package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    @Autowired
    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public List<Lap> getAllLaptops() {
        return laptopRepository.findAll();
    }

    public Lap getLaptopById(Long id) {
        return laptopRepository.findById(id).orElse(null);
    }
    @Transactional
    public ResponseEntity<String> addLaptop(Lap lap) {
        try {
            Lap savedLaptop = laptopRepository.save(lap);
            return ResponseEntity.ok("Laptop added successfully with ID: " + savedLaptop.getId());
        } catch (DataIntegrityViolationException e) {
            // Handle specific exception for data integrity violations (e.g., unique constraint violation)
            return ResponseEntity.badRequest().body("Error adding laptop: Duplicate entry");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding laptop: " + e.getMessage());
        }
    }


    public Lap updateLaptopById(Long id, Lap laptop) {
        return laptopRepository.findById(id)
                .map(existingLaptop -> {
                    existingLaptop.setName(laptop.getName());
                    existingLaptop.setPrice(laptop.getPrice());
                    existingLaptop.setBrand(laptop.getBrand());
                    existingLaptop.setStorage(laptop.getStorage());
                    existingLaptop.setRam(laptop.getRam());
                    existingLaptop.setProcessor(laptop.getProcessor());
                    return laptopRepository.save(existingLaptop);
                })
                .orElse(null);
    }

    public void deleteLaptopById(Long id) {
        laptopRepository.findById(id).ifPresent(laptopRepository::delete);
    }

    public Lap getLaptopByName(String name) {
        return laptopRepository.findByName(name);
    }

    public Lap getLaptopByPrice(int price) {
        return laptopRepository.findByPrice(price);
    }

    public List<Lap> getLaptopsByBrand(String brand) {
        return laptopRepository.findBybrand(brand);
    }
}
